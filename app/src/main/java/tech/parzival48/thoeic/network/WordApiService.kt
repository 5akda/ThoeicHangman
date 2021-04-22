package tech.parzival48.thoeic.network

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import tech.parzival48.thoeic.model.Word

interface WordApiService {

	@GET("vocabulary/page{page}/content/{item}.json")
	suspend fun getWord(
			@Path("page") pageNum: Int,
			@Path("item") itemNum: Int
	): Word

	companion object {
		fun create(retrofit: Retrofit): WordApiService {
			return retrofit.create(WordApiService::class.java)
		}
	}
}