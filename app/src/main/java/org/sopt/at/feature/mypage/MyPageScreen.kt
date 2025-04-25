package org.sopt.at.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.at.R.string.sign_out
import org.sopt.at.core.designsystem.common.AtSoptDefaultButton
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.feature.mypage.state.MyPageSideEffect

@Composable
fun MyPageRoute(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel()
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
        onSignOutClick = viewModel::signOut,
        modifier = modifier,
    )
}

@Composable
fun MyPageScreen(
    onSignOutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = AtSoptTheme.colors.black),
        contentAlignment = Alignment.Center,
    ) {
        AtSoptDefaultButton(
            title = stringResource(sign_out),
            onClick = onSignOutClick,
        )
    }
}
