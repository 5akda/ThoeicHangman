package tech.parzival48.thoeic.ui.vocab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import tech.parzival48.thoeic.network.VocabApiService
import tech.parzival48.thoeic.repository.VocabularyDataSource

class VocabViewModel(private val apiService: VocabApiService) : ViewModel() {

    private val config = PagingConfig(
        enablePlaceholders = false,
        pageSize = 30,
        prefetchDistance = 5
    )

    var vocabulary = Pager(config) {
        VocabularyDataSource(apiService)
    }.flow.cachedIn(viewModelScope)

}