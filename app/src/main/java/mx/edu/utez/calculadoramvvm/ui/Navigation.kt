package mx.edu.utez.calculadoramvvm.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.calculadoramvvm.ui.screens.CalculatorScreen
import mx.edu.utez.calculadoramvvm.ui.screens.ForgotPasswordScreen
import mx.edu.utez.calculadoramvvm.ui.screens.HomeScreen
import mx.edu.utez.calculadoramvvm.ui.screens.IncubadoraScreen
import mx.edu.utez.calculadoramvvm.ui.screens.LoginScreen
import mx.edu.utez.calculadoramvvm.ui.screens.RegisterScreen
import mx.edu.utez.calculadoramvvm.ui.screens.TicTacToeScreen
import mx.edu.utez.calculadoramvvm.ui.screens.UserViewScreen
import mx.edu.utez.calculadoramvvm.viewmodel.CalculatorViewModel
import mx.edu.utez.calculadoramvvm.viewmodel.IncubadoraViewModel
import mx.edu.utez.calculadoramvvm.viewmodel.TicTacToeViewModel
import mx.edu.utez.gato.viewmodel.LoginViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "incubadora") {
        composable("login") {
            val viewModel: LoginViewModel = viewModel() // instancia del ViewModel
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("calculator") {
            val viewModel: CalculatorViewModel = viewModel() // instancia del ViewModel
            CalculatorScreen(viewModel = viewModel, navController = navController)
        }
        composable("tictactoe") {
            val viewModel: TicTacToeViewModel = viewModel() // instancia del ViewModel
            TicTacToeScreen(viewModel = viewModel, navController = navController)
        }
        composable("incubadora") {
            val viewModel: IncubadoraViewModel = viewModel() // instancia del ViewModel
            IncubadoraScreen(viewModel = viewModel, navController = navController)
        }
        composable("usuarios") {
            UserViewScreen(navController = navController)
        }
    }
}