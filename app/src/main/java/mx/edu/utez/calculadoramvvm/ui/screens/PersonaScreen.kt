package mx.edu.utez.calculadoramvvm.ui.screens

import Title
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import mx.edu.utez.calculadoramvvm.ui.components.PersonaList
import mx.edu.utez.calculadoramvvm.ui.components.texts.Label
import mx.edu.utez.calculadoramvvm.viewmodel.PersonaViewModel

@Composable
fun PersonaScreen(viewModel: PersonaViewModel, navController: NavController){
    Column{
        Title("Usuarios registrados")
        val personas by viewModel.personas.collectAsStateWithLifecycle()
        PersonaList(personas) { viewModel::clickPersona }
        Label("Fin de la lista")
    }
}