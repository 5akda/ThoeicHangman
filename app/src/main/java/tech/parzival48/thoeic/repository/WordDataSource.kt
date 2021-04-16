package tech.parzival48.thoeic.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.parzival48.thoeic.network.ApiService

class WordDataSource(private val apiService: ApiService) {

	suspend fun getWord() = withContext(Dispatchers.IO) {
		apiService.getWord()
	}

}