package org.sopt.at.feature.history.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.NoRippleInteractionSource

@Composable
fun HistoryFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    var isExpanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 90f else 0f,
        animationSpec = tween(durationMillis = 300),
    )

    FloatingActionButton(
        onClick = {
            isExpanded = !isExpanded
            onClick()
        },
        modifier = modifier,
        shape = CircleShape,
        containerColor = AtSoptTheme.colors.gray300,
        contentColor = AtSoptTheme.colors.white,
        interactionSource = NoRippleInteractionSource,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier
                .rotate(rotation)
        )
    }
}

@Preview
@Composable
private fun HistoryFabPreview() {
    ATSOPTANDROIDTheme {
        HistoryFab()
    }
}
