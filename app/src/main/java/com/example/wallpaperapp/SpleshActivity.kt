package com.example.wallpaperapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wallpaperapp.databinding.ActivityWallpaperScreenBinding

class SpleshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splesh)


        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)



    }
}