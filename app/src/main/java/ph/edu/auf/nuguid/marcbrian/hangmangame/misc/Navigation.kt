package ph.edu.auf.nuguid.marcbrian.hangmangame.misc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val route: String, val title: String, val icon: ImageVector) {
    Play(
        ScreenRoutes.Play.route,
        "Play",
        Icons.Filled.PlayArrow
    ),
    QuestLog(
        ScreenRoutes.QuestLog.route,
        "Quest Log",
        Icons.AutoMirrored.Filled.List
    )

}