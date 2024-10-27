package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.OngoingGameScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme


@Composable
fun PlayScreen(
    gameState: GameState,
    currentGuess: String,
    eventHandler: PlayEventHandler,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when (gameState) {
            is OngoingGameState -> OngoingGameScreen(
                gameState = gameState,
                currentGuess = currentGuess,
                eventHandler = eventHandler,
            )

            is GameLostState -> GameLostScreen(
                gameState = gameState,
                eventHandler = eventHandler,
            )

            is GameWonState -> GameWonScreen(eventHandler = eventHandler)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val enemy = Enemies.first()
    val wordToGuess = enemy.words.random().uppercase()

    val gameState = OngoingGameState(
        level = 1,
        enemy = enemy,
        health = 100,
        wordsGuessed = emptyList(),
        wordToGuess = wordToGuess,
        guesses = emptyList(),
    )

    HangmanGameTheme {
        PlayScreen(
            gameState = gameState,
            currentGuess = "",
            eventHandler = PlayEventHandler(
                onCurrentGuessChange = {},
                onGuessSubmit = {},
                onPlayAgain = {},
                onGoToMainMenu = {},
            )
        )
    }
}