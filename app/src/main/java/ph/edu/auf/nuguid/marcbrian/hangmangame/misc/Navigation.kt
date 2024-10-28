package ph.edu.auf.nuguid.marcbrian.hangmangame.misc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavBarItem(val route: ScreenRoute, val title: String, val icon: ImageVector) {
    Home(
        ScreenRoute.Start,
        "Home",
        Icons.Filled.Home,
    ),
    HighScore(
        ScreenRoute.HighScore,
        "High Score",
        Icons.Filled.EmojiEvents,
    )
}