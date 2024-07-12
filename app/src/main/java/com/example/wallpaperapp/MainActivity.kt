package com.example.wallpaperapp

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapp.Adepater.WallpaperAdepater
import com.example.wallpaperapp.Client.WallpaperClient.Companion.getWallpeparClient
import com.example.wallpaperapp.Interface.WallpaperInterface
import com.example.wallpaperapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    var imageList: WallpaperModel? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getSearch("all")

        binding.bottomAppBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btm_Search -> {
                    val dialog = Dialog(this)
                    dialog.setContentView(R.layout.searchdilogbox)
                    dialog.show()
                    dialog.setCancelable(false)


                    dialog.findViewById<Button>(R.id.btnSearch).setOnClickListener {
                        var searchData =
                            dialog.findViewById<EditText>(R.id.edtSearch).text.toString()
                        getSearch(searchData)
                        dialog.dismiss()
                    }
                    true
                }


                R.id.btm_Home -> {
                    getSearch("all")
                    true
                }

                else -> {
                    false
                }
            }

        }
        }

        fun setRvData() {
            var adapter = WallpaperAdepater(this, imageList!!.hits)
            val lm = GridLayoutManager(this, 2)
            binding.rvWallpaperData.layoutManager = lm
            binding.rvWallpaperData.adapter = adapter
        }

        fun getSearch(q: String) {

            var Interface = getWallpeparClient()?.create(WallpaperInterface::class.java)
            Interface?.searchWallpaper("44883258-a6eb460debcffff3a673c138c", q, "all")
                ?.enqueue(object : Callback<WallpaperModel> {
                    override fun onResponse(
                        call: Call<WallpaperModel>,
                        response: Response<WallpaperModel>
                    ) {
                        if (response.code() == 200) {
                            imageList = response.body()
                            setRvData()
                        }
                    }

                    override fun onFailure(call: Call<WallpaperModel>, t: Throwable) {
                        Log.e("TAG", "onFailure: ${t.message}")
                    }
                })

        }


    }

    private fun getSearch(searchData: Any) {

    }
