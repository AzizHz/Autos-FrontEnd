package com.example.autos.ui.fragments


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.autos.R
import com.example.autos.databinding.FragmentProfileBinding
import com.example.autos.ui.activities.HomeActivity
import com.example.autos.ui.activities.LoginAndRegisterActivity

class ProfileFragment :Fragment(R.layout.fragment_profile){
    lateinit var binding: FragmentProfileBinding
    lateinit var tvEmail:TextView
    lateinit var tvFullName:TextView
    lateinit var logout_section:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val email = sharedPreferences?.getString("Email", null)
        val fullName=sharedPreferences?.getString("FullName",null)
        tvEmail=view.findViewById(R.id.tvEmail)
        tvFullName=view.findViewById(R.id.tvFullName)
        tvFullName.text=fullName
        tvEmail.text=email
        logout_section=view.findViewById(R.id.logout_section)
        logout_section.setOnClickListener{
            logout()
        }


    }

    fun logout(){
        val sharedPreferences = activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.clear()?.apply()
        val intent = Intent(requireContext(), LoginAndRegisterActivity::class.java)
        startActivity(intent)



    }

}