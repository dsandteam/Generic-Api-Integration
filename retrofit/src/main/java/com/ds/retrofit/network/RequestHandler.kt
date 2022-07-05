package com.ds.retrofit.network

import retrofit2.Response
import java.io.IOException

/**
 *
 * Created By Amir Fury on 19 May 2022
 *
 * **/

enum class Status {
    LOADING, SUCCESS, ERROR
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

abstract class SafeApiRequest {
    suspend fun <T > apiCall(call: suspend () -> Response<T>): Resource<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            return Resource.success(response.body())
        } else {
            throw ApiException(response.code().toString())
        }
    }
}

internal class ApiException(message: String) : IOException(message)