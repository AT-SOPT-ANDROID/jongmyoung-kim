package org.sopt.at.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalAtSoptColors = staticCompositionLocalOf<AtSoptColors> {
    error("No AtSoptColors provided")
}

private val LocalAtSoptTypography = staticCompositionLocalOf<AtSoptTypography> {
    error("No AtSoptTypography provided")
}

object AtSoptTheme {
    val colors: AtSoptColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAtSoptColors.current
    val typography: AtSoptTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAtSoptTypography.current
}

@Composable
fun ProvideAtSoptColors(
    colors: AtSoptColors,
    typography: AtSoptTypography,
    content: @Composable () -> Unit,
) {
    val provideColors = remember { colors.copy() }.apply { update(colors) }
    CompositionLocalProvider(
        LocalAtSoptColors provides provideColors,
        LocalAtSoptTypography provides typography,
        content = content,
    )
}

@Composable
fun ATSOPTANDROIDTheme(
    content: @Composable () -> Unit,
) {
    val colors = AtSoptLightColors()
    val typography = AtSoptTypography()

    ProvideAtSoptColors(
        colors = colors,
        typography = typography,
    ) {
        MaterialTheme(
            content = content,
        )
    }
}
