package ph.edu.auf.nuguid.marcbrian.hangmangame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.MainScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangmanGameTheme {
                MainScreen()
            }
        }
    }
}

