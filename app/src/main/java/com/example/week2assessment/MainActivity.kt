package com.example.week2assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.example.week2assessment.model.remote.ArtistNetwork
import com.example.week2assessment.model.remote.ArtistResponse
//import com.example.week2assessment.view.ClassicFragment
import com.example.week2assessment.view.ISearchArtist
//import com.example.week2assessment.view.PopFragment
import com.example.week2assessment.view.RockFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), ISearchArtist {
    private lateinit var tabLayoutItems: TabLayout
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var viewPager: ViewPager

    private var termRock = "rock"
    private var termClassic = "classick"
    private var termPop = "pop"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        search(termRock)
        Log.d(this.localClassName, "onCreate: ")
    }


    private fun initView() {
        viewPager = findViewById(R.id.vp_pager)
        tabLayoutItems = findViewById(R.id.tab_layout_items)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)

        refreshLayout.setOnRefreshListener {
            getMusic(tabLayoutItems.selectedTabPosition)
            refreshLayout.isRefreshing = false
        }
        //setupViewPager(viewPager)

        // If we dont use setupWithViewPager() method then
        // tabs are not used or shown when activity opened
        tabLayoutItems.setupWithViewPager(viewPager)

        tabLayoutItems.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: Tab?) {
                // tab?.icon = getDrawable(R.drawable.ic_launcher_background)
                getMusic(tab?.position)
            }

            override fun onTabUnselected(tab: Tab?) {}

            override fun onTabReselected(tab: Tab?) {}
        })

    }

    private fun getMusic(tab: Int?) {
        when (tab) {
            0 -> {
                search(termRock)
                //Toast.makeText(tabLayoutItems.context, tab.text, Toast.LENGTH_SHORT).show()
            }
            1 -> {
                search(termClassic)
            }
            2 -> {
                search(termPop)
            }
        }
    }

    override fun search(term: String) {
        ArtistNetwork.artistService.getArtistsByFilter(term)//termRock, "music", "song", 50)
            .enqueue(
                object : Callback<ArtistResponse> {
                    override fun onResponse(
                        call: Call<ArtistResponse>,
                        response: Response<ArtistResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            Log.d(TAG, "onResponse: $body")
                            createDisplayResponse(body, term)
                        } else {
                            Log.d(TAG, "fail: $response")
                        }
                    }

                    override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        Toast.makeText(
                            this@MainActivity,
                            "${t.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
    }

    private fun createDisplayResponse(body: ArtistResponse?, term: String) {
        body?.let {
            supportFragmentManager.beginTransaction()
//                .replace(R.id.vp_pager, RockFragment.newInstance(it,term))
                .replace(R.id.display_rock_container, RockFragment.newInstance(it, term))
                .commit()
        }
    }

}