package com.example.taylanwhite.meh.view

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.taylanwhite.meh.App
import com.example.taylanwhite.meh.R
import com.example.taylanwhite.meh.model.DealObject
import com.example.taylanwhite.meh.presenter.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_past.*
import java.util.*

class PastDealsMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_past)

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





        mTitleSettings.setOnClickListener {
            settingsFun()

        }
        mTitlePastDeals.setOnClickListener {

            val intent = Intent(this, PastDeals::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }

//                intent.putExtra("video", deal.video.url)
//                intent.putExtra("title", deal.deal.title)
//                intent.putExtra("price", deal.deal.items[0].price)
//                intent.putExtra("features", deal.deal.features)
//                intent.putExtra("buy", deal.deal.url)
//                intent.putExtra("theme", deal.deal.theme?.backgroundColor)
//                if(deal.deal.photos[0] != null) intent.putExtra("photo1", deal.deal.photos[0])
//                if(deal.deal.photos[1] != null) intent.putExtra("photo2", deal.deal.photos[1])
//                if(deal.deal.photos[2] != null) intent.putExtra("photo3", deal.deal.photos[2])
//                if(deal.deal.photos[3] != null) intent.putExtra("photo4", deal.deal.photos[3])
//                if(deal.deal.photos[4] != null) intent.putExtra("photo5", deal.deal.photos[4])
//

        // recreate database here
        val bundle = intent.extras
        val dealId = bundle.getString("id")
        val video = bundle.getString("video")
        val title = bundle.getString("title")
        val price = bundle.getString("price")
        val feature = bundle.getString("features")
        val buy = bundle.getString("buy")
        val theme = bundle.getString("theme")
        val specs = bundle.getString("specs")
        val photos = ArrayList<String>()
        photos.add(bundle.getString("photo1"))
        photos.add(bundle.getString("photo2"))
        photos.add(bundle.getString("photo3"))
        photos.add(bundle.getString("photo4"))
        photos.add(bundle.getString("photo5"))
        var i = 1


//        val myDatabaseHelper = DatabaseHelper(this@PastDealsMain)
//        val query = "SELECT * FROM DEALTABLE"

      //  val cursor = this.readableDatabase.rawQuery(query, null)



        val fastTitle = title//response.deal.title
        val  fastPrice = price//response.deal.items[0].price.toString()
        val fastDescription = feature//response.deal.features
        val buyURL = buy//response.deal.url
        val movieURL = video//response.video.url
        val specURL = specs//response.deal.topic?.url
        val fastBackground = theme//response.deal.theme?.backgroundColor
        txtNamePast.text = fastTitle

        txtPricePast.text = fastPrice
        txtDescriptionPast.text = fastDescription
        //imageDeal.setImageResource() =
        //response.deal.items[0].photo?.
        App.picasso.load(photos[0]).into(imageDealPast)
        //Handles photos
        imageDealPast.setOnClickListener {
            if (i >= photos.size)
            {
                i = 0
            }
            App.picasso.load(photos[i]).into(imageDealPast)

            i++

        }

        //Handles button for going to meh website
        btnBuyPast.setOnClickListener {

            try {
                val intent = Intent(Intent.ACTION_VIEW)
                if(buyURL != null) intent.data = Uri.parse(buyURL)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }

        btnMoviePast.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(movieURL)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
        btnBackPast.setOnClickListener {
            val intent = Intent(this, PastDeals::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
        }

        txtMoreSpecsPast.setOnClickListener{

            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(specURL)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }



        //Handles theme layout
        val currentLayout = findViewById(R.id.main_past_layout) as RelativeLayout
        currentLayout.setBackgroundColor(Color.parseColor(fastBackground))
//                        btnPastDeals.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnBuy.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
//                        btnMovie.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))
        //btnClearData.setBackgroundColor(Color.parseColor(response.deal.theme?.accentColor))





    }


    fun settingsFun()
    {
//            val container = findViewById(R.id.main_layout) as RelativeLayout
//
//            val view = View.inflate(this, R.layout.custom_dialogue_layout, container)

        val dialog = Dialog(this@PastDealsMain)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialogue_layout)
        dialog.show()

        //val btnDialogBack = findViewById(view.id.)
        val btnToggleNot = dialog.findViewById(R.id.tglNotifications)
        val btnDialogBack = dialog.findViewById(R.id.btnDialogueBack)
        val btnClearData = dialog.findViewById(R.id.btnClearData)



//
//                btnClearData.setOnClickListener {
//                    db.execSQL("TRUNCATE table" + TABLE_NAME)
//                    db.close()
//                }




        btnDialogBack.setOnClickListener {
            dialog.cancel()
        }

    }
}
