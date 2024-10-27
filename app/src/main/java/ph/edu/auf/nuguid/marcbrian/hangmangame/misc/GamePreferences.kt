package ph.edu.auf.nuguid.marcbrian.hangmangame.misc

import android.content.Context
import com.google.gson.Gson

class GamePreferences(context: Context) {
    private val gson = Gson()
    private val prefs = context.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)

    fun addHighScore(highScore: HighScore) {
        val highScores = getHighScores() + highScore
        prefs.edit()
            .putString("high_scores", gson.toJson(highScores))
            .apply()
    }

    fun getHighScores(): List<HighScore> {
        val highScores = prefs.getString("high_scores", null)
        return if (highScores != null)
            gson.fromJson(highScores, Array<HighScore>::class.java).toList()
        else emptyList()
    }
}