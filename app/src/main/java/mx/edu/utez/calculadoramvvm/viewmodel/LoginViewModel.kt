package mx.edu.utez.gato.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {
    var password = mutableStateOf("")
    var username = mutableStateOf("")
    var loginError = mutableStateOf("")

    // Función para validar login
    fun login(onSuccess: () -> Unit) {
        if (username.value == "admin" && password.value == "123") {
            loginError.value = ""
            onSuccess()
        } else {
            loginError.value = "Usuario o contraseña incorrectos"
        }
    }
}