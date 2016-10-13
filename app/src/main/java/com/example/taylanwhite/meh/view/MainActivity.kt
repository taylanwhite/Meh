package com.example.taylanwhite.meh.view

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
import android.view.*
import android.widget.*
import com.dtp.simplemvp.database.DataConnection
import com.example.taylanwhite.meh.App
import com.example.taylanwhite.meh.presenter.DatabaseHelper
import com.example.taylanwhite.meh.presenter.MehService
import com.example.taylanwhite.meh.R
//import com.example.taylanwhite.meh.model.Answer
import com.example.taylanwhite.meh.model.DealObject
import com.example.taylanwhite.meh.presenter.AlarmReceiver
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    val controller = DatabaseHelper(this)
    var toggleChecked = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading_bar.visibility = View.VISIBLE

        val mActionBar = supportActionBar
        mActionBar?.setDisplayShowHomeEnabled(false)
        mActionBar?.setDisplayShowTitleEnabled(false)
        val mInflater = LayoutInflater.from(this)
        val mCustomView = mInflater.inflate(R.layout.action_bar, null)
        mActionBar?.customView = mCustomView
        mActionBar?.setDisplayShowCustomEnabled(true)
        mActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A9A9A9")))
        val mTitleSettings = mCustomView.findViewById(R.id.txtSettings) as TextView
        mTitleSettings.text = "Settings"
        val mTitlePastDeals = mCustomView.findViewById(R.id.txtBack) as TextView
        mTitlePastDeals.text = "Past Deals"
        scheduleNotification()





        mTitleSettings.setOnClickListener {
            settingsFun()

        }
        mTitlePastDeals.setOnClickListener {

            val intent = Intent(this, PastDeals::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }


        //Underline txtMoreSpecs
        txtMoreSpecs.paintFlags = txtMoreSpecs.paintFlags or Paint.UNDERLINE_TEXT_FLAG


            //
            //Call function for data call
            fetchData()
            //close dialog when filled





        loading_bar.visibility = View.GONE

    }


    fun fetchData() {
        var i = 1
        var t = 0


        //val controller = DatabaseHelper(this)

        loading_bar.visibility = View.VISIBLE

        MehService.retrofit.getDailyProduct().enqueue(object: Callback<DealObject> {

            override fun onResponse(call: Call<DealObject>?, response: Response<DealObject>?) {
                loading_bar.visibility = View.GONE

                if(response?.isSuccessful ?: false) {
                    response?.body()!!.let { response ->



                        val fastTitle = response.deal.title
                        val  fastPrice = response.deal.items[0].price.toString()
                        val fastDescription = response.deal.features
                        val buyURL = response.deal.url
                        val movieURL = response.video.url
                        val specURL = response.deal.topic?.url
                        val fastBackground = response.deal.theme?.backgroundColor
                        val fastPicture = response.deal.photos
                        txtName.text = fastTitle
                        txtPrice.text = fastPrice
                        txtDescription.text = fastDescription
                        //imageDeal.setImageResource() =
                       //response.deal.items[0].photo?.
                        response.deal.id
                       App.picasso.load(response.deal.photos[0]).into(imageDeal)


                        //Handles photos
                        imageDeal.setOnClickListener {

                            if (i >= response.deal.photos.size)
                            {
                                i = 0
                            }
                            App.picasso.load(response.deal.photos[i]).into(imageDeal)

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



                        //Handles theme layout
                        val currentLayout = findViewById(R.id.main_layout) as RelativeLayout
                        currentLayout.setBackgroundColor(Color.parseColor(fastBackground))
//                        btnPastDeals.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnBuy.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnMovie.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
                        //btnClearData.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))

                        controller.insert_deal(response.deal.id!!, movieURL.toString(), fastTitle.toString(), fastPrice, fastDescription.toString(), specURL.toString(), buyURL.toString(), fastBackground.toString()  )
                        for (item in response.deal.photos) //(t <= response.deal.photos.size)
                        {
                            controller.insert_photo(response.deal.id!!, item)

                        }
                   // remove this when finished
                            Stetho.initializeWithDefaults(this@MainActivity)


                        //controller.insert_price(fastPrice)
                      //  controller.list_all_deals()

                    }
                }
            }
            override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.

                loading_bar.visibility = View.GONE

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
            val btnClearData = dialog.findViewById(R.id.btnClearData)


            btnToggleNot.setOnClickListener{

                if(toggleChecked.equals("True")) {
                    toggleChecked = "False"
                }
                else
                {
                    toggleChecked = "True"

                }

                    }
//
//                btnClearData.setOnClickListener {
//                    db.execSQL("TRUNCATE table" + TABLE_NAME)
//                    db.close()
//                }




            btnDialogBack.setOnClickListener {
            dialog.cancel()
            }

        }

    fun scheduleNotification() {
//         The time at which the alarm will be scheduled. Here the alarm is scheduled for 1 day from the current time.
//         We fetch the current time in milliseconds and add 1 day's time
//         i.e. 24*60*60*1000 = 86,400,000 milliseconds in a day.
        //val time = GregorianCalendar().timeInMillis + 24 * 60 * 60 * 1000

        val cur_cal = GregorianCalendar()
        cur_cal.timeInMillis = System.currentTimeMillis()

        val cal = GregorianCalendar()
        cal.add(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR))
        cal.set(Calendar.HOUR_OF_DAY, 24)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        // Create an Intent and set the class that will execute when the Alarm triggers. Here we have
        // specified AlarmReceiver in the Intent. The onReceive() method of this class will execute when the broadcast from your alarm is received.
        val intentAlarm = Intent(this, AlarmReceiver::class.java)

        // Get the Alarm Service.
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


//         Set the alarm for a particular time.
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.timeInMillis, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT))
        //Toast.makeText(this, "Notification Scheduled for Tomorrow", Toast.LENGTH_LONG).show()
    }





}
