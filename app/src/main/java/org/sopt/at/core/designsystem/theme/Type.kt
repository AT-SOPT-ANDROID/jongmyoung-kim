package org.sopt.at.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.at.R.font.pretendard_bold
import org.sopt.at.R.font.pretendard_medium
import org.sopt.at.R.font.pretendard_semibold

val PretendardBold = FontFamily(Font(pretendard_bold))
val PretendardSemiBold = FontFamily(Font(pretendard_semibold))
val PretendardMedium = FontFamily(Font(pretendard_medium))

@Immutable
class AtSoptTypography(
    val display56b: TextStyle,
    val title22sb: TextStyle,
    val title22m: TextStyle,
    val body16m: TextStyle,
    val body14m: TextStyle,
    val label14m: TextStyle,
    val label12m: TextStyle,
    val label10b: TextStyle,
    val label10m: TextStyle,
)

private fun AtSoptTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    lineHeight: TextUnit = 1.28.em,
    letterSpacing: TextUnit = 0.02.em,
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    ),
)

fun AtSoptTypography() = AtSoptTypography(
    display56b = AtSoptTextStyle(
        fontFamily = PretendardBold,
        fontSize = 56.sp
    ),
    title22sb = AtSoptTextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 22.sp
    ),
    title22m = AtSoptTextStyle(
        fontFamily = PretendardMedium,
        fontSize = 22.sp
    ),
    body16m = AtSoptTextStyle(
        fontFamily = PretendardMedium,
        fontSize = 16.sp
    ),
    body14m = AtSoptTextStyle(
        fontFamily = PretendardMedium,
        fontSize = 14.sp
    ),
    label14m = AtSoptTextStyle(
        fontFamily = PretendardMedium,
        fontSize = 14.sp
    ),
    label12m = AtSoptTextStyle(
        fontFamily = PretendardMedium,
        fontSize = 12.sp
    ),
    label10b = AtSoptTextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 10.sp
    ),
    label10m = AtSoptTextStyle(
        fontFamily = PretendardMedium,
        fontSize = 10.sp
    ),
)
