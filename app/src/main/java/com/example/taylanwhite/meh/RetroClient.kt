package com.example.taylanwhite.meh

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by taylanwhite on 9/16/16.
 */

object RetroClient {

    private val ROOT_URL = "https://api.meh.com/1/current.json?apikey=ZXzByssyPuglD85cLS1WJrwKda4ik9s0"
    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build()

    interface ApiService {


        val myJSON: Call<ProductList>
    }

    val apiService: ApiService
        get() = retrofitInstance.create(ApiService::class.java)
}
