package com.example.wallpaperapp.Adepater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.HitsItem
import com.example.wallpaperapp.MainActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.WallpapertileBinding

class WallpaperAdepater(val homeList: MainActivity, val wallpaperList: List<HitsItem?>): RecyclerView.Adapter<WallpaperAdepater.WallpaperViewHolder>() {

    class WallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var binding : WallpapertileBinding = WallpapertileBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.wallpapertile, parent, false)

        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Glide.with(homeList).load(wallpaperList[position]?.previewURL).into(holder.binding.imgWallpaper)
    }
}