package org.sopt.at.core.designsystem.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme

@Composable
fun AtSoptDefaultTextField(
    text: String,
    onTextChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AtSoptTheme.colors.gray500,
    borderColor: Color = AtSoptTheme.colors.gray500,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = AtSoptTheme.colors.white,
    hintTextColor: Color = AtSoptTheme.colors.gray300,
    hintTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    suffix: (@Composable () -> Unit)? = null,
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier
            .background(backgroundColor, MaterialTheme.shapes.extraSmall)
            .border(1.dp, borderColor, MaterialTheme.shapes.extraSmall)
            .padding(12.dp),
        textStyle = textStyle.copy(
            color = textColor,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(textColor),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            style = hintTextStyle.copy(
                                color = hintTextColor,
                            ),
                        )
                    }
                    innerTextField()
                }
                suffix?.invoke()
            }
        }
    )
}

@Preview
@Composable
private fun AtSoptDefaultTextFieldPreview() {
    ATSOPTANDROIDTheme {
        var id by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column {
            AtSoptDefaultTextField(
                text = id,
                onTextChange = { id = it },
                hint = "아이디",
                modifier = Modifier,
            )
            AtSoptDefaultTextField(
                text = password,
                onTextChange = { password = it },
                hint = "비밀번호",
                modifier = Modifier,
                suffix = { }
            )
        }
    }
}
