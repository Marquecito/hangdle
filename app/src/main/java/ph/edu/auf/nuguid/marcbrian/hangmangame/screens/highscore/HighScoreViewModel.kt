package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.highscore

import androidx.lifecycle.ViewModel
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.GamePreferences

class HighScoreViewModel(
    gamePreferences: GamePreferences,
) : ViewModel() {
    val highScores = gamePreferences.getHighScores().sortedByDescending { it.solvedWords }
}