package mx.edu.utez.calculadoramvvm.ui.components

import android.R.attr.x
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.calculadoramvvm.R
import mx.edu.utez.calculadoramvvm.data.model.User
import mx.edu.utez.calculadoramvvm.ui.theme.CalculadoraMVVMTheme
import mx.edu.utez.calculadoramvvm.viewmodel.UserListViewModel

@Composable
fun UserList(lista: List<User>, viewModel: UserListViewModel){
    LazyColumn {
        items(items = lista, key = { it.id }) { item ->
            UserCard(
                usr = item,
                x = { viewModel.onItemClicked(item) }
            )
        }
    }

}

