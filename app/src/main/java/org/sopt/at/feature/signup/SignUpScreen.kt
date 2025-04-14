package org.sopt.at.feature.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R.drawable.ic_back
import org.sopt.at.R.string.id_text
import org.sopt.at.R.string.next
import org.sopt.at.R.string.password_text
import org.sopt.at.R.string.sign_up_id_description
import org.sopt.at.R.string.sign_up_id_title
import org.sopt.at.R.string.sign_up_password_description
import org.sopt.at.R.string.sign_up_password_title
import org.sopt.at.core.designsystem.common.AtSoptDefaultButton
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.feature.signup.component.SignUpContent

@Composable
fun SignUpScreen(
    id: String,
    onIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    page: Int,
    navigateUp: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AtSoptTheme.colors.black)
            .padding(horizontal = 12.dp, vertical = 20.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(ic_back),
            contentDescription = null,
            modifier = Modifier.noRippleClickable(navigateUp),
            tint = AtSoptTheme.colors.white,
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (page == 0) {
            SignUpContent(
                page = 0,
                title = stringResource(sign_up_id_title),
                description = stringResource(sign_up_id_description),
                text = id,
                onTextChange = onIdChange,
                hint = stringResource(id_text),
            )
        } else {
            SignUpContent(
                page = 1,
                title = stringResource(sign_up_password_title),
                description = stringResource(sign_up_password_description),
                text = password,
                onTextChange = onPasswordChange,
                hint = stringResource(password_text),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        AtSoptDefaultButton(
            title = stringResource(next),
            borderColor = AtSoptTheme.colors.gray300,
            containerColor = AtSoptTheme.colors.black,
            onClick = onSignUpClick,
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    ATSOPTANDROIDTheme {
        SignUpScreen(
            id = "",
            onIdChange = { },
            password = "",
            onPasswordChange = { },
            page = 1,
            navigateUp = { },
            onSignUpClick = { }
        )
    }
}
