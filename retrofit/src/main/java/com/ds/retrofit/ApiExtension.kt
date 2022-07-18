@file:Suppress("UNCHECKED_CAST")

package com.ds.retrofit

import com.ds.retrofit.network.ApiRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.internal.LinkedTreeMap
import org.json.JSONObject

/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/

 inline fun <reified T> T?.convertToResponse(): T {
    val jsonObject = JSONObject(this as LinkedTreeMap<String, Any>)
    return Gson().fromJson(jsonObject.toString(), T::class.java)
}

internal fun ApiRequest.asJsonObject(): JsonObject {
    return JsonParser.parseString(Gson().toJson(this)).asJsonObject
}