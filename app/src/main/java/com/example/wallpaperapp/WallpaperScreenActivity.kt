package com.example.wallpaperapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.wallpaperapp.databinding.ActivityWallpaperScreenBinding

class WallpaperScreenActivity : AppCompatActivity() {
    lateinit var wallpaperScreenBinding: ActivityWallpaperScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        wallpaperScreenBinding = ActivityWallpaperScreenBinding.inflate(layoutInflater)
        setContentView(wallpaperScreenBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val img = intent.getStringExtra("img")
        Glide.with(this).load(img).into(wallpaperScreenBinding.imgPic)

    }
}