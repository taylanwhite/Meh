package com.example.taylanwhite.meh

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.taylanwhite.meh.model.Deal
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import kotlinx.android.synthetic.main.activity_main.*
//import retrofit.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()

        btnPastDeals.setOnClickListener {

            val intent = Intent(this,PastDeals::class.java)
            startActivity(intent)

        }


        btnBuy.setOnClickListener {


        }



        }


    fun fetchData()
    {
        MehService.retrofit.getDailyProduct().enqueue(object: Callback<DealObject>{

            override fun onResponse(call: Call<DealObject>?, response: Response<DealObject>?) {

                if(response?.isSuccessful ?: false) {
                    response?.body()!!.let { response ->


                        txtName.text = response.deal.title
                        txtPrice.text = "Price:" + response.deal.items[0].price.toString()
                        txtDescription.text = response.deal.features
                      //  imageDeal.setImageResource() =
                    }


                }
                else {

                }

            }

            override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })


    }
}
