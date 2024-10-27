package ph.edu.auf.nuguid.marcbrian.hangmangame.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.Screen
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.ScreenRoutes
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.OngoingGameState
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayEventHandler
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayViewModel


@Composable
fun MainScreen() {
    val items = listOf(Screen.Play, Screen.QuestLog)
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (navController.currentBackStackEntry?.destination?.route in items.map { it.route }) {


                NavigationBar {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = false,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = ScreenRoutes.Start.route
        ) {
            composable(ScreenRoutes.Start.route) {
                StartScreen(onPlay = { navController.navigate(ScreenRoutes.Play.route) })
            }
            composable(ScreenRoutes.Play.route) {
                val viewModel = viewModel { PlayViewModel() }
                with(viewModel) {
                    val gameState by gameState.collectAsStateWithLifecycle()
                    val currentGuess by currentGuess.collectAsStateWithLifecycle()

                    PlayScreen(
                        gameState = gameState,
                        currentGuess = currentGuess,
                        eventHandler = PlayEventHandler(
                            onCurrentGuessChange = ::onCurrentGuessChange,
                            onGuessSubmit = ::onGuessSubmit,
                            onPlayAgain = ::resetGame,
                            onGoToMainMenu = { navController.navigate(ScreenRoutes.Start.route) },
                        )
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        MainScreen()
    }
}
