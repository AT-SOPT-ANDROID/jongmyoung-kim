package org.sopt.at.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.core.common.type.HomeVodType
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme

@Composable
fun HomeVodButtonRow(
    vodButtons: ImmutableList<HomeVodType>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
        itemsIndexed(
            items = vodButtons,
            key = { index, _ -> index },
        ) { _, button ->
            HomeVodButtonMapper(
                button = button,
            )
        }
    }
}

@Composable
private fun HomeVodButtonMapper(
    button: HomeVodType,
    modifier: Modifier = Modifier,
) {
    when (button) {
        HomeVodType.KBO -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2025/03/23/1742719432_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )

        HomeVodType.APPLE -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2025/04/16/1744771892_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )

        HomeVodType.KBL -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2024/12/10/1733821492_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )

        HomeVodType.KIDS -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2024/10/21/1729475548_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )
        
        HomeVodType.UFC -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2023/10/19/1697689505_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )

        HomeVodType.CHAMPS_LEAGUE -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2024/09/19/1726753837_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )

        HomeVodType.TENNIS -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2024/09/23/1727035728_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )

        HomeVodType.STYLE_COLLECTION -> HomeVodButton(
            icon = "https://image.tving.com/ntgs/operation/specialHall/2024/10/07/1728293421_1.png/dims/resize/F_webp,400",
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun HomeVodButton(
    icon: String,
    modifier: Modifier = Modifier,
) {
    val gradientColor = Brush.verticalGradient(
        colors = listOf(
            AtSoptTheme.colors.gray500.copy(alpha = .9f),
            AtSoptTheme.colors.gray600.copy(alpha = .1f),
        ),
    )

    GlideImage(
        model = icon,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(gradientColor)
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .width(68.dp)
            .aspectRatio(2f),
    )
}

@Preview
@Composable
private fun HomeVodButtonRowPreview() {
    ATSOPTANDROIDTheme {
        HomeVodButtonRow(
            vodButtons = HomeVodType.entries.toImmutableList(),
        )
    }
}
