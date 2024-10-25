package com.example.musicplayer.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.musicplayer.constant.HomeScreen
import com.example.musicplayer.constant.MainGraph
import com.example.musicplayer.constant.PlayerScreen
import com.example.musicplayer.model.SerializableAudio
import com.example.musicplayer.presentation.screen.HomeScreenRoot
import com.example.musicplayer.presentation.screen.PlayerScreenRoot
import com.example.musicplayer.presentation.viewmodel.PlayerViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Composable
fun RootNavigationGraph() {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController, startDestination = HomeScreen, route = MainGraph::class
    ) {
        composable<HomeScreen> { backStackEntry ->
            val playerViewModel =
                backStackEntry.sharedViewModel<PlayerViewModel>(navController = rootNavController)
            HomeScreenRoot(playerViewModel = playerViewModel, navigateToPlayerScreen = { audio ->
                rootNavController.navigate(PlayerScreen(audio))
            })
        }

        composable<PlayerScreen>(
            typeMap = mapOf(typeOf<SerializableAudio>() to serializableAudioType)
        ) { backStackEntry ->
            val playerViewModel =
                backStackEntry.sharedViewModel<PlayerViewModel>(navController = rootNavController)
            val args = backStackEntry.toRoute<PlayerScreen>()
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

val serializableAudioType = object : NavType<SerializableAudio>(false) {
    override fun get(bundle: Bundle, key: String): SerializableAudio? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): SerializableAudio {
        return Json.decodeFromString<SerializableAudio>(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: SerializableAudio) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun serializeAsValue(value: SerializableAudio): String {
        return Uri.encode(Json.encodeToString(value))
    }


}