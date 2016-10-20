package com.example.taylanwhite.meh.view

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.taylanwhite.meh.App
import com.example.taylanwhite.meh.R
import com.example.taylanwhite.meh.model.Deal
import com.example.taylanwhite.meh.model.DealObject
import com.example.taylanwhite.meh.presenter.DatabaseHelper
import com.example.taylanwhite.meh.presenter.DealAdapter
import com.example.taylanwhite.meh.presenter.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_past.*
import kotlinx.android.synthetic.main.deal_list_row.*
import kotlinx.android.synthetic.main.loading_layout.view.*
import java.util.*

open class PastDeals : AppCompatActivity() {

    val dealList = ArrayList<DealObject>()

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
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                val intent = Intent(this@PastDeals, PastDealsMain::class.java)


                deal.deal.topic?.url

                intent.putExtra("id", deal.deal.id)
                intent.putExtra("video", deal.video.url)
                intent.putExtra("title", deal.deal.title)
                intent.putExtra("price", deal.deal.items[0].price)
                intent.putExtra("features", deal.deal.features)
                intent.putExtra("specs", deal.deal.topic?.url)
                intent.putExtra("buy", deal.deal.url)
                intent.putExtra("theme", deal.deal.theme?.backgroundColor)
                if(deal.deal.photos[0] != null) intent.putExtra("photo1", deal.deal.photos[0])
                if(deal.deal.photos[1] != null) intent.putExtra("photo2", deal.deal.photos[1])
                if(deal.deal.photos[2] != null) intent.putExtra("photo3", deal.deal.photos[2])
                if(deal.deal.photos[3] != null) intent.putExtra("photo4", deal.deal.photos[3])
                if(deal.deal.photos[4] != null) intent.putExtra("photo5", deal.deal.photos[4])



                //Add your data from getFactualResults method to bundle
                //Add the bundle to the intent
                startActivity(intent)
             //   Toast.makeText(applicationContext, deal , Toast.LENGTH_SHORT).show()
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

        val myDatabaseHelper = DatabaseHelper(this@PastDeals)
        val data =  myDatabaseHelper.get_all_deals()


        for (item in data) {
            dealList.add(item)
            //reverse arraylist to show todays deal first
            Collections.reverse(dealList)

            mAdapter.notifyDataSetChanged()

//            val currentLayout = findViewById(R.id.past_layout) as RelativeLayout
//            currentLayout.setBackgroundColor(Color.parseColor(fastBackground.toString()))
        }

                    }



    }
