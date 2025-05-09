package org.sopt.at.feature.search.state

import androidx.compose.runtime.Immutable

class SearchContract {
    @Immutable
    data class SearchState(
        val searchText: String = "",
        val searchResult: List<String> = emptyList(),
    )

    sealed class SearchSideEffect {

    }
}