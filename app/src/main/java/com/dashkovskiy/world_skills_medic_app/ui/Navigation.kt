package com.dashkovskiy.world_skills_medic_app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dashkovskiy.world_skills_medic_app.ui.code.CodeInputScreen
import com.dashkovskiy.world_skills_medic_app.ui.email.EmailInputScreen
import com.dashkovskiy.world_skills_medic_app.ui.launch.LaunchScreen
import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardScreen
import com.dashkovskiy.world_skills_medic_app.ui.utils.LocalStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.get

sealed class Nav(val route: String) {
    object Launch : Nav("launch")
    object Onboard : Nav("onboard")
    object EmailInput : Nav("email_input")
    object CodeInput : Nav("code_input/{email}")
    object CreatePassword : Nav("create_password")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val localStorage: LocalStorage = get()

    NavHost(
        navController = navController,
        startDestination = Nav.Launch.route
    ) {
        composable(route = Nav.Launch.route) {
            LaunchScreen {
                runBlocking {
                    val flag = localStorage.onboardFinishFlag().first()
                    if (flag) {
                        navController.navigate(Nav.EmailInput.route) {
                            popUpTo(Nav.Launch.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Nav.Onboard.route) {
                            popUpTo(Nav.Launch.route) { inclusive = true }
                        }
                    }
                }
            }
        }
        composable(route = Nav.Onboard.route) {
            OnboardScreen {
                navController.navigate(Nav.EmailInput.route) {
                    popUpTo(Nav.Onboard.route) { inclusive = true }
                }
            }
        }
        composable(route = Nav.EmailInput.route) {
            EmailInputScreen { email ->
                navController.navigate("code_input/$email")
            }
        }
        composable(
            route = Nav.CodeInput.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { entry ->
            val email = entry.arguments?.getString("email") ?: ""
            CodeInputScreen(
                email = email,
                navigateBack = {
                    navController.navigate(Nav.EmailInput.route) {
                        popUpTo(Nav.CodeInput.route) { inclusive = true }
                    }
                },
                navigateToCreatePasswordScreen = {
                    navController.navigate(Nav.CreatePassword.route)
                }
            )
        }
    }
}