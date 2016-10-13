package com.example.taylanwhite.meh.presenter

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.NotificationCompat
import android.widget.RelativeLayout
import com.example.taylanwhite.meh.App
import com.example.taylanwhite.meh.R
import com.example.taylanwhite.meh.model.Deal
import com.example.taylanwhite.meh.model.DealObject
import com.example.taylanwhite.meh.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



/**
 * Created by taylanwhite on 10/5/16.
 */
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        service()

    }


}

fun service(){
    //needs to be in own function
    MehService.retrofit.getDailyProduct().enqueue(object: Callback<DealObject> {
        override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
            throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onResponse(call: Call<DealObject>?, response: Response<DealObject>?) {

            if (response?.isSuccessful ?: false) {
                response?.body()!!.let { response ->
                    val fastTitle = response.deal.title
                    val fastPrice = "Price: $" + response.deal.items[0].price.toString()

                        val mBuilder = NotificationCompat.Builder(App.instance).setAutoCancel(true).setSmallIcon(R.mipmap.notification_icon).setContentTitle(fastTitle).setContentText(fastPrice)

                        val resultIntent = Intent(App.instance, MainActivity::class.java)


                        val resultPendingIntent = PendingIntent.getActivity(
                                App.instance,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT)
                        mBuilder.setContentIntent(resultPendingIntent)
                        // Sets an ID for the notification
                        val mNotificationId = 1
                        // Gets an instance of the NotificationManager service
                        val mNotifyMgr = App.instance.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
                        // Builds the notification and issues it.
                        mNotifyMgr.notify(mNotificationId, mBuilder.build())
                }

            }
        }
    })
}



//
//val mBuilder = NotificationCompat.Builder(App.instance).setAutoCancel(true).setSmallIcon(R.mipmap.notification_icon).setContentTitle(fastTitle).setContentText(fastPrice)
//
//val resultIntent = Intent(App.instance, MainActivity::class.java)
//
//
//val resultPendingIntent = PendingIntent.getActivity(
//        App.instance,
//        0,
//        resultIntent,
//        PendingIntent.FLAG_UPDATE_CURRENT)
//mBuilder.setContentIntent(resultPendingIntent)
//// Sets an ID for the notification
//val mNotificationId = 1
//// Gets an instance of the NotificationManager service
//val mNotifyMgr = App.instance.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
//// Builds the notification and issues it.
//mNotifyMgr.notify(mNotificationId, mBuilder.build())