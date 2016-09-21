package com.example.taylanwhite.meh

import android.content.Intent
import android.database.Observable
import android.graphics.Movie
import com.example.taylanwhite.meh.model.Deal
//import com.example.taylanwhite.meh.model.manual.model.DailyProduct
import com.google.gson.GsonBuilder
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by taylanwhite on 9/19/16.
 */
class MehService {


    companion object {
        var BASE_URL = "https://api.meh.com/1/"
        var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(MehInterface::class.java)
    }



    interface MehInterface
    {
        @GET("current.json")
        fun getDailyProduct(@Query("apikey")apiKey: String = "ZXzByssyPuglD85cLS1WJrwKda4ik9s0" ):Call<DealObject>
    }











}
