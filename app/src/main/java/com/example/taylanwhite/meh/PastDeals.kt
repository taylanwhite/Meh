package com.example.taylanwhite.meh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_past_deals.*

open class PastDeals : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_deals)






        btnBack.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)



        }



    }

    fun notification(){



    }





}
