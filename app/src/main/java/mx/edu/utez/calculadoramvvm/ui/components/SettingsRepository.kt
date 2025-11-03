package mx.edu.utez.calculadoramvvm.ui.components

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import mx.edu.utez.calculadoramvvm.data.model.SettingsUiState
import java.io.IOException

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    /**
     * Define las claves (Keys) para cada ajuste.
     * Ponerlas aquí evita errores de tipeo en otros lugares.
     */
    private object PreferencesKeys {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val VOLUME = floatPreferencesKey("volume")
        val BRIGHTNESS = floatPreferencesKey("brightness")
        val DIFFICULTY = stringPreferencesKey("difficulty")
    }

    /**
     * Un único Flow que emite el estado completo de la configuración
     * cada vez que CUALQUIER preferencia cambia.
     */
    val settingsUiStateFlow: Flow<SettingsUiState> = dataStore.data
        .catch { exception ->
            // dataStore.data puede lanzar IOException si hay un error de E/S
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            // Lee cada valor o usa el default definido en SettingsUiState
            val defaults = SettingsUiState()
            val isDarkMode = preferences[PreferencesKeys.IS_DARK_MODE] ?: defaults.isDarkMode
            val volume = preferences[PreferencesKeys.VOLUME] ?: defaults.volume
            val brightness = preferences[PreferencesKeys.BRIGHTNESS] ?: defaults.brightness
            val difficulty = preferences[PreferencesKeys.DIFFICULTY] ?: defaults.difficulty

            SettingsUiState(isDarkMode, volume, brightness, difficulty)
        }

    // --- Funciones de Escritura (Suspend) ---

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun setVolume(volume: Float) {
        dataStore.edit { preferences ->
            // Aseguramos que el valor esté en el rango 0.0 a 1.0
            preferences[PreferencesKeys.VOLUME] = volume.coerceIn(0f, 1f)
        }
    }

    suspend fun setBrightness(brightness: Float) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.BRIGHTNESS] = brightness.coerceIn(0f, 1f)
        }
    }

    suspend fun setDifficulty(difficulty: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DIFFICULTY] = difficulty
        }
    }
}