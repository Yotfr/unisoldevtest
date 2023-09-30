package ru.yotfr.connectivity.connectivityprovider

interface ConnectivityProvider {
    fun isWifiAvailable(): Boolean
}