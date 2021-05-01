package tech.parzival48.thoeic.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object NetworkProvider {

	private const val GITHUB_URL = "https://raw.githubusercontent.com/parzival48/ThoeicHangman/main/_api/"
	private var mApiUrl = ""

	fun setApiUrl(apiUrl: String) {
		mApiUrl = apiUrl
	}

	fun getRtdbRetrofit(): Retrofit {
		Timber.d("Retrofit Base URL: %s", mApiUrl)
		return Retrofit.Builder()
				.baseUrl(mApiUrl)
				.client(getOkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.build()
	}

	fun getGithubRetrofit(): Retrofit {
		Timber.d("Retrofit Base URL: %s", mApiUrl)
		return Retrofit.Builder()
				.baseUrl(GITHUB_URL)
				.client(getOkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.build()
	}

	fun getFirestore(): FirebaseFirestore {
		return Firebase.firestore
	}

	private fun getOkHttpClient(): OkHttpClient {
		val logger = HttpLoggingInterceptor()
		logger.level = HttpLoggingInterceptor.Level.BODY
		return OkHttpClient.Builder()
				//.addInterceptor(logger)
				.build()
	}
}