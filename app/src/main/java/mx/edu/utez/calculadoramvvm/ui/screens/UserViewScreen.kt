package mx.edu.utez.calculadoramvvm.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utez.calculadoramvvm.ui.components.dinamiclists.DynamicUserList
import mx.edu.utez.calculadoramvvm.viewmodel.DynamicUserListViewModel

@Composable
fun UserViewScreen(
    viewModel: DynamicUserListViewModel = viewModel(),
    navController: NavController
    ) {
        // 1. Observa el estado del ViewModel.
        // La UI se recompondrá automáticamente cuando 'items' cambie.
        val items by viewModel.items.collectAsStateWithLifecycle()

        // 2. Pasa los datos y la función del ViewModel al Composable de lista.
        // Usamos una referencia al método: viewModel::onItemClicked
        DynamicUserList (
            items = items,
            onItemClick = viewModel::onItemClicked
        )
}