package org.sopt.at.feature.main.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import org.sopt.at.R.drawable.ic_history_selected_24
import org.sopt.at.R.drawable.ic_history_unselected_24
import org.sopt.at.R.drawable.ic_home_selected_24
import org.sopt.at.R.drawable.ic_home_unselected_24
import org.sopt.at.R.drawable.ic_live_selected_24
import org.sopt.at.R.drawable.ic_live_unselected_24
import org.sopt.at.R.drawable.ic_search_selected_24
import org.sopt.at.R.drawable.ic_search_unselected_24
import org.sopt.at.R.drawable.ic_shorts_selected_24
import org.sopt.at.R.drawable.ic_shorts_unselected_24
import org.sopt.at.R.string.ic_history_desc
import org.sopt.at.R.string.ic_home_desc
import org.sopt.at.R.string.ic_live_desc
import org.sopt.at.R.string.ic_search_desc
import org.sopt.at.R.string.ic_shorts_desc
import org.sopt.at.core.common.navigation.MainTabRoute
import org.sopt.at.core.common.navigation.Route
import org.sopt.at.feature.history.navigation.History
import org.sopt.at.feature.home.navigation.Home
import org.sopt.at.feature.live.navigation.Live
import org.sopt.at.feature.main.type.MainTab.entries
import org.sopt.at.feature.search.navigation.Search
import org.sopt.at.feature.shorts.navigation.Shorts

enum class MainTab(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute,
) {
    HOME(
        selectedIcon = ic_home_selected_24,
        unselectedIcon = ic_home_unselected_24,
        contentDescription = ic_home_desc,
        route = Home,
    ),

    SHORTS(
        selectedIcon = ic_shorts_selected_24,
        unselectedIcon = ic_shorts_unselected_24,
        contentDescription = ic_shorts_desc,
        route = Shorts,
    ),

    LIVE(
        selectedIcon = ic_live_selected_24,
        unselectedIcon = ic_live_unselected_24,
        contentDescription = ic_live_desc,
        route = Live,
    ),

    SEARCH(
        selectedIcon = ic_search_selected_24,
        unselectedIcon = ic_search_unselected_24,
        contentDescription = ic_search_desc,
        route = Search,
    ),

    HISTORY(
        selectedIcon = ic_history_selected_24,
        unselectedIcon = ic_history_unselected_24,
        contentDescription = ic_history_desc,
        route = History,
    );

    companion object {

        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
