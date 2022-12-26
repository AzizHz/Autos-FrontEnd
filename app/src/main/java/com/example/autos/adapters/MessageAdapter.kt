package com.example.autos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autos.R
import com.example.autos.data.Message
import com.example.autos.data.Post

class MessageAdapter(val context: Context, var messageList:MutableList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder?>()  {
    val ITEM_LEFT=1
    val ITEM_RIGHT=2

    inner class LeftMsgHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val leftMessage: TextView


        init {
            leftMessage = itemView.findViewById(R.id.tv_left_item_msg)
        }
    }
    inner class RightMsgHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rightMessage: TextView


        init {
            rightMessage = itemView.findViewById(R.id.tv_right_item_msg)
        }
    }


    fun setMsgList(message: Message){
        this.messageList.add(messageList.lastIndex+1,message)
        notifyItemInserted(messageList.lastIndex+1)
    }

    override fun getItemViewType(position: Int): Int {
        val message=messageList[position]
        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val connectedUser = sharedPreferences.getString("userId", null)
        return if(message.senderId==connectedUser){
            ITEM_RIGHT
        }else{
            ITEM_LEFT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==ITEM_RIGHT){
            val view:View=LayoutInflater.from(context).inflate(R.layout.message_item_right,parent,false)
        RightMsgHolder(view)
        }
        else{
            val view:View=LayoutInflater.from(context).inflate(R.layout.message_item_left,parent,false)
            LeftMsgHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message=messageList[position]
        if(holder.javaClass==RightMsgHolder::class.java){
            val viewHolder=holder as RightMsgHolder
            viewHolder.rightMessage.text=message.text
        }
        else{
            val viewHolder=holder as LeftMsgHolder
            viewHolder.leftMessage.text=message.text

        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }


}