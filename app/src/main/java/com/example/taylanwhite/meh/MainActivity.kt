package com.example.taylanwhite.meh

import android.app.*
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.example.taylanwhite.meh.model.Deal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {


    var toggleChecked = ""


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



        //handle loading screen
        loadingScreen()

       mTitleTextView.setOnClickListener {
           settingsFun()

       }



        //Underline txtMoreSpecs
        txtMoreSpecs.paintFlags = txtMoreSpecs.paintFlags or Paint.UNDERLINE_TEXT_FLAG



        btnPastDeals.setOnClickListener {

            val intent = Intent(this, PastDeals::class.java)
            startActivity(intent)

        }

    }


    fun fetchData()
    {
        var i = 1


        val controller = DatabaseHelper(this)

        MehService.retrofit.getDailyProduct().enqueue(object: Callback<DealObject>{

            override fun onResponse(call: Call<DealObject>?, response: Response<DealObject>?) {

                if(response?.isSuccessful ?: false) {
                    response?.body()!!.let { response ->
                        val fastTitle = response.deal.title
                        val  fastPrice = "Price: $" + response.deal.items[0].price.toString()
                        val fastDescription = response.deal.features
                        val buyURL = response.deal.url
                        val movieURL = response.video.url
                        val specURL = response.deal.topic?.url
                        val fastBackground = response.deal.theme?.backgroundColor
                        txtName.text = fastTitle
                        txtPrice.text = fastPrice
                        txtDescription.text = fastDescription
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
                                intent.setData(Uri.parse(buyURL))
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }

                        btnMovie.setOnClickListener {
                            try {
                            val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(movieURL)
                            startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }

                        txtMoreSpecs.setOnClickListener{

                            try {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(specURL)
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }



                        btnNotification.setOnClickListener {

                            //setLargeIcon(image)
                            if((toggleChecked.equals("True"))) {
                                val mBuilder = NotificationCompat.Builder(this@MainActivity).setAutoCancel(true).setSmallIcon(R.mipmap.notification_icon).setContentTitle(response.deal.title).setContentText("Price= $" + response.deal.items[0].price.toString())

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


                        }

                        //Handles theme layout
                        val currentLayout = findViewById(R.id.main_layout) as RelativeLayout
                        currentLayout.setBackgroundColor(Color.parseColor(fastBackground))
//                        btnPastDeals.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnBuy.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnMovie.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
                        //btnClearData.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))

                                 controller.insert_deal(movieURL.toString(), fastTitle.toString(), fastPrice.toString(), fastDescription.toString(), specURL.toString(), buyURL.toString(), toggleChecked, response.deal.photos[0], fastBackground.toString()  )
                        controller.list_all_deals()




                    }
                }
            }
            override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
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
            val btnToggleNot = dialog.findViewById(R.id.tglNotifications)
            val btnDialogBack = dialog.findViewById(R.id.btnDialogueBack)
            val clearData = dialog.findViewById(R.id.btnClearData)

            btnToggleNot.setOnClickListener{

                if(toggleChecked.equals("True")) {
                    toggleChecked = "False"
                }
                else
                {
                    toggleChecked = "True"

                }

                    }




            btnDialogBack.setOnClickListener {
            dialog.cancel()
            }

        }

    fun loadingScreen()
    {
        if(txtName.text.toString().trim().length == 0)
        {
            val dialog = Dialog(this@MainActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.loading_layout)
            dialog.show()


            //Call function for data call
            fetchData()

            dialog.cancel()
        }

    }



}
