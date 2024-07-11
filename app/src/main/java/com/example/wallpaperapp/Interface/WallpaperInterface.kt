package com.example.wallpaperapp.Interface

import com.example.wallpaperapp.WallpaperModal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperInterface {

    @GET("api/")

    fun searchWallpaper(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String
    ): Call<WallpaperModal>

}