package com.example.wallpaperapp.Client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WallpaperClient {

    companion object{
        var retrofit: Retrofit? = null
        val BASE_Url = "https://pixabay.com/"
        fun getWallpeparClient():Retrofit?{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }
    }
}