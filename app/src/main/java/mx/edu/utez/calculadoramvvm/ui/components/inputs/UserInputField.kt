package com.example.myapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.edu.utez.gato.viewmodel.LoginViewModel

@Composable
fun UserInputField(viewModel: LoginViewModel, label: String = "Usuario") {
    OutlinedTextField(
        value = viewModel.username.value,
        onValueChange = { viewModel.username.value = it },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}