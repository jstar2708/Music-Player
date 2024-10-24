package com.example.musicplayer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.musicplayer.constant.HomeScreen
import com.example.musicplayer.constant.PlayerScreen
import com.example.musicplayer.presentation.screen.HomeScreenRoot
import com.example.musicplayer.presentation.screen.PlayerScreenRoot
import com.example.musicplayer.presentation.viewmodel.PlayerViewModel

@Composable
fun RootNavigationGraph() {
    val rootNavController = rememberNavController()
    NavHost(navController = rootNavController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            val playerViewModel =
                it.sharedViewModel<PlayerViewModel>(navController = rootNavController)
            HomeScreenRoot(
                playerViewModel = playerViewModel,
                navigateToPlayerScreen = { audio ->
                rootNavController.navigate(PlayerScreen(audio))
            })
        }

        composable<PlayerScreen> {
            val playerViewModel =
                it.sharedViewModel<PlayerViewModel>(navController = rootNavController)
            val args = it.toRoute<PlayerScreen>()
            PlayerScreenRoot(args = args, playerViewModel)
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel<T>(parentEntry)
}