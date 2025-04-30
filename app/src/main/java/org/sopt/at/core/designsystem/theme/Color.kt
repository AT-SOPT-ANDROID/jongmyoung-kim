package org.sopt.at.core.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

// primary
val primary = Color(0xFFFF153C)

// gray scale
val black = Color(0xFF000000)
val gray600 = Color(0xFF191919)
val gray500 = Color(0xFF262626)
val gray400 = Color(0xFF404040)
val gray300 = Color(0xFF737373)
val gray200 = Color(0xFF808080)
val gray100 = Color(0xFFB3B3B3)
val white = Color(0xFFFFFFFF)

@Stable
class AtSoptColors(
    primary: Color,
    black: Color,
    gray600: Color,
    gray500: Color,
    gray400: Color,
    gray300: Color,
    gray200: Color,
    gray100: Color,
    white: Color,
    isLight: Boolean,
) {
    var primary by mutableStateOf(primary)
        private set
    var black by mutableStateOf(black)
        private set
    var gray600 by mutableStateOf(gray600)
        private set
    var gray500 by mutableStateOf(gray500)
        private set
    var gray400 by mutableStateOf(gray400)
        private set
    var gray300 by mutableStateOf(gray300)
        private set
    var gray200 by mutableStateOf(gray200)
        private set
    var gray100 by mutableStateOf(gray100)
        private set
    var white by mutableStateOf(white)
        private set
    var isLight by mutableStateOf(isLight)

    fun copy(): AtSoptColors = AtSoptColors(
        primary,
        black,
        gray600,
        gray500,
        gray400,
        gray300,
        gray200,
        gray100,
        white,
        isLight,
    )

    fun update(other: AtSoptColors) {
        primary = other.primary
        black = other.black
        gray600 = other.gray600
        gray500 = other.gray500
        gray400 = other.gray400
        gray300 = other.gray300
        gray200 = other.gray200
        gray100 = other.gray100
        white = other.white
        isLight = other.isLight
    }
}

fun AtSoptLightColors(
    Primary: Color = primary,
    Black: Color = black,
    Gray600: Color = gray600,
    Gray500: Color = gray500,
    Gray400: Color = gray400,
    Gray300: Color = gray300,
    Gray200: Color = gray200,
    Gray100: Color = gray100,
    White: Color = white,
) = AtSoptColors(
    Primary,
    Black,
    Gray600,
    Gray500,
    Gray400,
    Gray300,
    Gray200,
    Gray100,
    White,
    isLight = true,
)
