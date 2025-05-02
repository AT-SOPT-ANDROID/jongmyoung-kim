package org.sopt.at.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.core.common.type.HomeGenreType
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.core.util.noRippleClickable

@Composable
fun HomeTabRow(
    homeTabs: ImmutableList<HomeGenreType>,
    selectedTab: HomeGenreType?,
    onSelectTab: (HomeGenreType?) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        itemsIndexed(
            items = homeTabs,
            key = { index, _ -> index },
        ) { index, tab ->
            HomeTabItem(
                genreTab = tab,
                isSelected = tab == selectedTab,
                onSelectTab = { onSelectTab(tab) },
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 12.dp else 0.dp,
                        end = if (index == homeTabs.lastIndex) 12.dp else 0.dp,
                    ),
            )
        }
    }
}

@Composable
private fun HomeTabItem(
    genreTab: HomeGenreType,
    isSelected: Boolean,
    onSelectTab: (HomeGenreType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genreTab.genre,
        style = AtSoptTheme.typography.body16m.copy(
            color = if (isSelected) AtSoptTheme.colors.white else AtSoptTheme.colors.gray100,
        ),
        modifier = modifier
            .noRippleClickable { onSelectTab(genreTab) }
            .padding(vertical = 8.dp),
    )
}

@Preview
@Composable
private fun HomeTabRowPreview() {
    ATSOPTANDROIDTheme {
        var selectedTab by remember { mutableStateOf(HomeGenreType.DRAMA) }

        HomeTabRow(
            homeTabs = HomeGenreType.entries.toImmutableList(),
            selectedTab = selectedTab,
            onSelectTab = { },
            modifier = Modifier,
        )
    }
}
