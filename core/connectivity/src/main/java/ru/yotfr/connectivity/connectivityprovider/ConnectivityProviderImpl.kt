package ru.yotfr.connectivity.connectivityprovider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

internal class ConnectivityProviderImpl(
    private val context: Context
) : ConnectivityProvider {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isWifiAvailable(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
    }
}