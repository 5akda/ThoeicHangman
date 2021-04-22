package tech.parzival48.thoeic.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.VocabApiService

class VocabularyDataSource(private val apiService: VocabApiService) : PagingSource<Int, Word>() {

	companion object {
		private const val FIRST_PAGE_NUMBER = 1
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Word> {
		return try {
			val nextPageNum = params.key ?: FIRST_PAGE_NUMBER
			val response = apiService.getVocabulary(nextPageNum)
			LoadResult.Page(
					data = response.content,
					prevKey = if (nextPageNum > FIRST_PAGE_NUMBER) nextPageNum - 1 else null,
					nextKey = if (nextPageNum < response.totalPages) nextPageNum + 1 else null
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