package com.example.autos.ui.activities


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.autos.R
import com.example.autos.ui.fragments.ChatFragment
import com.example.autos.ui.fragments.HomePageFragment
import com.example.autos.ui.fragments.ProfileFragment
import com.example.autos.ui.fragments.RegisterFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(this.resources.getColor(R.color.white))
        }
        WindowInsetsControllerCompat(window, View(this) ).isAppearanceLightStatusBars = true
        replaceFragment(HomePageFragment())
        bottomNavigationView=findViewById(R.id.bottomNav)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(HomePageFragment())
                R.id.profile->replaceFragment(ProfileFragment())
                R.id.chat->replaceFragment(ChatFragment())
                else->{}
            }
            true
        }
    }
    private fun replaceFragment(fragment:Fragment){

        val fragmentManager=supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace( R.id.homeFragmentContainer ,fragment)
        fragmentTransaction.commit()


    }


    fun goToProfile (view: android.view.View) {
        val profileFragment= ProfileFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.homeFragmentContainer, profileFragment).addToBackStack(null)
        transaction.commit()
    }
}

