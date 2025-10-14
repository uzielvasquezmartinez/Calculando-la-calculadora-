package mx.edu.utez.calculadoramvvm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utez.calculadoramvvm.R
import mx.edu.utez.calculadoramvvm.data.model.User
import mx.edu.utez.calculadoramvvm.ui.theme.CalculadoraMVVMTheme

@Composable
fun UserCard(usr: User, x: (User) -> Unit){
    Column {
        Card (modifier = Modifier.clickable{x(usr)}) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(usr.imagen),
                    contentDescription = "Imagen de usuario"
                )
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text=usr.nombre)
                    Text(text=usr.correo)
                    Text(text=usr.telefono)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserCard(){
    val u1 = User(
        1,
        R.drawable.femaleprofile,
        "Berenice",
        "berenice@gmail.com",
        "7771892834",
        1)
    CalculadoraMVVMTheme {
        UserCard(u1) { }
    }

}