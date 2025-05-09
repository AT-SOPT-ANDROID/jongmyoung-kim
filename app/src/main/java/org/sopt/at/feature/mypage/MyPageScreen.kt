package org.sopt.at.feature.mypage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.at.R.string.patch_nickname_error
import org.sopt.at.R.string.sign_out
import org.sopt.at.core.designsystem.common.AtSoptDefaultButton
import org.sopt.at.core.designsystem.common.AtSoptDefaultTextField
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.feature.mypage.state.MyPageSideEffect
import org.sopt.at.feature.mypage.state.MyPageState

@Composable
fun MyPageRoute(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    MyPageSideEffect.SignOutSucceed -> navigateToSignIn()
                    MyPageSideEffect.PatchNicknameFailed ->
                        Toast.makeText(context, getString(context, patch_nickname_error), Toast.LENGTH_SHORT).show()
                }
            }
    }

    MyPageScreen(
        uiState = uiState,
        onTextChange = viewModel::updateText,
        onCheckClick = viewModel::patchUserNickname,
        onSignOutClick = viewModel::signOut,
        modifier = modifier,
    )
}

@Composable
fun MyPageScreen(
    uiState: MyPageState,
    onTextChange: (String) -> Unit,
    onCheckClick: () -> Unit,
    onSignOutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AtSoptTheme.colors.black),
        verticalArrangement = Arrangement.Center,
    ) {
        AtSoptDefaultButton(
            title = stringResource(sign_out),
            onClick = onSignOutClick,
        )
        AtSoptDefaultTextField(
            text = uiState.nicknameText,
            onTextChange = onTextChange,
            hint = uiState.userNickname,
            suffix = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = AtSoptTheme.colors.white,
                    modifier = Modifier.noRippleClickable(onCheckClick),
                )
            }
        )
        Text(
            text = uiState.userNickname,
            style = AtSoptTheme.typography.display56b.copy(
                color = AtSoptTheme.colors.white,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    ATSOPTANDROIDTheme {
        MyPageScreen(
            uiState = MyPageState(),
            onTextChange = {},
            onCheckClick = {},
            onSignOutClick = {},
        )
    }
}
