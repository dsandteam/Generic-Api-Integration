package com.ds.retrofit.network

import android.content.Context
import com.amir.chuck.RequestLogger
import com.ds.retrofit.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/

object ServiceGenerator {

    inline operator fun <reified T> invoke(
        context: Context,
        baseUrl: String,
        connectivityInterceptor: Interceptor?,timeOut: Long = 120
    ): T {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val gson = GsonBuilder().setLenient().create()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(timeOut, TimeUnit.SECONDS)
            readTimeout(timeOut, TimeUnit.SECONDS)
            writeTimeout(timeOut, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            addInterceptor(loggingInterceptor)
            addInterceptor(RequestLogger(context))
            connectivityInterceptor?.let {
                addInterceptor(it)
            } ?: addInterceptor(BaseInterceptor(context, null))
        }.build()

        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(T::class.java)
    }
}