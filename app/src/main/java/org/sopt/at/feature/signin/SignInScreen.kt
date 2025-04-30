package org.sopt.at.feature.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.at.R.drawable.ic_back
import org.sopt.at.R.drawable.ic_invisible_24
import org.sopt.at.R.drawable.ic_visible_24
import org.sopt.at.R.string.id_text
import org.sopt.at.R.string.password_text
import org.sopt.at.R.string.sign_in_button
import org.sopt.at.R.string.sign_in_error
import org.sopt.at.R.string.sign_in_success
import org.sopt.at.R.string.sign_in_title
import org.sopt.at.core.designsystem.common.AtSoptDefaultButton
import org.sopt.at.core.designsystem.common.AtSoptDefaultTextField
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.feature.signin.component.AccountManagementGroup
import org.sopt.at.feature.signin.component.SignInFooter
import org.sopt.at.feature.signin.state.SignInSideEffect
import org.sopt.at.feature.signin.state.SignInState

@Composable
fun SignInRoute(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    SignInSideEffect.SignInSucceed -> navigateToHome()

                    SignInSideEffect.SignInFailed ->
                        Toast.makeText(context, getString(context, sign_in_error), Toast.LENGTH_SHORT).show()
                }
            }
    }

    SignInScreen(
        signInState = uiState,
        onIdChange = viewModel::updateId,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        checkButtonEnabled = viewModel::checkButtonEnabled,
        navigateToSignUp = navigateToSignUp,
        modifier = modifier,
    )
}

@Composable
fun SignInScreen(
    signInState: SignInState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    checkButtonEnabled: () -> Boolean,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = { },
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isSignInButtonEnabled = remember(
        signInState.userId,
        signInState.userPassword,
    ) { checkButtonEnabled() }
    val containerColor =
        if (isSignInButtonEnabled) AtSoptTheme.colors.primary else AtSoptTheme.colors.gray400
    val contentColor =
        if (isSignInButtonEnabled) AtSoptTheme.colors.white else AtSoptTheme.colors.gray200

    val focusManager = LocalFocusManager.current


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

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(sign_in_title),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(500),
                color = AtSoptTheme.colors.white,
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptDefaultTextField(
            text = signInState.userId,
            onTextChange = onIdChange,
            hint = stringResource(id_text),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
        )

        Spacer(modifier = Modifier.height(12.dp))

        AtSoptDefaultTextField(
            text = signInState.userPassword,
            onTextChange = onPasswordChange,
            hint = stringResource(password_text),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            suffix = {
                Icon(
                    imageVector = ImageVector.vectorResource(if (isPasswordVisible) ic_visible_24 else ic_invisible_24),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable {
                        isPasswordVisible = !isPasswordVisible
                    },
                    tint = AtSoptTheme.colors.gray300,
                )
            },
        )

        Spacer(modifier = Modifier.height(20.dp))

        AtSoptDefaultButton(
            title = stringResource(sign_in_button),
            containerColor = containerColor,
            contentColor = contentColor,
            enabled = isSignInButtonEnabled,
            onClick = onSignInClick,
        )

        Spacer(modifier = Modifier.height(36.dp))

        AccountManagementGroup(
            navigateToSignUp = navigateToSignUp,
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignInFooter()
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    ATSOPTANDROIDTheme {
        SignInScreen(
            signInState = SignInState(),
            onIdChange = { },
            onPasswordChange = { },
            onSignInClick = { },
            checkButtonEnabled = { true },
            navigateToSignUp = { },
        )
    }
}
