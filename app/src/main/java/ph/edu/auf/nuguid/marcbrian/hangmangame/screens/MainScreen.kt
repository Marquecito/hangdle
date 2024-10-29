package ph.edu.auf.nuguid.marcbrian.hangmangame.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ph.edu.auf.nuguid.marcbrian.hangmangame.gamePreferences
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.NavBarItem
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.ScreenRoute
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.highscore.HighScoreScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.highscore.HighScoreViewModel
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayEventHandler
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayViewModel


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            val navBarItems = NavBarItem.entries
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute in navBarItems.map { it.route.id }) {
                NavigationBar(
                    containerColor = Color.Transparent,
                ) {
                    navBarItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = screen.route.id == currentRoute,
                            onClick = {
                                navController.navigate(screen.route.id) {
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
        println("Ignored: $innerPadding")
        NavHost(
            navController = navController,
            startDestination = ScreenRoute.Start.id
        ) {
            composable(ScreenRoute.Start.id) {
                StartScreen(onPlay = { navController.navigate(ScreenRoute.Play.id) })
            }
            composable(ScreenRoute.Play.id) {
                val viewModel = viewModel {
                    PlayViewModel(gamePreferences)
                }
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
                            onGoToMainMenu = { navController.navigate(ScreenRoute.Start.id) },
                        ),
                    )
                }
            }

            composable(ScreenRoute.HighScore.id) {
                val viewModel = viewModel {
                    HighScoreViewModel(gamePreferences)
                }

                HighScoreScreen(
                    highScores = viewModel.highScores,
                )
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
