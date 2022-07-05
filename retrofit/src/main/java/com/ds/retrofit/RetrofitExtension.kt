@file:Suppress("UNCHECKED_CAST")

package com.ds.retrofit

import com.ds.retrofit.network.Request
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

/**
 *
 * Created By Amir Fury on 19 May 2022
 *
 * **/

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)

 inline fun <reified T> T?.convertToResponse(): T {
    val jsonObject = JSONObject(this as LinkedTreeMap<String, Any>)
    return Gson().fromJson(jsonObject.toString(), T::class.java)
}

internal fun Request.asJsonObject(): JsonObject {
    return JsonParser.parseString(this.json()).asJsonObject
}