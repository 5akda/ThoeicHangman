package tech.parzival48.thoeic.ui.vocab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import tech.parzival48.thoeic.network.VocabApiService
import tech.parzival48.thoeic.repository.VocabularyDataSource

class VocabViewModel(private val apiService: VocabApiService) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 30
        private const val PREFETCH_DISTANCE = 5
    }

    private val config = PagingConfig(
        enablePlaceholders = true,
        pageSize = PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE
    )

    var vocabulary = Pager(config) {
        VocabularyDataSource(apiService)
    }.flow.cachedIn(viewModelScope)

}