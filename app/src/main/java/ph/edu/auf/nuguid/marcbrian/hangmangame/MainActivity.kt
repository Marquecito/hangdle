package ph.edu.auf.nuguid.marcbrian.hangmangame

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ph.edu.auf.nuguid.marcbrian.hangmangame.screens.MainScreen
import ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme.HangmanGameTheme

class MainActivity : ComponentActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangmanGameTheme {
                MainScreen()
            }
        }

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.beneath_the_mask)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}

