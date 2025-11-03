package mx.edu.utez.calculadoramvvm.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mx.edu.utez.calculadoramvvm.data.model.SettingsUiState
import mx.edu.utez.calculadoramvvm.ui.components.SettingsRepository

/**
 * Hereda de AndroidViewModel para tener acceso al Application context.
 */
class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsRepository: SettingsRepository

    init {
        val appContext = getApplication<Application>().applicationContext

        val userSettingsDataStore = appContext.dataStore

        // 3. Pasa la instancia de DataStore (NO el context) al repositorio
        settingsRepository = SettingsRepository(userSettingsDataStore)
    }

    /**
     * Expone el StateFlow a la UI.
     * stateIn lo convierte en un Flow "caliente" que sobrevive a cambios
     * de configuración (mientras el viewModelScope esté vivo).
     */
    val uiState: StateFlow<SettingsUiState> = settingsRepository.settingsUiStateFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SettingsUiState() // Estado inicial mientras carga DataStore
        )

    // --- Acciones de la UI ---
    // La UI llama a estas funciones, que delegan el trabajo
    // al repositorio dentro de una corutina.

    fun setDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingsRepository.setDarkMode(isDarkMode)
        }
    }

    fun setVolume(volume: Float) {
        viewModelScope.launch {
            settingsRepository.setVolume(volume)
        }
    }

    fun setBrightness(brightness: Float) {
        viewModelScope.launch {
            settingsRepository.setBrightness(brightness)
        }
    }

    fun setDifficulty(difficulty: String) {
        viewModelScope.launch {
            settingsRepository.setDifficulty(difficulty)
        }
    }
}