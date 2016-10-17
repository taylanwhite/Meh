package com.example.taylanwhite.meh.presenter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.taylanwhite.meh.App
import com.example.taylanwhite.meh.R

import com.example.taylanwhite.meh.model.Deal

class DealAdapter(private val dealList: List<String>) : RecyclerView.Adapter<DealAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var deal_name: TextView
        var deal_features: TextView
        var deal_image: ImageView

        init {
            deal_name = view.findViewById(R.id.deal_name) as TextView
            deal_features = view.findViewById(R.id.deal_features) as TextView
            deal_image = view.findViewById(R.id.deal_image) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.deal_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.deal_name.text = dealList[0]
        holder.deal_features.text = dealList[1]
        App.picasso.load(dealList[2]).into(holder.deal_image)

    }

    override fun getItemCount(): Int {
        return dealList.size
    }
}