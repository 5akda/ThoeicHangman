package tech.parzival48.thoeic.ui.splash

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.parzival48.thoeic.repository.AppVersionFirestoreData
import tech.parzival48.thoeic.repository.UrlFirestoreData
import timber.log.Timber

class SplashViewModel(databaseService: FirebaseFirestore) : ViewModel() {

    val animationString = MutableLiveData<String>()
    val latestVersion = AppVersionFirestoreData(databaseService)
    val baseUrl = UrlFirestoreData(databaseService)

    init {
        drawAnimation()
    }

    fun hasInternetConnection(connectivityManager: ConnectivityManager): Boolean {
        val networks = connectivityManager.allNetworks
        networks.forEach { nw ->
            val capabilities = connectivityManager.getNetworkCapabilities(nw)
            capabilities?.let {
                if(it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    Timber.i("Connected to %s", it.toString())
                    return true
                }
            }
        }
        return false
    }

    private fun drawAnimation() {
        val animations = listOf(
                "_ _ _ _ M _ _",
                "H _ _ _ M _ _",
                "H A _ _ M A _",
                "H A N _ M A N",
                "H A N G M A N"
        )
        viewModelScope.launch(Dispatchers.IO) {
            animations.forEach {
                delay(350L)
                animationString.postValue(it)
            }
        }
    }
}