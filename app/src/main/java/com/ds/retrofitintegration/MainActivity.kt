package com.ds.retrofitintegration

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ds.retrofit.network.Status
import com.ds.retrofit.network.convertToResponse
import com.google.gson.Gson
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val appViewModel by instance<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchNewData()
    }

    private fun fetchNewData() {
        val response =
            appViewModel.get<NewsResponse>(Constants.apiUrl.plus(Constants.newsApiKey))
        response.observe(this) {
            when (it?.status) {
                Status.LOADING -> {
                    toast("Loading...")
                }
                Status.SUCCESS -> {
                    toast(Gson().toJson(it.data.convertToResponse()))
                }
                Status.ERROR -> {toast("Error : ${it.message}")}
                else -> {}
            }
        }
    }
}

fun Context.toast(message : String?) = Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()