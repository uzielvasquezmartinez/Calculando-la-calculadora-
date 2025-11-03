package mx.edu.utez.calculadoramvvm.data.model

data class SettingsUiState(
    val isDarkMode: Boolean = false,
    val volume: Float = 0.5f,       // Valor por defecto (50%)
    val brightness: Float = 0.5f,   // Valor por defecto (50%)
    val difficulty: String = "Normal" // Valor por defecto
)