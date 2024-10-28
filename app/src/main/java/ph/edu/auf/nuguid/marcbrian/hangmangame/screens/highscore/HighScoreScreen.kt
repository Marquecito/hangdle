package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.highscore

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.HighScore
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun HighScoreScreen(
    highScores: List<HighScore>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(highScores) { index, highScore ->
            HighScore(rank = index + 1, highScore = highScore)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HangmanGameTheme {
        HighScoreScreen(
            highScores = Array(100) { index ->
                HighScore(
                    level = 100 - index,
                    solvedWords = (100 - index) * 3,
                )
            }.toList()
        )
    }
}