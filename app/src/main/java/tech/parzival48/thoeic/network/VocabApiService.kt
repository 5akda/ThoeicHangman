package tech.parzival48.thoeic.network

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import tech.parzival48.thoeic.model.RespondedList
import tech.parzival48.thoeic.model.Word

interface VocabApiService {

	@GET("{page}")
	suspend fun getVocabulary(
			@Path("page") page: Int
	): RespondedList<Word>

	companion object {
		fun create(retrofit: Retrofit): VocabApiService {
			return retrofit.create(VocabApiService::class.java)
		}
	}
}