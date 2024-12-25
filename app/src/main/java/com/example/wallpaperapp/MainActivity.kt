package com.example.wallpaperapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapp.Adepater.WallpaperAdepater
import com.example.wallpaperapp.Client.WallpaperClient.Companion.getWallpeparClient
import com.example.wallpaperapp.Interface.WallpaperInterface
import com.example.wallpaperapp.Modal.WallpaperModel
import com.example.wallpaperapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // nember od Selected teb we have 3 tebs so values must lie between 1-3 default value is 1 because first tab is by default
    var selectedTeb = 1

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
        bottomNavTeb()
        wallpeparSearch()

    }

    private fun wallpeparSearch() {
        binding.searchViewbtn.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                getSearch(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                getSearch(p0!!)
                return true
            }

        })
    }

    private fun bottomNavTeb() {
        binding.homebtnLayout.setOnClickListener {
            // check if home layout is already selected or not.

            if (selectedTeb != 1) {

                // unselected other tebs expect home teb
                binding.likebtnText.visibility = View.GONE
                binding.profilebtnText.visibility = View.GONE

                binding.likebtnImage.setImageResource(R.drawable.heart)
                binding.profilebtnImage.setImageResource(R.drawable.user)

                binding.likebtnLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.transparent
                    )
                )
                binding.profilebtnLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.transparent
                    )
                )

                // select teb

                binding.homebtnText.visibility = View.VISIBLE
                binding.homebtnImage.setImageResource(R.drawable.home_select)
                binding.homebtnLayout.setBackgroundResource(R.drawable.round_back_btn_home)

                // create Animation for teb


                val scaleAnimation = ScaleAnimation(
                    0.8f, 1.0f, 0.8f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                binding.homebtnLayout.startAnimation(scaleAnimation)

                // set selectedTeb to 1
                selectedTeb = 1
            }
        }
        binding.likebtnLayout.setOnClickListener {

            // check if Like layout is already selected or not.

            if (selectedTeb != 2) {

                // unselected other tebs expect Like teb
                binding.homebtnText.visibility = View.GONE
                binding.profilebtnText.visibility = View.GONE

                binding.homebtnImage.setImageResource(R.drawable.home)
                binding.profilebtnImage.setImageResource(R.drawable.user)

                binding.homebtnLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.transparent
                    )
                )
                binding.profilebtnLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.transparent
                    )
                )

                // select teb

                binding.likebtnText.visibility = View.VISIBLE
                binding.likebtnImage.setImageResource(R.drawable.heart_select)
                binding.likebtnLayout.setBackgroundResource(R.drawable.round_back_btn_like)

                // create Animation for teb


                val scaleAnimation = ScaleAnimation(
                    0.8f, 1.0f, 0.8f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.5f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                binding.likebtnLayout.startAnimation(scaleAnimation)

                // set selectedTeb to 1
                selectedTeb = 2

            }
        }
        binding.profilebtnLayout.setOnClickListener {


                // check if profile layout is already selected or not.

                if (selectedTeb != 3) {

                    // unselected other tebs expect profile teb
                    binding.homebtnText.visibility = View.GONE
                    binding.likebtnText.visibility = View.GONE

                    binding.homebtnImage.setImageResource(R.drawable.home)
                    binding.likebtnImage.setImageResource(R.drawable.heart)

                    binding.homebtnLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            android.R.color.transparent
                        )
                    )
                    binding.likebtnLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            android.R.color.transparent
                        )
                    )

                    // select teb

                    binding.profilebtnText.visibility = View.VISIBLE
                    binding.profilebtnImage.setImageResource(R.drawable.user_select)
                    binding.profilebtnLayout.setBackgroundResource(R.drawable.round_back_btn_profile)

                    // create Animation for teb


                    val scaleAnimation = ScaleAnimation(
                        0.8f, 1.0f, 0.8f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.5f
                    )
                    scaleAnimation.duration = 200
                    scaleAnimation.fillAfter = true
                    binding.profilebtnLayout.startAnimation(scaleAnimation)

                    // set selectedTeb to 1
                    selectedTeb = 3

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

