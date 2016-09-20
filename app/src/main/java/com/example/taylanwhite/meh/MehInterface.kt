package com.example.taylanwhite.meh

import android.content.Intent
import android.database.Observable
import android.graphics.Movie
import com.example.taylanwhite.meh.model.manual.model.DailyProduct
import retrofit2.Call

import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by taylanwhite on 9/19/16.
 */
open interface MehInterface {



    @GET("/1/current.json?apikey=ZXzByssyPuglD85cLS1WJrwKda4ik9s0")
    fun getDailyProduct(@Body user:DailyProduct): Call<DailyProduct>




}
