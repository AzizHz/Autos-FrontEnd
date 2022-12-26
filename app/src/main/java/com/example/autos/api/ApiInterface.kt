package com.example.autos.api

import com.example.autos.data.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("User/login")
    fun getUser(@Body body: JsonObject) : Call<ResponseLogin>

    @GET("Post/")
    fun getPosts() : Call<List<Post>>

    @GET("Message/{chatId}")
    fun getMessages(@Path("chatId") userId: String?):Call<MutableList<Message>>


    @POST("Message/")
    fun addMessages(@Body body: JsonObject) :Call<Message>



    @GET("User/{userId}")
    fun getIndexUser(@Path("userId") userId: String?) : Call<User>

    @GET("Chat/{userId}")
    fun userchats(@Path("userId") userId: String?):Call<List<Chat>>

    companion object {

        var BASE_URL = "http://172.29.176.1:9090/"
        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}