package ph.edu.auf.nuguid.marcbrian.hangmangame.misc

import android.content.Context

class GamePreferences(context: Context) {

    private val prefs = context.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)

    fun savePlayerStats(stats: PlayerStats) {
        prefs.edit()
            .putInt("player_level", stats.level)
            .putInt("player_xp", stats.xp)
            .apply()

    }

    fun loadPlayerStats(): PlayerStats {
        val level = prefs.getInt("player_level", 1)
        val xp = prefs.getInt("player_xp", 0)
        return PlayerStats(level, xp)

    }
}