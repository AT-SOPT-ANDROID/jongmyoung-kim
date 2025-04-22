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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Priority
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.R.drawable.ic_tving_original
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeSwipeBanner(
    bannerUrls: ImmutableList<Pair<String, String>>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
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
            GlideImage(
                model = bannerUrls[it].first,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .aspectRatio(.8f),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter,
                transition = CrossFade,
            ) { requestBuilder ->
                requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
            }

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

                GlideImage(
                    model = bannerUrls[it].second,
                    contentDescription = null,
                    transition = CrossFade,
                ) { requestBuilder ->
                    requestBuilder
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                }
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
