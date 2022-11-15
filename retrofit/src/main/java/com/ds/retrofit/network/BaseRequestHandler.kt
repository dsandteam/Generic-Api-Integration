@file:Suppress("UNCHECKED_CAST")

package com.ds.retrofit.network

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.internal.LinkedTreeMap
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
/**
 *
 * Created By Amir Fury on 18 July 2022
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


inline fun <reified T> T?.convertToResponse(): T {
    val jsonObject = JSONObject(this as LinkedTreeMap<String, Any>)
    return Gson().fromJson(jsonObject.toString(), T::class.java)
}

internal fun ApiRequest.asJsonObject(): JsonObject {
    return JsonParser.parseString(Gson().toJson(this)).asJsonObject
}