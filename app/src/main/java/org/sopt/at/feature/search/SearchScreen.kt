package org.sopt.at.feature.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.at.R.drawable.ic_search_selected_24
import org.sopt.at.R.string.search_hint
import org.sopt.at.core.designsystem.common.AtSoptDefaultTextField
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.feature.search.state.SearchContract.SearchState

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    else -> {}
                }
            }
    }

    SearchScreen(
        uiState = uiState,
        searchText = searchText,
        onTextChange = viewModel::updateSearchText,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    uiState: SearchState,
    searchText: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = AtSoptTheme.colors.black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        stickyHeader {
            AtSoptDefaultTextField(
                text = searchText,
                onTextChange = onTextChange,
                hint = stringResource(search_hint),
            )
        }

        item {
            Text(
                text = uiState.searchResult.joinToString("\n"),
                style = AtSoptTheme.typography.body16m.copy(
                    color = AtSoptTheme.colors.white,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    ATSOPTANDROIDTheme {
        SearchScreen(
            uiState = SearchState(),
            searchText = "",
            onTextChange = {},
        )
    }
}
