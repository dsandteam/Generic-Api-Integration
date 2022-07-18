package com.ds.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.retrofit.network.ApiRequest
import com.ds.retrofit.network.Resource
import com.ds.retrofit.repository.ApiRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/

open class ApiViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    open fun <ResponseType> get(apiUrl: String): LiveData<Resource<ResponseType>> {
        val data = MutableLiveData<Resource<ResponseType>>()
        data.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response = apiRepository.get<ResponseType>(apiUrl)
            data.postValue(response)
        }
        return data
    }

    open fun <ResponseType> post(
        apiUrl: String,
        apiRequest: ApiRequest
    ): LiveData<Resource<ResponseType>> {
        val data = MutableLiveData<Resource<ResponseType>>()
        data.postValue(Resource.loading(null))
        viewModelScope.launch { data.postValue(apiRepository.post(apiUrl, apiRequest)) }
        return data
    }

    open fun <ResponseType> postMultipart(
        apiUrl: String,
        requestBody: RequestBody
    ): LiveData<Resource<ResponseType>> {
        val data = MutableLiveData<Resource<ResponseType>>()
        data.postValue(Resource.loading(null))
        viewModelScope.launch { data.postValue(apiRepository.multipart(apiUrl, requestBody)) }
        return data
    }

}