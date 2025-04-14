package org.sopt.at.feature.signup.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R.drawable.ic_invisible_24
import org.sopt.at.R.drawable.ic_visible_24
import org.sopt.at.core.designsystem.common.AtSoptDefaultTextField
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable

@Composable
fun SignUpContent(
    page: Int,
    title: String,
    description: String,
    text: String,
    hint: String,
    onTextChange: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight(500),
            color = AtSoptTheme.colors.white,
        ),
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(28.dp))

    AtSoptDefaultTextField(
        text = text,
        onTextChange = onTextChange,
        hint = hint,
        borderColor = AtSoptTheme.colors.gray100,
        visualTransformation = if (isPasswordVisible || page == 0) VisualTransformation.None else PasswordVisualTransformation(),
        suffix =  {
            if (page == 1) {
                Icon(
                    imageVector = ImageVector.vectorResource(if (isPasswordVisible) ic_visible_24 else ic_invisible_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .noRippleClickable {
                        isPasswordVisible = !isPasswordVisible
                    },
                    tint = AtSoptTheme.colors.gray300,
                )
            }
        }
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = description,
        style = MaterialTheme.typography.labelMedium.copy(
            color = AtSoptTheme.colors.gray300,
        )
    )
}

@Preview
@Composable
private fun SignUpContentPreview() {
    ATSOPTANDROIDTheme {
        SignUpContent(
            page = 0,
            title = "title",
            description = "description",
            text = "",
            onTextChange = { },
            hint = "hint"
        )
    }
}
