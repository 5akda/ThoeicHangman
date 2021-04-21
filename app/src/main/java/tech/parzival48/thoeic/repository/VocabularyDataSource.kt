package tech.parzival48.thoeic.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.VocabApiService

class VocabularyDataSource(private val apiService: VocabApiService) : PagingSource<Int, Word>() {

    private val FIRST_PAGE_NUMBER = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Word> {
        return try {
            val nextPageNumber = params.key ?: FIRST_PAGE_NUMBER
            val response = apiService.getVocabulary(nextPageNumber)
            LoadResult.Page(
                data = response.content,
                prevKey = if (nextPageNumber > FIRST_PAGE_NUMBER) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Word>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}