package com.ds.retrofit.repository

import com.ds.retrofit.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

open class BaseRepository(private val baseApiService: BaseApiService) : SafeApiRequest() {

    suspend fun <ResponseType> get(apiUrl: String): Resource<ResponseType> {
        return withContext(Dispatchers.IO) {
            try {
                apiCall { baseApiService.get(apiUrl) }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }

    suspend fun <ResponseType> post(
        apiUrl: String,
        apiRequest: ApiRequest
    ): Resource<ResponseType> {
        return withContext(Dispatchers.IO) {
            try {
                apiCall { baseApiService.post(apiUrl, apiRequest.asJsonObject()) }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }

    suspend fun <ResponseType> postMultipart(
        apiUrl: String,
        requestBody: RequestBody
    ): Resource<ResponseType> {
        return withContext(Dispatchers.IO) {
            try {
                apiCall { baseApiService.postMultipart(apiUrl, requestBody) }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }

}