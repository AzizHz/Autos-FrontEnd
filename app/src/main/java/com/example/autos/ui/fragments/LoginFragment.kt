package com.example.autos.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.autos.R
import com.example.autos.api.ApiInterface
import com.example.autos.data.ResponseLogin
import com.example.autos.data.User
import com.example.autos.databinding.FragmentLoginBinding
import com.example.autos.helpers.SocketHandler
import com.example.autos.ui.activities.LoginAndRegisterActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment:Fragment(R.layout.fragment_login) {
lateinit var binding: FragmentLoginBinding
lateinit var etEmail:TextInputEditText
lateinit var etPassword:TextInputEditText
lateinit var signin:Button
lateinit var onlineUser:Array<JsonObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etEmail=view.findViewById(R.id.etEmail)
        etPassword=view.findViewById(R.id.etPassword)
        signin=view.findViewById(R.id.sign_in_btn)
        signin.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin(){ if (validate()){

        SocketHandler.setSocket()
        val mSocket= SocketHandler.getSocket()


        val apiInterface = ApiInterface.create()

        val paramObject = JSONObject()
        paramObject.put("Email",etEmail.text.toString());
        paramObject.put("Password",etPassword.text.toString());
        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(paramObject.toString()) as JsonObject
        apiInterface.getUser(gsonObject).enqueue(object : Callback<ResponseLogin> {

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val responseBody = response.body()!!
mSocket.connect()
                mSocket.emit("new-user-add",responseBody.Client._id)

                getActivity()
                    ?.getSharedPreferences("shared", Context.MODE_PRIVATE)
                    ?.edit()
                    ?.putString("Password",etPassword.text.toString())
                    ?.putString("Email",etEmail.text.toString())
                    ?.putString("Token",responseBody.token)
                    ?.putString("userId", responseBody.Client._id)
                    ?.putString("FullName",responseBody.Client.FullName)
                    ?.apply();
                (activity as LoginAndRegisterActivity).homePage()


            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d("STATE", t.toString())


            }

        })

    }


    }




    private fun validate(): Boolean {


        if (etEmail.text!!.isEmpty()) {

            return false
        }

        if (etPassword.text!!.isEmpty()) {

            return false
        }

        return true
    }

}