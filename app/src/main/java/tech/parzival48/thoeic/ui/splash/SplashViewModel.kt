package tech.parzival48.thoeic.ui.splash

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.parzival48.thoeic.repository.AppVersionFirestoreData
import tech.parzival48.thoeic.repository.UrlFirestoreData
import timber.log.Timber

class SplashViewModel(databaseService: FirebaseFirestore) : ViewModel() {

	val latestVersion = AppVersionFirestoreData(databaseService)
	val baseUrl = UrlFirestoreData(databaseService)
	private val animations = listOf(
			"_ _ _ _ M _ _",
			"H _ _ _ M _ _",
			"H A _ _ M A _",
			"H A N _ M A N",
			"H A N G M A N"
	)

	val animationString: Flow<String> = flow {
		animations.forEach {
			delay(350)
			emit(it)
		}
	}

	fun hasInternetConnection(connectivityManager: ConnectivityManager): Boolean {
		val networks = connectivityManager.allNetworks
		networks.forEach { nw ->
			val capabilities = connectivityManager.getNetworkCapabilities(nw)
			capabilities?.let {
				if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
					Timber.i("Connected to %s", it.toString())
					return true
				}
			}
		}
		return false
	}

}