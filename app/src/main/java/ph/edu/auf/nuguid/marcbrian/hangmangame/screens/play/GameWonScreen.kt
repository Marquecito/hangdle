package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.auf.nuguid.marcbrian.hangmangame.components.StrongButton
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

@Composable
fun GameWonScreen(
    eventHandler: PlayEventHandler,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "You won!",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                fontSize = 56.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            StrongButton(
                text = "Play Again",
                onClick = eventHandler.onPlayAgain,
                borderColor = MaterialTheme.colorScheme.tertiaryContainer,
            )
            Spacer(modifier = Modifier.height(8.dp))
            StrongButton(
                text = "Main menu",
                onClick = eventHandler.onGoToMainMenu,
                borderColor = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Preview
@Composable
private fun GameLostState() {
    HangmanGameTheme {
        GameWonScreen(
            eventHandler = PlayEventHandler(
                onCurrentGuessChange = {},
                onGuessSubmit = {},
                onPlayAgain = {},
                onGoToMainMenu = {},
            )
        )
    }
}