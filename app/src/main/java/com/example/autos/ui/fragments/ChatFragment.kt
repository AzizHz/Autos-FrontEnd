package com.example.autos.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autos.R
import com.example.autos.adapters.ChatAdapter
import com.example.autos.api.ApiInterface
import com.example.autos.data.Chat
import com.example.autos.databinding.FragmentChatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatFragment:Fragment(R.layout.fragment_chat) {
    lateinit var binding: FragmentChatBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var rvchats:RecyclerView
    lateinit var ChatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentChatBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val connectedUser = sharedPreferences.getString("userId", null)
        rvchats=view.findViewById(R.id.rvChats)
        rvchats.setHasFixedSize(true)
        linearLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        rvchats.layoutManager=linearLayoutManager
        ApiInterface.create().userchats(connectedUser).enqueue(object : Callback<List<Chat>?>{
            override fun onResponse(call: Call<List<Chat>?>, response: Response<List<Chat>?>) {
                val responseBody=response.body()!!
                ChatAdapter= ChatAdapter(this@ChatFragment.requireContext(),responseBody)
                ChatAdapter.notifyDataSetChanged()
                rvchats.adapter=ChatAdapter
            }

            override fun onFailure(call: Call<List<Chat>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}