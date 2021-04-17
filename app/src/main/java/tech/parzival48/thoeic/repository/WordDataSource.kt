package tech.parzival48.thoeic.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.parzival48.thoeic.network.WordApiService

class WordDataSource(private val apiService: WordApiService) {

	suspend fun getWord() = withContext(Dispatchers.IO) {
		apiService.getWord()
	}

}