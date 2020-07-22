package com.news.newsapi.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors

object Utils {


    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("google.com")
            //You can replace it with your name

            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }
    fun hasServerConnected(context: Context): Boolean {
        if (hasNetworkAvailable(context)) {
            try {
                val connection = URL("https://www.google.com/").openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "Test")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1000 //configurable


                val callable: Callable<Boolean> = Callable {
                    connection.connect()
                    return@Callable  (connection.responseCode == 200)
                }
                return Executors.newSingleThreadExecutor().submit(callable).get()

            } catch (e: IOException) {
                Timber.d("Error checking server connection!!")
                //Log.e("classTag", "Error checking server connection", e)
            }
        } else {
            Timber.d("Server is unavailable!")
            //Log.w("classTag", "Server is unavailable!")
        }
        Timber.d("Can not connect to server!!")
        //Log.d("classTag", "hasServerConnected: false")
        return false
    }
    private fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        Timber.d("hasNetworkAvailable: ${(network != null)}")
       // Log.d("classTag", "hasNetworkAvailable: ${(network != null)}")
        return (network != null)
    }
}