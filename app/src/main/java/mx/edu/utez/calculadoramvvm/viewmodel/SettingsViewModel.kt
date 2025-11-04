package mx.edu.utez.calculadoramvvm.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel // <-- Cambiamos a AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import mx.edu.utez.calculadoramvvm.data.datastore.*
import mx.edu.utez.calculadoramvvm.data.datastore.SettingsDataStore


// Data class para el UiState (si aún no la tienes)
data class SettingsUiState(
    val isDarkMode: Boolean = false,
    val volume: Float = 0.5f,
    val brightness: Float = 0.5f,
    val difficulty: String = "Normal"
)

// Cambiamos a AndroidViewModel y recibimos Application
class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsDataStore: SettingsDataStore = SettingsDataStore(application)

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        // Inicializar y recolectar los datos de DataStore
        settingsDataStore.settingsFlow
            .onEach { settingsData: SettingsData ->
                // Actualizar el UiState con los valores persistidos
                _uiState.update { currentState ->
                    currentState.copy(
                        isDarkMode = settingsData.isDarkMode,
                        volume = settingsData.volume,
                        brightness = settingsData.brightness,
                        difficulty = settingsData.difficulty
                    )
                }
            }
            .launchIn(viewModelScope) // Recolectar dentro del ámbito del ViewModel
    }
    // --- Funciones para cambiar y PERSISTIR los valores ---

    fun setDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveDarkMode(isDarkMode) // Persistir en DataStore
        }
    }



    fun setVolume(volume: Float) {
        viewModelScope.launch {
            settingsDataStore.saveVolume(volume) // Persistir en DataStore
        }
    }

    fun setBrightness(brightness: Float) {
        viewModelScope.launch {
            settingsDataStore.saveBrightness(brightness) // Persistir en DataStore
        }
    }

    fun setDifficulty(difficulty: String) {
        viewModelScope.launch {
            settingsDataStore.saveDifficulty(difficulty) // Persistir en DataStore
            }
        }
}