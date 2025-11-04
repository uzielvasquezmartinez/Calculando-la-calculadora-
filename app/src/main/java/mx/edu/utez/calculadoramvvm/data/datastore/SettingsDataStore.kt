package mx.edu.utez.calculadoramvvm.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Nombre del archivo de DataStore
private const val SETTINGS_PREFERENCES_NAME = "settings_preferences"

// Delegado para crear la instancia de DataStore a nivel de Context
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCES_NAME
)

class SettingsDataStore(private val context: Context) {

    // --- Definición de las claves ---
    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val VOLUME = floatPreferencesKey("volume")
        val BRIGHTNESS = floatPreferencesKey("brightness")
        val DIFFICULTY = stringPreferencesKey("difficulty")
    }

    // --- Leer los datos de configuración (Flow) ---
    // Retorna un Flow con el estado actual de la configuración.
    val settingsFlow: Flow<SettingsData> = context.dataStore.data
        .map { preferences ->
            // Mapeamos las preferencias a un objeto de datos (Data Class)
            SettingsData(
                isDarkMode = preferences[IS_DARK_MODE] ?: false, // Valor por defecto
                volume = preferences[VOLUME] ?: 0.5f,              // Valor por defecto (ej. 0.5)
                brightness = preferences[BRIGHTNESS] ?: 0.5f,      // Valor por defecto (ej. 0.5)
                difficulty = preferences[DIFFICULTY] ?: "Normal"   // Valor por defecto
            )
        }


    // --- Funciones para guardar los datos (Suspended functions) ---

    suspend fun saveDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun saveVolume(volume: Float) {
        context.dataStore.edit { preferences ->
            preferences[VOLUME] = volume
        }
    }

    suspend fun saveBrightness(brightness: Float) {
        context.dataStore.edit { preferences ->
            preferences[BRIGHTNESS] = brightness
        }
    }

    suspend fun saveDifficulty(difficulty: String) {
        context.dataStore.edit { preferences ->
            preferences[DIFFICULTY] = difficulty
        }
    }
}
// Data class que representa los datos de configuración (UiState)
data class SettingsData(
    val isDarkMode: Boolean,
    val volume: Float,
    val brightness: Float,
    val difficulty:String
)

