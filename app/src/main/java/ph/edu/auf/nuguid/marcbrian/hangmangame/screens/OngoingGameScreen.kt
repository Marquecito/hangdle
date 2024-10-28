package ph.edu.auf.nuguid.marcbrian.hangmangame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ph.edu.auf.nuguid.marcbrian.hangmangame.R
import ph.edu.auf.nuguid.marcbrian.hangmangame.components.StrongButton
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.Enemies
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.HealthBar
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.OngoingGameState
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.PlayEventHandler
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.WordleTile
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.wordle.WordleTiles
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun OngoingGameScreen(
    gameState: OngoingGameState,
    currentGuess: String,
    eventHandler: PlayEventHandler,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Box {
            TextField(
                value = currentGuess,
                onValueChange = eventHandler.onCurrentGuessChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions {
                    eventHandler.onGuessSubmit()
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .align(Alignment.Center),
            )
            Image(
                painter = painterResource(id = R.drawable.endbg),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            with(gameState) {
                Column(
                    modifier = Modifier
                        .imePadding()
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState(), reverseScrolling = true)
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
                    HealthBar(
                        health = enemyHealth,
                        maxHealth = enemy.maxHealth,
                        modifier = Modifier.padding(8.dp),
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
                                .clickable {
                                    focusRequester.requestFocus()
                                    keyboard?.show()
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
                            text = "Your HP: $health",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(focusRequester) {
        delay(100.milliseconds)
        focusRequester.requestFocus()
    }
}


@Preview
@Composable
private fun Preview() {
    val enemy = Enemies.first()
    val wordToGuess = enemy.words.random().uppercase()

    val gameState = OngoingGameState(
        level = 1,
        health = 100,
        enemy = enemy,
        enemyHealth = enemy.maxHealth,
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
