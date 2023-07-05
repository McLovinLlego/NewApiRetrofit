package com.example.newapiretrofit.tiempo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newapiretrofit.R

class TiempoAdapter(private val itemList: List<Metereologia>, private val limit:Boolean ) :
    RecyclerView.Adapter<TiempoAdapter.ViewHolder>(){


    class ViewHolder (view: View) :  RecyclerView.ViewHolder(view) {
        val temp: TextView  = view.findViewById(R.id.txtTemperatura)
        val humidity: TextView =  view.findViewById(R.id.txtHumedad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_tiempo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (limit){
            if (itemList.size > 3) 3 else itemList.size
        } else {
            itemList.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.temp.text = item.temp
        holder.temp.text = item.humidity
    }
}