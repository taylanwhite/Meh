package com.example.taylanwhite.meh


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.taylanwhite.meh.model.Deal
import com.squareup.picasso.Picasso

class DealAdapter(private val dealList: List<Deal>) : RecyclerView.Adapter<DealAdapter.MyViewHolder>() {

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
        val deal = dealList[position]
        App.picasso.load(deal.photos[0]).into(holder.deal_image);
        holder.deal_name.text = deal.title
        holder.deal_features.text = deal.features

    }

    override fun getItemCount(): Int {
        return dealList.size
    }
}