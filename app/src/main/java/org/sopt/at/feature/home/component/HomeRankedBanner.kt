package org.sopt.at.feature.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.R.drawable.ic_rank_eight
import org.sopt.at.R.drawable.ic_rank_eighteen
import org.sopt.at.R.drawable.ic_rank_eleven
import org.sopt.at.R.drawable.ic_rank_fifteen
import org.sopt.at.R.drawable.ic_rank_five
import org.sopt.at.R.drawable.ic_rank_four
import org.sopt.at.R.drawable.ic_rank_fourteen
import org.sopt.at.R.drawable.ic_rank_nine
import org.sopt.at.R.drawable.ic_rank_nineteen
import org.sopt.at.R.drawable.ic_rank_one
import org.sopt.at.R.drawable.ic_rank_seven
import org.sopt.at.R.drawable.ic_rank_seventeen
import org.sopt.at.R.drawable.ic_rank_six
import org.sopt.at.R.drawable.ic_rank_sixteen
import org.sopt.at.R.drawable.ic_rank_ten
import org.sopt.at.R.drawable.ic_rank_thirteen
import org.sopt.at.R.drawable.ic_rank_three
import org.sopt.at.R.drawable.ic_rank_twelve
import org.sopt.at.R.drawable.ic_rank_twenty
import org.sopt.at.R.drawable.ic_rank_two
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme

@Composable
fun HomeRankedBanner(
    rankedBannerUrls: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    val bannerIcons = persistentListOf(
        ic_rank_one,
        ic_rank_two,
        ic_rank_three,
        ic_rank_four,
        ic_rank_five,
        ic_rank_six,
        ic_rank_seven,
        ic_rank_eight,
        ic_rank_nine,
        ic_rank_ten,
        ic_rank_eleven,
        ic_rank_twelve,
        ic_rank_thirteen,
        ic_rank_fourteen,
        ic_rank_fifteen,
        ic_rank_sixteen,
        ic_rank_seventeen,
        ic_rank_eighteen,
        ic_rank_nineteen,
        ic_rank_twenty,
    )

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(
            items = rankedBannerUrls,
            key = { index, _ -> index },
        ) { index, bannerUrl ->
            val startPadding = if (index == 0) 36.dp else 0.dp
            val endPadding = if (index == rankedBannerUrls.lastIndex) 20.dp else 0.dp

            RankedBannerItem(
                bannerUrl = bannerUrl,
                bannerIndexIcon = bannerIcons[index],
                modifier = Modifier.padding(
                    start = startPadding,
                    end = endPadding,
                ),
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun RankedBannerItem(
    bannerUrl: String,
    @DrawableRes bannerIndexIcon: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(bannerIndexIcon),
            contentDescription = null,
            tint = AtSoptTheme.colors.white,
            modifier = Modifier,
        )
        GlideImage(
            model = bannerUrl,
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Fit,
        )
    }
}

@Preview
@Composable
private fun HomeRankedBannerPreview() {
    ATSOPTANDROIDTheme {
        HomeRankedBanner(
            rankedBannerUrls = listOf<String>(
                "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1150/ko/20250408/0916/P001769310.jpg/dims/resize/F_webp,400",
            ).toImmutableList(),
        )
    }
}
