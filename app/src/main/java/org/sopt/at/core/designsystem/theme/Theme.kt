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

object AtSoptTheme {
    val colors: AtSoptColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAtSoptColors.current
}

@Composable
fun ProvideAtSoptColors(
    colors: AtSoptColors,
    content: @Composable () -> Unit,
) {
    val provideColors = remember { colors.copy() }.apply { update(colors) }
    CompositionLocalProvider(
        LocalAtSoptColors provides provideColors,
        content = content,
    )
}

@Composable
fun ATSOPTANDROIDTheme(
    content: @Composable () -> Unit,
) {
    val colors = AtSoptLightColors()

    ProvideAtSoptColors(
        colors = colors,
    ) {
        MaterialTheme(
            typography = Typography,
            content = content,
        )
    }
}
