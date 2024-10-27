package ph.edu.auf.nuguid.marcbrian.hangmangame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ph.edu.auf.nuguid.marcbrian.hangmangame.R
import ph.edu.auf.nuguid.marcbrian.hangmangame.components.StrongButton
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.ScreenRoutes
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme


val CustomFont = FontFamily(
    Font(R.font.personaclown, FontWeight.Normal)
)



@Composable
fun StartScreen(
    onPlay: () -> Unit,
) {
    Image(
        painter = painterResource(id = R.drawable.startbg),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "hanged man",
            style = MaterialTheme.typography.displaySmall,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "arcana",
            style = MaterialTheme.typography.displayMedium,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(100.dp))

        StrongButton(
            text = "Start the day",
            onClick = onPlay,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    HangmanGameTheme {
        StartScreen(onPlay = {})
    }
}