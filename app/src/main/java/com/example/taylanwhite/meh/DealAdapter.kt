package com.example.taylanwhite.meh


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.taylanwhite.meh.model.Deal

class DealAdapter(private val dealList: List<Deal>) : RecyclerView.Adapter<DealAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dealName: TextView
        var dealPrice: TextView

        init {
            dealName = view.findViewById(R.id.deal_name) as TextView
            dealPrice = view.findViewById(R.id.deal_price) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.deal_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val deal = dealList[position]
        holder.dealName.text = deal.title
        holder.dealPrice.text = deal.features
    }

    override fun getItemCount(): Int {
        return dealList.size
    }
}