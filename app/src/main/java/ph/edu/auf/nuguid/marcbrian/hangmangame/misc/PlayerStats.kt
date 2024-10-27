package ph.edu.auf.nuguid.marcbrian.hangmangame.misc

    data class Quest(val Id: Int, val description: String, val completed: Boolean)

    data class PlayerStats(
        var level: Int = 1,
        var xp: Int = 0,

    )
