package com.example.autos.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.autos.R
import com.example.autos.ui.fragments.EmailVerificationFragment
import com.example.autos.ui.fragments.LoginFragment
import com.example.autos.ui.fragments.RegisterFragment


class LoginAndRegisterActivity : AppCompatActivity() {

    private val loginFragment=LoginFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_and_register)



        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(this.resources.getColor(R.color.gray))
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,loginFragment)
            commit()

        }
    }


    fun goToRegister (view: android.view.View) {
        val registerFragment=RegisterFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainer, registerFragment).addToBackStack(null)
        transaction.commit()
    }
    fun goToLogin (view: android.view.View) {
        val loginFragment=LoginFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainer, loginFragment).addToBackStack(null)
        transaction.commit()
    }
    fun goToEv (view: android.view.View) {
        val emailVerificationFragment=EmailVerificationFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainer, emailVerificationFragment).addToBackStack(null)
        transaction.commit()
    }
    fun homePage() {
        val intent = Intent(this, HomeActivity::class.java)
        // start your next activity
        startActivity(intent)
        finish()
    }
}