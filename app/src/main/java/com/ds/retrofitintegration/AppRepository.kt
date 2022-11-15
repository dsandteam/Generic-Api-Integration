package com.ds.retrofitintegration

import com.ds.retrofit.repository.BaseRepository

class AppRepository(private val apiService: ApiService) :
    BaseRepository(apiService) {

    suspend fun delete() {
        apiService.delete()
    }

}