package com.example.autos.helpers

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.autos.R
import java.lang.ref.WeakReference

class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    private val view:WeakReference<View> = WeakReference(itemView)
    private lateinit var bannerView: ImageView
    var imageResource=0
    init {
       findView()
    }
    private fun findView(){
        view.get()?.let {
            bannerView=it.findViewById(R.id.bannerImage)
        }
    }
    fun updateView(){
        bannerView.setImageResource(imageResource)
    }
}