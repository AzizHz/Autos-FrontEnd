package com.example.autos.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.autos.R
import com.example.autos.databinding.FragmentEmailVerificationBinding
import com.google.android.material.textfield.TextInputEditText

class EmailVerificationFragment:Fragment(R.layout.fragment_email_verification){
lateinit var binding: FragmentEmailVerificationBinding
lateinit var et1: TextInputEditText;
lateinit var et2: TextInputEditText;
lateinit var et3: TextInputEditText;
lateinit var et4: TextInputEditText;
lateinit var et5: TextInputEditText;
lateinit var et6: TextInputEditText;




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding= FragmentEmailVerificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et1=view.findViewById(R.id.et1)
        et2=view.findViewById(R.id.et2)
        et3=view.findViewById(R.id.et3)
        et4=view.findViewById(R.id.et4)
        et5=view.findViewById(R.id.et5)
        et6=view.findViewById(R.id.et6)


        activationNumber()


    }


private fun activationNumber(){
    et1.addTextChangedListener( object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(!s.toString().trim().isEmpty()){
                et2.requestFocus()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    });
    et2.addTextChangedListener( object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(!s.toString().trim().isEmpty()){
                et3.requestFocus()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    });
    et3.addTextChangedListener( object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(!s.toString().trim().isEmpty()){
                et4.requestFocus()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    });
    et4.addTextChangedListener( object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(!s.toString().trim().isEmpty()){
                et5.requestFocus()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    });
    et5.addTextChangedListener( object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(!s.toString().trim().isEmpty()){
                et6.requestFocus()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    });

}



}