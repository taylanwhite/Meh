package com.example.taylanwhite.meh.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.taylanwhite.meh.presenter.DealAdapter
import com.example.taylanwhite.meh.presenter.MehService
import com.example.taylanwhite.meh.R
import com.example.taylanwhite.meh.presenter.RecyclerTouchListener
import com.example.taylanwhite.meh.model.Deal
import com.example.taylanwhite.meh.model.DealObject
import com.example.taylanwhite.meh.view.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_past_deals.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

open class PastDeals : AppCompatActivity() {

    val dealList = ArrayList<Deal>()
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: DealAdapter
    var toggleChecked = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_deals)

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
        val mTitleBack = mCustomView.findViewById(R.id.txtBack) as TextView
        mTitleBack.text = "Back"

        mTitleSettings.setOnClickListener {
            settingsFun()

        }
        mTitleBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


        recyclerView = findViewById(R.id.recycler_view) as RecyclerView

        mAdapter = DealAdapter(dealList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

        setDealAdapter()



        recyclerView.addOnItemTouchListener(RecyclerTouchListener(applicationContext, recyclerView, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                val deal = dealList[position]
                Toast.makeText(applicationContext, deal.title + " is selected!", Toast.LENGTH_SHORT).show();

            }

            override fun onLongClick(view: View, position: Int) {

            }
        }))



    }

    fun settingsFun()
    {
//            val container = findViewById(R.id.main_layout) as RelativeLayout
//
//            val view = View.inflate(this, R.layout.custom_dialogue_layout, container)
        val dialog = Dialog(this@PastDeals)
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


    fun setDealAdapter(){

        MehService.retrofit.getDailyProduct().enqueue(object: Callback<DealObject> {
            override fun onFailure(call: Call<DealObject>?, t: Throwable?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<DealObject>?, response: Response<DealObject>?) {

                if (response?.isSuccessful ?: false) {
                    response?.body()!!.let { response ->
                        val fastTitle = response.deal.title
                        val fastPrice = "Price: $" + response.deal.items[0].price.toString()
                        val fastDescription = response.deal.features
                        val buyURL = response.deal.url
                        val movieURL = response.video.url
                        val specURL = response.deal.topic?.url
                        val fastBackground = response.deal.theme?.backgroundColor
                        var fastPicture =  response.deal.photos


                        var deal = Deal(fastTitle, fastPrice, fastPicture)
                        dealList.add(deal)
                        deal = Deal(fastTitle, fastPrice, fastPicture)
                        dealList.add(deal)
                       // deal = Deal()
//                        deal = Deal(fastTitle, fastDescription)
//                        dealList.add(deal)
//                        deal = Deal(fastTitle, fastDescription)
//                        dealList.add(deal)
//                        deal = Deal(fastTitle, fastDescription)
//                        dealList.add(deal)
//                        deal = Deal(fastTitle, fastDescription)
//                        dealList.add(deal)

                        mAdapter.notifyDataSetChanged()

                        val currentLayout = findViewById(R.id.past_layout) as RelativeLayout
                        currentLayout.setBackgroundColor(Color.parseColor(fastBackground))
                    }


                }
            }
        })
    }}