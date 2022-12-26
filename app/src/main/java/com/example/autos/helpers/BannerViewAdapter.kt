package com.example.autos.helpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autos.R

class BannerViewAdapter:RecyclerView.Adapter<BannerViewHolder>()  {

    private val list = mutableListOf<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.banner_item,parent,false)
    return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.imageResource=list[position]
        holder.updateView()
    }
fun reload(list:MutableList<Int>){
    this.list.clear()
    this.list.addAll(list)
    notifyDataSetChanged()

}
    override fun getItemCount(): Int {
       return  list.size
    }
}