package com.ds.retrofitintegration

import android.app.Application
import com.ds.retrofit.ApiViewModel
import com.ds.retrofit.network.ApiService
import com.ds.retrofit.network.ServiceGenerator
import com.ds.retrofit.repository.ApiRepository
import com.ds.retrofit.repository.ApiRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton {
            ServiceGenerator.invoke<ApiService>(
                instance(),
                Constants.baseUrl,
                null, false
            )
        }
        bind<ApiRepository>() with singleton { ApiRepositoryImpl(instance()) }
        bind() from singleton { ApiViewModel(instance()) }
    }

}