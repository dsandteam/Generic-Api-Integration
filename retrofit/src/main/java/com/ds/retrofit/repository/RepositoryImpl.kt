package com.ds.retrofit.repository

import com.ds.retrofit.asJsonObject
import com.ds.retrofit.network.ApiService
import com.ds.retrofit.network.Request
import com.ds.retrofit.network.Resource
import com.ds.retrofit.network.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 *
 * Created By Amir Fury on 19 May 2022
 *
 * **/
class RepositoryImpl(private val apiService: ApiService) : RetrofitRepository, SafeApiRequest() {

    override suspend fun <ResponseType> get(apiUrl: String): Resource<ResponseType> {
        return withContext(Dispatchers.IO) {
            try {
                apiCall { apiService.get(apiUrl) }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }

    override suspend fun <ResponseType> post(
        apiUrl: String,
        request: Request
    ): Resource<ResponseType> {
        return withContext(Dispatchers.IO) {
            try {
                apiCall { apiService.post(apiUrl, request.asJsonObject()) }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }

    override suspend fun <ResponseType> multipart(
        apiUrl: String,
        requestBody: RequestBody
    ): Resource<ResponseType> {
        return withContext(Dispatchers.IO){
            try {
                apiCall { apiService.postMultipart(apiUrl,requestBody) }
            }catch (e : Exception){
                Resource.error(e.message,null)
            }
        }
    }

}