package com.example.autos.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.WindowInsetsControllerCompat
import com.example.autos.R
import com.example.autos.data.DESCRIPTION
import com.example.autos.data.ID
import com.example.autos.data.TITLE
import com.example.autos.data.USER

class DetailActivity : AppCompatActivity() {
    lateinit var postTitle : TextView
    lateinit var postDetail:TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(this.resources.getColor(R.color.white))
        }
        WindowInsetsControllerCompat(window, View(this) ).isAppearanceLightStatusBars = true
        postTitle=findViewById(R.id.postTitle)
        postDetail=findViewById(R.id.postDetail)
        val title = intent.getStringExtra(TITLE)
        var detail=intent.getStringExtra(DESCRIPTION)
        var owner=intent.getStringExtra(USER)
        postTitle.text = title.toString()
        postDetail.text=detail.toString()


    }
}