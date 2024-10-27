package ph.edu.auf.nuguid.marcbrian.hangmangame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.nuguid.marcbrian.hangmangame.R
import ph.edu.auf.nuguid.marcbrian.hangmangame.components.StrongButton
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.Enemies
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.OngoingGameState
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayEventHandler
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.WordleTile
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.wordle.WordleTiles
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun OngoingGameScreen(
    gameState: OngoingGameState,
    currentGuess: String,
    eventHandler: PlayEventHandler,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Image(
            painter = painterResource(id = R.drawable.endbg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        with(gameState) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Level: $level",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "Enemy: ${enemy.name}",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "Health: ${enemy.health}",
                    style = MaterialTheme.typography.bodyLarge,
                )

                Spacer(modifier = Modifier.height(80.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (guess in guesses) {
                        WordleTiles(guess = guess)
                    }

                    val currentGuessTiles = Array(wordToGuess.length) {
                        WordleTile(letter = currentGuess.getOrNull(it))
                    }.toList()
                    WordleTiles(
                        guess = currentGuessTiles,
                        modifier = Modifier
                            .focusable()
                            .onKeyEvent {
                                if (it.type == KeyEventType.KeyUp &&
                                    it.key.nativeKeyCode in LetterKeys
                                ) {
                                    val letterIndex = LetterKeys.indexOf(it.key.nativeKeyCode)
                                    val letter = Letters[letterIndex]
                                    eventHandler.onCurrentGuessChange(currentGuess + letter)
                                    true
                                } else if (it.type == KeyEventType.KeyUp &&
                                    it.key == Key.Backspace
                                ) {
                                    eventHandler.onCurrentGuessChange(currentGuess.dropLast(1))
                                    true
                                } else if (it.type == KeyEventType.KeyUp && it.key == Key.Enter) {
                                    eventHandler.onGuessSubmit()
                                    true
                                } else false
                            }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                StrongButton(
                    text = "Submit",
                    onClick = eventHandler.onGuessSubmit,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Surface {
                    Text(
                        text = "Your Health: $health",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    }
}

private val LetterKeys = (29..54).toList()
private val Letters = ('A'..'Z').toList()

@Preview
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
        OngoingGameScreen(
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