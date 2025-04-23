package org.sopt.at.feature.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.R.drawable.ic_tving_original
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme

@Composable
fun HomeSwipeBanner(
    bannerUrls: ImmutableList<Pair<String, String>>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp),
        beyondViewportPageCount = 1,
        pageSpacing = 12.dp,
    ) {
        Box(
            modifier = Modifier,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(bannerUrls[it].first)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .aspectRatio(.8f),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 20.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(ic_tving_original),
                    contentDescription = null,
                    modifier = Modifier,
                    tint = Color.Unspecified,
                )

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(bannerUrls[it].second)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeSwipeBannerPreview() {
    ATSOPTANDROIDTheme {
        HomeSwipeBanner(
            bannerUrls = persistentListOf(),
            pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { 5 }
            ),
        )
    }
}
