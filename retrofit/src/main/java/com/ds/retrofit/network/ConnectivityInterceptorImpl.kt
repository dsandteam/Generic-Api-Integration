package com.ds.retrofit.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * Created By Amir Fury on 19 May 2022
 *
 * **/

private const val noInternetError = "No Internet Connection!"

class ConnectivityInterceptorImpl(private val context: Context) : ConnectivityInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(context))
            throw ApiException(noInternetError)
        else {
            val request = chain.request()
            // TODO: Add headers to newRequest
            val newRequest = request.newBuilder().build()
            return chain.proceed(newRequest)
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}