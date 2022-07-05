package com.ds.retrofit.repository

import com.ds.retrofit.network.Request
import com.ds.retrofit.network.Resource
import okhttp3.RequestBody

/**
 *
 * Created By Amir Fury on 19 May 2022
 *
 * **/
interface RetrofitRepository {

    suspend fun<ResponseType>  get(apiUrl: String): Resource<ResponseType>

    suspend fun <ResponseType> post(
        apiUrl: String,
        request: Request
    ): Resource<ResponseType>

    suspend fun <ResponseType> multipart(apiUrl: String,requestBody: RequestBody) : Resource<ResponseType>
}