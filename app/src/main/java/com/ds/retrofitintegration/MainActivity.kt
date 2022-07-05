package com.ds.retrofitintegration

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ds.retrofit.RetrofitViewModel
import com.ds.retrofit.convertToResponse
import com.ds.retrofit.json
import com.ds.retrofit.network.Status
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val retrofitViewModel by instance<RetrofitViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchNewData()
    }

    private fun fetchNewData() {
        val response =
            retrofitViewModel.get<NewsResponse>(Constants.apiUrl.plus(Constants.newsApiKey))
        response.observe(this) {
            when (it?.status) {
                Status.LOADING -> {
                    toast("Loading...")
                }
                Status.SUCCESS -> {
                    toast("Success...")
                    toast(it.data.convertToResponse().json())
                }
                Status.ERROR -> {toast("Error : ${it.message}")}
            }
        }
    }
}

fun Context.toast(message : String?) = Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()