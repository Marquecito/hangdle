package ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = White,
    onSecondary = Black,
    tertiary = Red,
    onTertiary = White,
    tertiaryContainer = LightRed,
)

@Composable
fun HangmanGameTheme(content: @Composable () -> Unit) {
    val colorScheme = DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity  = view.context as Activity
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}