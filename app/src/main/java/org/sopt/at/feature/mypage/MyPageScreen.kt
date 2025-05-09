package org.sopt.at.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.at.R.string.sign_out
import org.sopt.at.core.designsystem.common.AtSoptDefaultButton
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
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

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    MyPageSideEffect.SignOutSucceed -> navigateToSignIn()
                }
            }
    }

    MyPageScreen(
        uiState = uiState,
        onSignOutClick = viewModel::signOut,
        modifier = modifier,
    )
}

@Composable
fun MyPageScreen(
    uiState: MyPageState,
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
            onSignOutClick = {},
        )
    }
}
