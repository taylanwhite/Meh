package com.example.taylanwhite.meh

import com.example.taylanwhite.meh.data.model.Meh
import com.google.gson.GsonBuilder
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import rx.Observable

/**
 * Created by taylanwhite on 9/16/16.
 */
//https://api.meh.com/1/current.json?apikey=ZXzByssyPuglD85cLS1WJrwKda4ik9s0


interface MehService{
    @GET("1/current.json?apikey=/{api_key}")
    fun getMehData(@Path("api_key") api_key: String): Observable<DailyProduct>

}
