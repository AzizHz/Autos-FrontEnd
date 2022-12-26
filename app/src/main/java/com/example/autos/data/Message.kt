package com.example.autos.data

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("chatId")
    var chatId:String,
    @SerializedName("senderId")
    var senderId:String,
    @SerializedName("text")
    var text:String
)
