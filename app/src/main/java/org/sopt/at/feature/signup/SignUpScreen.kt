package org.sopt.at.feature.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.at.R.drawable.ic_back
import org.sopt.at.R.string.id_text
import org.sopt.at.R.string.next
import org.sopt.at.R.string.nickname_text
import org.sopt.at.R.string.password_text
import org.sopt.at.R.string.sign_up_error
import org.sopt.at.R.string.sign_up_id_description
import org.sopt.at.R.string.sign_up_id_error
import org.sopt.at.R.string.sign_up_id_title
import org.sopt.at.R.string.sign_up_nickname_description
import org.sopt.at.R.string.sign_up_nickname_error
import org.sopt.at.R.string.sign_up_nickname_title
import org.sopt.at.R.string.sign_up_password_description
import org.sopt.at.R.string.sign_up_password_error
import org.sopt.at.R.string.sign_up_password_title
import org.sopt.at.R.string.sign_up_success
import org.sopt.at.core.designsystem.common.AtSoptDefaultButton
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.feature.signup.component.SignUpContent
import org.sopt.at.feature.signup.state.SignUpSideEffect
import org.sopt.at.feature.signup.state.SignUpState

@Composable
fun SignUpRoute(
    navigateToSignIn: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    BackHandler {
        if (uiState.page == 0) navigateUp() else viewModel.updatePage(-1)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    SignUpSideEffect.SignUpSucceed -> {
                        Toast.makeText(context, getString(context, sign_up_success), Toast.LENGTH_SHORT).show()
                        navigateToSignIn()
                    }

                    SignUpSideEffect.SignUpFailed ->
                        Toast.makeText(context, getString(context, sign_up_error), Toast.LENGTH_SHORT).show()

                    SignUpSideEffect.InvalidId ->
                        Toast.makeText(context, getString(context, sign_up_id_error), Toast.LENGTH_SHORT).show()

                    SignUpSideEffect.InvalidPassword ->
                        Toast.makeText(context, getString(context, sign_up_password_error), Toast.LENGTH_SHORT).show()

                    SignUpSideEffect.InvalidNickname ->
                        Toast.makeText(context, getString(context, sign_up_nickname_error), Toast.LENGTH_SHORT).show()
                }
            }
    }

    SignUpScreen(
        signUpState = uiState,
        onIdChange = viewModel::updateId,
        onPasswordChange = viewModel::updatePassword,
        onNicknameChange = viewModel::updateNickname,
        navigateUp = { if (uiState.page == 0) navigateUp() else viewModel.updatePage(-1) },
        onSignUpClick = viewModel::signUp,
        modifier = modifier,
    )
}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
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

        when (signUpState.page) {
            0 -> SignUpContent(
                page = 0,
                title = stringResource(sign_up_id_title),
                description = stringResource(sign_up_id_description),
                text = signUpState.userId,
                onTextChange = onIdChange,
                hint = stringResource(id_text),
            )

            1 -> SignUpContent(
                page = 1,
                title = stringResource(sign_up_password_title),
                description = stringResource(sign_up_password_description),
                text = signUpState.userPassword,
                onTextChange = onPasswordChange,
                hint = stringResource(password_text),
            )

            2 -> SignUpContent(
                page = 2,
                title = stringResource(sign_up_nickname_title),
                description = stringResource(sign_up_nickname_description),
                text = signUpState.userNickname,
                onTextChange = onNicknameChange,
                hint = stringResource(nickname_text),
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
            signUpState = SignUpState(),
            onIdChange = { },
            onPasswordChange = { },
            onNicknameChange = { },
            navigateUp = { },
            onSignUpClick = { }
        )
    }
}
