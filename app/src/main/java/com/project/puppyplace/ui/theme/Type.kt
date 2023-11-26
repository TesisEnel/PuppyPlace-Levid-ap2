package com.project.puppyplace.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.project.puppyplace.R

private val font = FontFamily(
    Font(R.font.aneklatinsemibold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = font,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = font,
    ),
    displayLarge = TextStyle(
        fontFamily = font,
        fontSize = 54.sp
    ),
    displayMedium = TextStyle(
        fontFamily = font,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = font,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = font,
    ),
    headlineSmall = TextStyle(
        fontFamily = font,
    ),
    titleMedium = TextStyle(
        fontFamily = font,
    ),
    titleSmall = TextStyle(
        fontFamily = font,
    ),
    bodyMedium = TextStyle(
        fontFamily = font,

    ),
    bodySmall = TextStyle(
        fontFamily = font,
        fontSize = 26.sp
    ),
    labelLarge = TextStyle(
        fontFamily = font,
    ),
    labelMedium = TextStyle(
        fontFamily = font,
    ),
    displaySmall = TextStyle(
        fontFamily = font,
        fontSize = 40.sp
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