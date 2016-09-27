package com.example.taylanwhite.meh

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.example.taylanwhite.meh.model.Deal
import com.example.taylanwhite.meh.model.Video
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.RealmConfiguration
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

        // Realm myRealm = Realm.getInstance(this@MainActivity)
//        val realm = Realm.getInstance(context)
//        realm.beginTransaction()
//        val video = realm.createObject(Video::class.java)
//
//        realm.commitTransaction()


        //Underline txtMoreSpecs
        txtMoreSpecs.setPaintFlags(txtMoreSpecs.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        //Call function for data call
        fetchData()

        btnPastDeals.setOnClickListener {

            val intent = Intent(this, PastDeals::class.java)
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
                        txtPrice.text = "Price: $" + response.deal.items[0].price.toString()
                        txtDescription.text = response.deal.features
                        //imageDeal.setImageResource() =
                       //response.deal.items[0].photo?.
                       Picasso.with(this@MainActivity).load(response.deal.photos[0]).into(imageDeal)

                        //Handles photos
                        imageDeal.setOnClickListener {

                            if (i >= response.deal.photos.size)
                            {
                                i = 0
                            }
                            Picasso.with(this@MainActivity).load(response.deal.photos[i]).into(imageDeal)

                            i++

                        }

                        //Handles button for going to meh website
                        btnBuy.setOnClickListener {

                            try {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.setData(Uri.parse(response.deal.url))
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }

                        btnMovie.setOnClickListener {
                            try {
                            val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(response.video.url)
                            //    intent.data = Uri.parse(response.deal.video?.url)
                            startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }

                        txtMoreSpecs.setOnClickListener{

                            try {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(response.deal.topic?.url)
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }



                        btnNotification.setOnClickListener {

                            var firstPicture = response.deal.photos[0]
                            var testPicture = Picasso.with(this@MainActivity).load(response.deal.photos[0])
                            val mBuilder = NotificationCompat.Builder(this@MainActivity).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(response.deal.title).setContentText(response.deal.items[0].price.toString()
                            )
                            val resultIntent = Intent(this@MainActivity, MainActivity::class.java)
                            val resultPendingIntent = PendingIntent.getActivity(
                                    this@MainActivity,
                                    0,
                                    resultIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT)
                            mBuilder.setContentIntent(resultPendingIntent)
                            // Sets an ID for the notification
                            val mNotificationId = 1
                            // Gets an instance of the NotificationManager service
                            val mNotifyMgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                            // Builds the notification and issues it.
                            mNotifyMgr.notify(mNotificationId, mBuilder.build())




                        }

                        //Handles theme layout
                        val currentLayout = findViewById(R.id.main_layout) as RelativeLayout
                        currentLayout.setBackgroundColor(Color.parseColor(response.deal.theme?.backgroundColor))


                    }
                }
            }
            override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }




}
