package com.example.autos.ui.activities

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autos.R
import com.example.autos.adapters.MessageAdapter
import com.example.autos.adapters.PostAdapter
import com.example.autos.api.ApiInterface
import com.example.autos.data.*
import com.example.autos.helpers.SocketHandler
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.socket.emitter.Emitter
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatActivity : AppCompatActivity() {
    lateinit var otherUser: TextView
    lateinit var rvMessages: RecyclerView
    lateinit var messageAdapter: MessageAdapter
    lateinit var linearLayoutManager:LinearLayoutManager
    lateinit var tvMessage:TextView
    lateinit var btnSend:ImageButton
    lateinit var message: Message
    var messageList :MutableList<Message> = ArrayList()
    lateinit var data:JSONObject




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(this.resources.getColor(R.color.white))
        }
        WindowInsetsControllerCompat(window, View(this) ).isAppearanceLightStatusBars = true
        otherUser=findViewById(R.id.tv_other_user)
        val other_user = intent.getStringExtra(OTHER_USER_NAME)

        val chatId=intent.getStringExtra(CHAT_ID)
        otherUser.text=other_user.toString()
        rvMessages=findViewById(R.id.rv_messages)
        tvMessage=findViewById(R.id.tv_message)
        btnSend=findViewById((R.id.btn_send))
        rvMessages.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        rvMessages.layoutManager=linearLayoutManager

        val apiInterface = ApiInterface.create()
        apiInterface.getMessages(chatId).enqueue(object : Callback<MutableList<Message>> {
            override fun onResponse(call: Call<MutableList<Message>>, response: Response<MutableList<Message>>) {

                messageList.addAll(response.body()!!)


            }

            override fun onFailure(call: Call<MutableList<Message>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        btnSend.setOnClickListener{
            sendMsg()
        }

        messageAdapter= MessageAdapter(applicationContext,messageList )
        messageAdapter.notifyDataSetChanged()
        rvMessages.adapter=messageAdapter
        SocketHandler.setSocket()
        val mSocket= SocketHandler.getSocket()
        mSocket.connect()
        mSocket.on("recieve-message",Emitter.Listener {args ->
            data= args[0] as JSONObject
            println(data)
            println(data::class.java.typeName)


            message= Message(data.getJSONObject("message").get("chatId").toString(),data.getJSONObject("message").get("senderId").toString(),data.getJSONObject("message").get("text").toString())
            messageAdapter.setMsgList(message)

        })


}


    fun sendMsg(){
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val senderId = sharedPreferences.getString("userId", null)
        SocketHandler.setSocket()
        val mSocket= SocketHandler.getSocket()
        mSocket.connect()

        val other_user_id=intent.getStringExtra(OTHER_USER_ID)
        val apiInterface = ApiInterface.create()
        val chatId=intent.getStringExtra(CHAT_ID)
        val paramObject = JSONObject()
        val data=JSONObject()
        paramObject.put("text",tvMessage.text.toString());
        paramObject.put("chatId",chatId);
        paramObject.put("senderId",senderId);
        data.put("message",paramObject)
        data.put("receiverId",other_user_id)
        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(paramObject.toString()) as JsonObject
        apiInterface.addMessages(gsonObject).enqueue(object : Callback<Message> {

            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                mSocket.emit("send-message",data)


                tvMessage.text=""

            }

            override fun onFailure(call: Call<Message>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}