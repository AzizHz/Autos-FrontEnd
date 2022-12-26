package com.example.autos.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @SerializedName("message")
    var message:String,
    @SerializedName("Client")
    var Client:User,
    @SerializedName("token")
    var token:String
)
