package tech.parzival48.thoeic

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import tech.parzival48.thoeic.network.NetworkProvider
import tech.parzival48.thoeic.network.VocabApiService
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.repository.AppVersionFirestoreData
import tech.parzival48.thoeic.repository.UrlFirestoreData
import tech.parzival48.thoeic.repository.VocabularyDataSource
import tech.parzival48.thoeic.repository.WordDataSource
import tech.parzival48.thoeic.ui.game.GameViewModel
import tech.parzival48.thoeic.ui.splash.SplashViewModel
import tech.parzival48.thoeic.ui.vocab.VocabViewModel
import timber.log.Timber

class ThoeicHangmanApp : Application() {

	private val networkModule = module {
		single { NetworkProvider.getFirestore() }
		single { WordApiService.create(NetworkProvider.getRtdbRetrofit()) }
		single { VocabApiService.create(NetworkProvider.getGithubRetrofit()) }
	}

	private val vocabModule = module {
		factory { VocabularyDataSource(get()) }
		viewModel { VocabViewModel(get()) }
	}

	private val splashModule = module {
		factory { AppVersionFirestoreData(get()) }
		factory { UrlFirestoreData(get()) }
		viewModel { SplashViewModel(get(), get()) }
	}

	private val gameModule = module {
		factory { WordDataSource(get()) }
		viewModel { GameViewModel(get()) }
	}

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@ThoeicHangmanApp)
			androidLogger()
			modules(networkModule, vocabModule, splashModule, gameModule)
		}

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
	}
}