package com.ds.retrofitintegration

import com.ds.retrofit.viewmodel.BaseViewModel

class AppViewModel(private val repository: AppRepository) :
    BaseViewModel(repository) {
    suspend fun delete() {
        repository.delete()
    }
}