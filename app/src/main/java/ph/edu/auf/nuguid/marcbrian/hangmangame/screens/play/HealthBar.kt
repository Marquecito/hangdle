package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun HealthBar(
    health: Int,
    maxHealth: Int,
    modifier: Modifier = Modifier,
) {
    val healthRatio by animateFloatAsState(health.toFloat() / maxHealth.toFloat())
    val healthBarColor = MaterialTheme.colorScheme.secondary

    Surface(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .drawBehind {
                    drawRect(
                        color = healthBarColor,
                        size = size.copy(width = size.width * healthRatio)
                    )
                }
        ) {
            Text(
                text = "HP: $health",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HangmanGameTheme {
        HealthBar(health = 5, maxHealth = 10)
    }
}