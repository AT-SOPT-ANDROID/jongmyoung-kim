package org.sopt.at.core.designsystem.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.NoRippleInteractionSource

@Composable
fun AtSoptDefaultButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    contentColor: Color = AtSoptTheme.colors.gray200,
    containerColor: Color = AtSoptTheme.colors.gray400,
    borderColor: Color = AtSoptTheme.colors.gray400,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor,
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = PaddingValues(vertical = 12.dp),
        interactionSource = NoRippleInteractionSource,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@Preview
@Composable
private fun AtSoptDefaultButtonPreview() {
    ATSOPTANDROIDTheme {
        Column {
            val buttonEnabled = false

            AtSoptDefaultButton(
                title = "로그인하기",
                onClick = { },
                enabled = buttonEnabled,
                containerColor = if (buttonEnabled) AtSoptTheme.colors.primary else AtSoptTheme.colors.gray400,
                contentColor = if (buttonEnabled) AtSoptTheme.colors.white else AtSoptTheme.colors.gray200
            )
            AtSoptDefaultButton(
                title = "다음",
                onClick = { },
                contentColor = AtSoptTheme.colors.white,
                containerColor = AtSoptTheme.colors.black,
                borderColor = AtSoptTheme.colors.gray100,
            )
        }
    }
}
