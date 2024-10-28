package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.wordle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.WordleTile
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.checkGuess
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun WordleTiles(
    guess: List<WordleTile>,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        for (wordleTile in guess) {
            WordleTile(wordleTile = wordleTile)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HangmanGameTheme {
        WordleTiles(
            guess = checkGuess(
                wordToGuess = "QUEEF",
                guess = "QUEUE",
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun CorrectPreview() {
    HangmanGameTheme {
        WordleTiles(
            guess = checkGuess(
                wordToGuess = "QUEUE",
                guess = "QUEUE",
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun LongPreview() {
    HangmanGameTheme {
        WordleTiles(
            guess = checkGuess(
                wordToGuess = "Thickskinned",
                guess = "Thickskinned",
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}