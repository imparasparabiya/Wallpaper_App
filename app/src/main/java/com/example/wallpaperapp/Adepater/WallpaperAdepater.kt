package com.example.wallpaperapp.Adepater

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.Modal.HitsItem
import com.example.wallpaperapp.MainActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.WallpaperScreenActivity
import com.example.wallpaperapp.databinding.WallpapertileBinding

class WallpaperAdepater(val mainActivity: MainActivity, val wallpaperList: List<HitsItem?>?): RecyclerView.Adapter<WallpaperAdepater.WallpaperViewHolder>() {

    class WallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var binding : WallpapertileBinding = WallpapertileBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.wallpapertile, parent, false)

        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wallpaperList!!.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.binding.imgWallpaper.setOnClickListener {
            val intent = Intent(mainActivity, WallpaperScreenActivity::class.java)
            intent.putExtra("img", wallpaperList?.get(position)?.largeImageURL)
            mainActivity.startActivity(intent)
        }
        Glide.with(mainActivity).load(wallpaperList?.get(position)?.webformatURL).into(holder.binding.imgWallpaper)
    }
}