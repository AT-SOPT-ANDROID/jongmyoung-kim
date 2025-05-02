package org.sopt.at.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.R.string.see_more
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable

@Composable
fun HomeSmallBanner(
    title: String,
    onMoreClick: () -> Unit,
    smallBannerUrls: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = AtSoptTheme.typography.title22sb.copy(
                    color = AtSoptTheme.colors.white,
                ),
            )
            Text(
                text = stringResource(see_more),
                style = AtSoptTheme.typography.body16m.copy(
                    color = AtSoptTheme.colors.gray300,
                ),
                modifier = Modifier.noRippleClickable(onMoreClick),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(
                items = smallBannerUrls,
                key = { index, _ -> index },
            ) { index, bannerUrl ->
                val startPadding = if (index == 0) 20.dp else 0.dp
                val endPadding = if (index == smallBannerUrls.lastIndex) 20.dp else 0.dp

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(bannerUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = startPadding, end = endPadding)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeSmallBannerPreview() {
    ATSOPTANDROIDTheme {
        HomeSmallBanner(
            title = "지금 방영중인 콘텐츠",
            onMoreClick = { },
            smallBannerUrls = persistentListOf(),
        )
    }
}
