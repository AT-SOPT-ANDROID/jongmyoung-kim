package org.sopt.at.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetAllUserNicknameUseCase
import org.sopt.at.feature.search.state.SearchContract.SearchSideEffect
import org.sopt.at.feature.search.state.SearchContract.SearchState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllUserNicknameUseCase: GetAllUserNicknameUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchState())
    val uiState = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SearchSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    @OptIn(FlowPreview::class)
    fun updateSearchText(searchText: String) = viewModelScope.launch {
        _searchText.update { searchText }

        _searchText.debounce(500L)
            .collectLatest {
                searchUserNickname(it)
            }
    }

    fun searchUserNickname(searchText: String) = viewModelScope.launch {
        getAllUserNicknameUseCase(searchText).onSuccess { result ->
            result?.let { nicknames ->
                _uiState.update { it.copy(searchResult = nicknames) }
            }
        }
    }
}
