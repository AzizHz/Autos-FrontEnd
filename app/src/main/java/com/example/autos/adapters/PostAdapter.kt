package com.example.autos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autos.R
import com.example.autos.data.*
import com.example.autos.ui.activities.DetailActivity

class PostAdapter(val context: Context, var postList:List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val postTitle: TextView
        val postDescription: TextView

        init {
            postTitle = itemView.findViewById(R.id.tv_Post_Title)
            postDescription = itemView.findViewById(R.id.tv_Post_Description)
        }
    }

    fun setFilteredList(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.post_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postTitle.text=postList[position].Title.toString()
        holder.postDescription.text=postList[position].Description.toString()
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {

                putExtra(TITLE, postList[position].Title.toString())
                putExtra(USER, postList[position].User.toString())
                putExtra(ID, postList[position]._id.toString())


                putExtra(DESCRIPTION, postList[position].Description.toString())


            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }


}