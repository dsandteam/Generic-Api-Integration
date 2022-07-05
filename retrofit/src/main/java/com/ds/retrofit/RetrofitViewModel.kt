package com.ds.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.retrofit.network.Request
import com.ds.retrofit.network.Resource
import com.ds.retrofit.repository.RetrofitRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *
 * Created By Amir Fury on 19 May 2022
 *
 * **/
open class RetrofitViewModel(private val retrofitRepository: RetrofitRepository) : ViewModel() {

    open fun <ResponseType> get(apiUrl: String): LiveData<Resource<ResponseType>> {
        val data = MutableLiveData<Resource<ResponseType>>()
        data.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response = retrofitRepository.get<ResponseType>(apiUrl)
            data.postValue(response)
        }
        return data
    }

    open fun <ResponseType> post(
        apiUrl: String,
        request: Request
    ): LiveData<Resource<ResponseType>> {
        val data = MutableLiveData<Resource<ResponseType>>()
        data.postValue(Resource.loading(null))
        viewModelScope.launch { data.postValue(retrofitRepository.post(apiUrl, request)) }
        return data
    }

    open fun <ResponseType> postMultipart(
        apiUrl: String,
        requestBody: RequestBody
    ): LiveData<Resource<ResponseType>> {
        val data = MutableLiveData<Resource<ResponseType>>()
        data.postValue(Resource.loading(null))
        viewModelScope.launch { data.postValue(retrofitRepository.multipart(apiUrl, requestBody)) }
        return data
    }

}