package tech.parzival48.thoeic

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import tech.parzival48.thoeic.network.NetworkProvider
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.ui.game.GameViewModel
import tech.parzival48.thoeic.ui.splash.SplashViewModel
import timber.log.Timber

class ThoeicHangmanApp : Application() {

    private val networkModule = module {
        single { NetworkProvider.getFirestore() }

        single { NetworkProvider.getRetrofit() }
        factory { WordApiService.create(get()) }
    }

    private val viewModelModule = module {
        viewModel { SplashViewModel(get()) }
        viewModel { GameViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ThoeicHangmanApp)
            androidLogger()
            modules(listOf(networkModule, viewModelModule))
        }

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}