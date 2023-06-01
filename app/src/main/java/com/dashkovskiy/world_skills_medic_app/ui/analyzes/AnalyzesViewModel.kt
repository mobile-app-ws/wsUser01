package com.dashkovskiy.world_skills_medic_app.ui.analyzes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkovskiy.world_skills_medic_app.api.ApiService
import com.dashkovskiy.world_skills_medic_app.api.Catalog
import com.dashkovskiy.world_skills_medic_app.api.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

data class AnalyzesState(
    val originalCatalog : List<Catalog> = emptyList(),
    val catalog: List<Catalog> = emptyList(),
    val news: List<News> = emptyList(),
    val categories: Set<String> = emptySet(),
    val searchValue : String = "",
    val selectedCategory : String = "",
    val isLoading: Boolean = false
)

class AnalyzesViewModel(
    private val apiService: ApiService
) : ViewModel() {
    private val _state = MutableStateFlow(AnalyzesState())
    val viewState = _state.asStateFlow()

    private val state: AnalyzesState
        get() = _state.value

    init {
        viewModelScope.launch {
            _state.value = state.copy(isLoading = true)
            joinAll(
                launch { getNews() },
                launch { getCatalog() }
            )
            _state.value = state.copy(isLoading = false)
        }
    }

    fun setSearch(search : String){
        _state.value = state.copy(searchValue = search)
    }

    fun setCategory(category : String){
        _state.value = state.copy(
            selectedCategory = category,
            catalog = state.originalCatalog.filter { it.category == category }
        )

    }

    private suspend fun getNews() {
        val response = apiService.getNews()
        if (response.isSuccessful) {
            val body = response.body() ?: return
            _state.update { it.copy(news = body) }
        }
    }

    private suspend fun getCatalog() {
        val response = apiService.getCatalog()
        if (response.isSuccessful) {
            val body = response.body() ?: return
            _state.update {
                it.copy(
                    catalog = body,
                    originalCatalog = body,
                    categories = body.map { c -> c.category }.toSet()
                )
            }
        }
    }

}