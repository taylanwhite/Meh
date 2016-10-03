package com.example.taylanwhite.meh

import android.app.*
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.util.Log
import android.view.*
import android.widget.*
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
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialogue_layout.*
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
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mActionBar = supportActionBar
        mActionBar?.setDisplayShowHomeEnabled(false)
        mActionBar?.setDisplayShowTitleEnabled(false)
        val mInflater = LayoutInflater.from(this)
        val mCustomView = mInflater.inflate(R.layout.action_bar, null)
        mActionBar?.customView = mCustomView
        mActionBar?.setDisplayShowCustomEnabled(true)
        mActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A9A9A9")))
        val mTitleTextView = mCustomView.findViewById(R.id.txtSettings) as TextView

        mTitleTextView.text = "Settings"




       mTitleTextView.setOnClickListener {
           settingsFun()

       }









        //Underline txtMoreSpecs
        txtMoreSpecs.paintFlags = txtMoreSpecs.paintFlags or Paint.UNDERLINE_TEXT_FLAG

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

                            val url = URL(response.deal.photos[0])
                            val image = BitmapFactory.decodeStream(url.openConnection().inputStream)
                            //setLargeIcon(image)
                            val mBuilder = NotificationCompat.Builder(this@MainActivity).setLargeIcon(image).setContentTitle(response.deal.title).setContentText(response.deal.items[0].price.toString()
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
//                        btnPastDeals.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnBuy.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnMovie.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
                        //btnClearData.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))



                    }
                }
            }
            override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }

    fun getBitmapFromURL(src: String): Bitmap? {
        try {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.getInputStream()
            val myBitmap = BitmapFactory.decodeStream(input)
            return myBitmap
        } catch (e: IOException) {
            // Log exception
            return null
        }

    }



        fun settingsFun()
        {
//            val container = findViewById(R.id.main_layout) as RelativeLayout
//
//            val view = View.inflate(this, R.layout.custom_dialogue_layout, container)

            val dialog = Dialog(this@MainActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_dialogue_layout)
            dialog.show()

            //val btnDialogBack = findViewById(view.id.)
            val btnToggleNots = dialog.findViewById(R.id.tglNotifications)
            val btnDialogBack = dialog.findViewById(R.id.btnDialogueBack)
            val clearData = dialog.findViewById(R.id.btnClearData)


            btnDialogBack.setOnClickListener {
            dialog.cancel()
            }

        }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main_menu, menu)
//
//        return true
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//
//        return true
//    }



}
