package org.sopt.at.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.R.string.home_large_banner_1
import org.sopt.at.R.string.home_large_banner_2
import org.sopt.at.R.string.home_small_banner_1
import org.sopt.at.R.string.home_small_banner_2
import org.sopt.at.R.string.home_small_banner_3
import org.sopt.at.R.string.home_small_banner_4
import org.sopt.at.core.common.type.HomeGenreType
import org.sopt.at.core.common.type.HomeVodType
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.feature.home.component.HomeRankedBanner
import org.sopt.at.feature.home.component.HomeSmallBanner
import org.sopt.at.feature.home.component.HomeSwipeBanner
import org.sopt.at.feature.home.component.HomeTabRow
import org.sopt.at.feature.home.component.HomeTopBar
import org.sopt.at.feature.home.component.HomeVodButtonRow
import org.sopt.at.feature.home.state.HomeState

@Composable
fun HomeRoute(
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    // TODO: 추후 추가 예정
                    else -> {}
                }
            }
    }

    LaunchedEffect(uiState.selectedTab) {
        viewModel.getHomeBanners()
    }

    HomeScreen(
        homeState = uiState,
        onTabSelect = viewModel::onTabSelect,
        navigateToMyPage = navigateToMyPage,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    homeState: HomeState,
    onTabSelect: (HomeGenreType?) -> Unit,
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)

    LazyColumn(
        modifier = modifier.background(AtSoptTheme.colors.black),
    ) {
        item {
            HomeTopBar(
                profileImageUrl = homeState.userProfileUrl,
                onProfileImageClick = navigateToMyPage,
                modifier = paddedModifier,
                onTvingLogoClick = { onTabSelect(null) },
            )
        }

        stickyHeader {
            HomeTabRow(
                homeTabs = HomeGenreType.entries.toImmutableList(),
                selectedTab = homeState.selectedTab,
                onSelectTab = onTabSelect,
                modifier = Modifier
                    .background(color = AtSoptTheme.colors.black),
            )
        }

        item {
            HomeSwipeBanner(
                bannerUrls = homeState.homeSwipeBannerUrls.toImmutableList(),
            )

            Spacer(modifier = Modifier.height(28.dp))
        }

        item {
            HomeVodButtonRow(
                vodButtons = HomeVodType.entries.toImmutableList(),
                modifier = Modifier,
            )
        }

        item {
            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = stringResource(home_large_banner_1),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = AtSoptTheme.colors.white,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    modifier = paddedModifier,
                )

                Spacer(modifier = Modifier.height(12.dp))

                HomeRankedBanner(
                    rankedBannerUrls = homeState.homeSmallBannerUrls[0].toImmutableList(),
                )
            }
        }

        item {
            HomeSmallBanner(
                title = stringResource(home_small_banner_1),
                onMoreClick = { },
                smallBannerUrls = homeState.homeSmallBannerUrls[1].toImmutableList(),
                modifier = Modifier,
            )
        }

        item {
            HomeSmallBanner(
                title = stringResource(home_small_banner_2),
                onMoreClick = { },
                smallBannerUrls = homeState.homeSmallBannerUrls[2].toImmutableList(),
                modifier = Modifier,
            )
        }

        item {
            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = stringResource(home_large_banner_2),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = AtSoptTheme.colors.white,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    modifier = paddedModifier,
                )

                Spacer(modifier = Modifier.height(12.dp))

                HomeRankedBanner(
                    rankedBannerUrls = homeState.homeSmallBannerUrls[3].toImmutableList(),
                )
            }
        }

        item {
            HomeSmallBanner(
                title = stringResource(home_small_banner_3),
                onMoreClick = { },
                smallBannerUrls = homeState.homeSmallBannerUrls[4].toImmutableList(),
                modifier = Modifier,
            )
        }

        item {
            HomeSmallBanner(
                title = stringResource(home_small_banner_4),
                onMoreClick = { },
                smallBannerUrls = homeState.homeSmallBannerUrls[5].toImmutableList(),
                modifier = Modifier,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ATSOPTANDROIDTheme {
        HomeScreen(
            homeState = HomeState(),
            onTabSelect = { },
            navigateToMyPage = { },
            modifier = Modifier,
        )
    }
}
