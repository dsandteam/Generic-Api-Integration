package com.ds.retrofitintegration

import com.ds.retrofit.network.BaseApiService

interface ApiService : BaseApiService {

    suspend fun delete()

}