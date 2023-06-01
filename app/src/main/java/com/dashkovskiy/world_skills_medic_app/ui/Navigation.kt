package com.dashkovskiy.world_skills_medic_app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dashkovskiy.world_skills_medic_app.BottomNavigation
import com.dashkovskiy.world_skills_medic_app.ui.analyzes.AnalyzesScreen
import com.dashkovskiy.world_skills_medic_app.ui.code.CodeInputScreen
import com.dashkovskiy.world_skills_medic_app.ui.email.EmailInputScreen
import com.dashkovskiy.world_skills_medic_app.ui.launch.LaunchScreen
import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardScreen
import com.dashkovskiy.world_skills_medic_app.ui.password.CreatePasswordScreen
import com.dashkovskiy.world_skills_medic_app.ui.patientmap.PatientMapScreen
import com.dashkovskiy.world_skills_medic_app.ui.profile.ProfileScreen
import com.dashkovskiy.world_skills_medic_app.ui.results.ResultsScreen
import com.dashkovskiy.world_skills_medic_app.ui.support.SupportScreen
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
    object PatientMap : Nav("patient_map")

    //main nav graph with bottom navigation
    object Main : Nav("main_graph")
    object Analyzes : Nav("analyzes")
    object Results : Nav("results")
    object Support : Nav("support")
    object Profile : Nav("profile")
}

val mainGraphRoutes = listOf(
    Nav.Analyzes.route,
    Nav.Results.route,
    Nav.Support.route,
    Nav.Profile.route
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val localStorage: LocalStorage = get()

    val backStack by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (backStack?.destination?.route in mainGraphRoutes) {
                BottomNavigation(navHostController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
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
            composable(route = Nav.CreatePassword.route) {
                CreatePasswordScreen {
                    navController.navigate(Nav.PatientMap.route) {
                        popUpTo(Nav.CreatePassword.route) { inclusive = true }
                    }
                }
            }
            composable(route = Nav.PatientMap.route) {
                PatientMapScreen {
                    navController.navigate(Nav.Main.route) {
                        popUpTo(Nav.PatientMap.route) { inclusive = true }
                    }
                }
            }
            navigation(startDestination = Nav.Analyzes.route, route = Nav.Main.route) {
                composable(Nav.Analyzes.route) {
                    AnalyzesScreen()
                }
                composable(Nav.Results.route) {
                    ResultsScreen()
                }
                composable(Nav.Support.route) {
                    SupportScreen()
                }
                composable(Nav.Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}