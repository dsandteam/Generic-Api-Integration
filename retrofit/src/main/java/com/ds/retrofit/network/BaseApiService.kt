package com.ds.retrofit.network

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
interface BaseApiService {

    @GET
    suspend fun <ResponseType> get(
        @Url url: String,
    ): Response<ResponseType>

    @POST
    suspend fun <ResponseType> post(
        @Url url: String,
        @Body request: JsonObject
    ): Response<ResponseType>

    @POST
    @Multipart
    suspend fun <ResponseType> postMultipart(
        @Url url: String,
        @Body requestBody: RequestBody
    ): Response<ResponseType>

}