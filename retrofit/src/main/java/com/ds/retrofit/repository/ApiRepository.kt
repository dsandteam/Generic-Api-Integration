package com.ds.retrofit.repository

import com.ds.retrofit.network.ApiRequest
import com.ds.retrofit.network.Resource
import okhttp3.RequestBody

/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
interface ApiRepository {

    suspend fun<ResponseType>  get(apiUrl: String): Resource<ResponseType>

    suspend fun <ResponseType> post(
        apiUrl: String,
        apiRequest: ApiRequest
    ): Resource<ResponseType>

    suspend fun <ResponseType> multipart(apiUrl: String,requestBody: RequestBody) : Resource<ResponseType>
}