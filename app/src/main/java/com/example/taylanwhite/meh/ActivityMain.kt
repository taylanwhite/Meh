package com.example.taylanwhite.meh

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_daily_deal.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityMain : AppCompatActivity() {
    //var BASE_URL = "ZXzByssyPuglD85cLS1WJrwKda4ik9s0"
    //var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    //txtName.setText("test")
    //txtPrice.setText("22")
    //txtDescription.setText("..")
    //"https://api.meh.com/1/current.json?apikey=ZXzByssyPuglD85cLS1WJrwKda4ik9s0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_deal)

        val mehService: MehService = retrofit.create()
    MehService


    }


}
