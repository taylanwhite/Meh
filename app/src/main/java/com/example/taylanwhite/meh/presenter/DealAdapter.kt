package com.example.taylanwhite.meh.presenter


import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.taylanwhite.meh.App
import com.example.taylanwhite.meh.R

import com.example.taylanwhite.meh.model.Deal
import com.example.taylanwhite.meh.model.DealObject

class DealAdapter(private val dealList: List<DealObject>) : RecyclerView.Adapter<DealAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var deal_name: TextView
        var deal_features: TextView
        var deal_image: ImageView
        var deal_layout: RelativeLayout

        init {
            deal_name = view.findViewById(R.id.deal_name) as TextView
            deal_features = view.findViewById(R.id.deal_features) as TextView
            deal_image = view.findViewById(R.id.deal_image) as ImageView
            deal_layout = view.findViewById(R.id.relativeLayout) as RelativeLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.deal_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setBackgroundColor(0xffffff)
        //holder.deal_layout.RelativeLayout = .setBackgroundColor(Color.parseColor(fastBackground))
        holder.deal_name.text = dealList[position].deal.title
        holder.deal_features.text = "Price:" + dealList[position].deal.items[0].price.toString()
        var image =  dealList[position].deal.photos
        App.picasso.load(image[0]).into(holder.deal_image)

    }

    override fun getItemCount(): Int {
        return dealList.size
    }
}