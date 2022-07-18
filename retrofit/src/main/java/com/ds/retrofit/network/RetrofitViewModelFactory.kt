package com.ds.retrofit.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ds.retrofit.RetrofitViewModel
import com.ds.retrofit.repository.RetrofitRepository

class RetrofitViewModelFactory(private val repository: RetrofitRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(RetrofitViewModel::class.java) -> RetrofitViewModel(repository)
            else -> error("Invalid ViewModel")
        }
    } as T

}