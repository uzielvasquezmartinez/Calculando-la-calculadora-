package mx.edu.utez.calculadoramvvm.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utez.calculadoramvvm.viewmodel.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    // Inyectamos el ViewModel
    settingsViewModel: SettingsViewModel = viewModel(), navController: NavController
) {
    // 1. Observamos el UiState del ViewModel
    val uiState by settingsViewModel.uiState.collectAsState()

    // Opciones para el selector de dificultad
    val difficultyOptions = listOf("Fácil", "Normal", "Difícil")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // --- 1. Switch Modo Oscuro ---
            SettingRow(
                title = "Modo Oscuro",
                icon = { Icon(Icons.Default.DarkMode, contentDescription = "Modo Oscuro") }
            ) {
                Switch(
                    checked = uiState.isDarkMode,
                    onCheckedChange = { settingsViewModel.setDarkMode(it) }
                )
            }

            Spacer(Modifier.height(16.dp))

            // --- 2. Slider de Volumen ---
            SettingSlider(
                title = "Volumen",
                icon = { Icon(Icons.Default.VolumeUp, contentDescription = "Volumen") },
                value = uiState.volume,
                onValueChange = { settingsViewModel.setVolume(it) }
            )

            Spacer(Modifier.height(16.dp))

            // --- 3. Slider de Brillo ---
            SettingSlider(
                title = "Brillo",
                icon = { Icon(Icons.Default.BrightnessMedium, contentDescription = "Brillo") },
                value = uiState.brightness,
                onValueChange = { settingsViewModel.setBrightness(it) }
            )

            Spacer(Modifier.height(24.dp))

            // --- 4. Selector de Dificultad ---
            Text(
                text = "Dificultad",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                difficultyOptions.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = difficultyOptions.size
                        ),
                        onClick = { settingsViewModel.setDifficulty(label) },
                        selected = (label == uiState.difficulty)
                    ) {
                        Text(label)
                    }
                }
            }
        }
    }
}
/** Composable auxiliar para filas de settings (Switch) */
@Composable
private fun SettingRow(
    title: String,
    icon: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon()
            Spacer(Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
        }
        content()
    }
}
/** Composable auxiliar para filas de settings (Slider) */
@Composable
private fun SettingSlider(
    title: String,
    icon: @Composable () -> Unit,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon()
            Spacer(Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
}