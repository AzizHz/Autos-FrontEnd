package com.example.autos.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import com.example.autos.R
import com.example.autos.api.ApiInterface
import com.example.autos.data.ResponseLogin
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplachScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)


        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(this.resources.getColor(R.color.gray))
        }
        WindowInsetsControllerCompat(window, View(this) ).isAppearanceLightStatusBars = true
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val password = sharedPreferences.getString("Password", null)
        val email = sharedPreferences.getString("Email", null)


        val apiInterface = ApiInterface.create()

        val paramObject = JSONObject()
        paramObject.put("Email", email);
        paramObject.put("Password", password);
        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(paramObject.toString()) as JsonObject
        apiInterface.getUser(gsonObject).enqueue(object : Callback<ResponseLogin> {

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val responseBody = response.body()
                handler = Handler()
                if(response.code()==200){
                handler.postDelayed({
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)}
                else{
                    handler.postDelayed({
                        val intent = Intent(applicationContext, LoginAndRegisterActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 3000)
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d("STATE", t.toString())
                handler = Handler()
                handler.postDelayed({
                    val intent = Intent(applicationContext, LoginAndRegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)

            }

        })

    }
}