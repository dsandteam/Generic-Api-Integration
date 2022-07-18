package com.ds.retrofit.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ds.retrofit.ApiViewModel
import com.ds.retrofit.repository.ApiRepository
/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
class ViewModelFactory(private val repository: ApiRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(ApiViewModel::class.java) -> ApiViewModel(repository)
            else -> error("Invalid ViewModel")
        }
    } as T

}