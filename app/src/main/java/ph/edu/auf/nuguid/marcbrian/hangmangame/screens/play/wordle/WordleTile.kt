package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.wordle

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.GuessValidation
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play.WordleTile
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun WordleTile(
    wordleTile: WordleTile,
    modifier: Modifier = Modifier,
) {
    val color = when (wordleTile.validation) {
        GuessValidation.Correct -> MaterialTheme.colorScheme.tertiary
        GuessValidation.Present -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.primary
    }

    Surface(
        color = color,
        modifier = modifier,
        border = BorderStroke(
            width = 2.dp,
            color = contentColorFor(color).copy(alpha = 0.4f),
        ),
    ) {
        Box(modifier = Modifier.size(40.dp)) {
            if (wordleTile.letter != null) {
                Text(
                    text = wordleTile.letter.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 24.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CorrectPreview() {
    HangmanGameTheme {
        WordleTile(
            wordleTile = WordleTile(letter = 'A', validation = GuessValidation.Correct),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun PresentPreview() {
    HangmanGameTheme {
        WordleTile(
            wordleTile = WordleTile(letter = 'A', validation = GuessValidation.Present),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun AbsentPreview() {
    HangmanGameTheme {
        WordleTile(
            wordleTile = WordleTile(letter = 'A', validation = GuessValidation.Absent),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun NullPreview() {
    HangmanGameTheme {
        WordleTile(
            wordleTile = WordleTile(letter = null, validation = null),
            modifier = Modifier.padding(16.dp),
        )
    }
}