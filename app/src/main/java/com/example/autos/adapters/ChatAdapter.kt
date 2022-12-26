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
import com.example.autos.api.ApiInterface
import com.example.autos.data.*
import com.example.autos.ui.activities.ChatActivity
import com.example.autos.ui.activities.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatAdapter(val context: Context, val chatList:List<Chat>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userName: TextView


        init {
            userName=itemView.findViewById(R.id.username)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.chat_singel_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        lateinit var userId:String
        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val connectedUser = sharedPreferences.getString("userId", null)
        userId = if(chatList[position].members!![0]==connectedUser){
            chatList[position].members!![1]
        } else{
            chatList[position].members!![0]
        }

        val apiInterface = ApiInterface.create()
        apiInterface.getIndexUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                holder.userName.text=response.body()!!.FullName
                holder.itemView.setOnClickListener{
                    val intent = Intent(holder.itemView.context, ChatActivity::class.java)
                    intent.apply {

                        putExtra(CHAT_ID, chatList[position]._id)
                        putExtra(OTHER_USER_ID, response.body()!!._id)
                        putExtra(OTHER_USER_NAME, response.body()!!.FullName)





                    }
                    holder.itemView.context.startActivity(intent)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                holder.userName.text="error"
            }
        })




    }

    override fun getItemCount(): Int {
        return chatList.size
    }



}
