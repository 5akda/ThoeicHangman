package tech.parzival48.thoeic.repository

import tech.parzival48.thoeic.model.RespondedList
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.VocabApiService

class VocabularyDataSource(private val apiService: VocabApiService) {

	suspend fun getVocabulary(): RespondedList<Word> {
		return apiService.getVocabulary()
	}

}