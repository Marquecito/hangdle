package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.highscore

import androidx.lifecycle.ViewModel
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.GamePreferences

class HighScoreViewModel(
    private val gamePreferences: GamePreferences,
) : ViewModel() {
    val highScores = gamePreferences.getHighScores().sortedBy { it.solvedWords }
}