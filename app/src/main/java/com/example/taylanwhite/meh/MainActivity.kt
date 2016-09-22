package com.example.taylanwhite.meh

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.example.taylanwhite.meh.model.Deal
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
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
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fetchData()

        btnPastDeals.setOnClickListener {

            val intent = Intent(this,PastDeals::class.java)
            startActivity(intent)

        }






        }


    fun fetchData()
    {
        var i = 1

        MehService.retrofit.getDailyProduct().enqueue(object: Callback<DealObject>{

            override fun onResponse(call: Call<DealObject>?, response: Response<DealObject>?) {

                if(response?.isSuccessful ?: false) {
                    response?.body()!!.let { response ->



                        txtName.text = response.deal.title
                        txtPrice.text = "Price:" + response.deal.items[0].price.toString()
                        txtDescription.text = response.deal.specifications
                        //imageDeal.setImageResource() =
                       //response.deal.items[0].photo?.
                       Picasso.with(this@MainActivity).load(response.deal.photos[0]).into(imageDeal)

                        imageDeal.setOnClickListener {

                            if (i >= response.deal.photos.size)
                            {
                                i = 0
                            }
                            Picasso.with(this@MainActivity).load(response.deal.photos[i]).into(imageDeal)

                            i++

                        }


                        btnBuy.setOnClickListener {

                            try {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.setData(Uri.parse(response.deal.url))
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }

                        val currentLayout = findViewById(R.id.main_layout) as RelativeLayout
                        currentLayout.setBackgroundColor(Color.parseColor(response.deal.theme?.backgroundColor))


//                        val mImageSwitcherPicasso = ImageSwitcherPicasso(this@MainActivity, imageSwitcher)
//                        Picasso.with(this@MainActivity).load(response.deal.photos[0]).into(mImageSwitcherPicasso)
//
//                       // val image = Picasso.with(this@MainActivity).load(response.deal.photos[0])
                        //val mImageSwitcherPicasso = ImageSwitcherPicasso(this@MainActivity, imageSwitcher)
                        //
                        //  imageSwitcher.setImageResource(image)


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
