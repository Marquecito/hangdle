package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.highscore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.HighScore
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun HighScore(
    rank: Int,
    highScore: HighScore,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
        ) {
            Surface(
                color = when (rank) {
                    1 -> MaterialTheme.colorScheme.tertiary
                    else -> MaterialTheme.colorScheme.secondary
                },
            ) {
                Box(modifier = Modifier.size(32.dp)) {
                    Text(
                        text = "$rank",
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Level: ${highScore.level}")
                Text(text = "Words solved: ${highScore.solvedWords}")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HangmanGameTheme {
        HighScore(
            rank = 1,
            highScore = HighScore(level = 1, solvedWords = 1),
            modifier = Modifier.padding(16.dp),
        )
    }
}