package org.sopt.at.core.designsystem.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme

@Composable
fun AtSoptDefaultTopBar(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.invoke()

        trailingIcon?.invoke()
    }
}

@Preview
@Composable
private fun AtSoptDefaultTopBarPreview() {
    ATSOPTANDROIDTheme {
        AtSoptDefaultTopBar(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
