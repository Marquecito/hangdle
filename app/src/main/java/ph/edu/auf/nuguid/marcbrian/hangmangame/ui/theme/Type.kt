package ph.edu.auf.nuguid.marcbrian.hangmangame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ph.edu.auf.nuguid.marcbrian.hangmangame.R

private val displayFont = FontFamily(
    Font(R.font.personaclown, FontWeight.Normal)
)

private val bodyFont = FontFamily(
    Font(R.font.personanormal, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = displayFont,
        fontSize = 56.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = displayFont,
        fontSize = 48.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = bodyFont,
        fontSize = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = bodyFont,
        fontSize = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = bodyFont,
        fontWeight = FontWeight.Normal,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)