package com.example.week2assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.week2assessment.model.remote.ArtistNetwork
import com.example.week2assessment.model.remote.ArtistResponse
import com.example.week2assessment.view.ISearchArtist
import com.example.week2assessment.view.RockFragment
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), ISearchArtist {
    private lateinit var tabLayoutItems : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        search("rock")
        Log.d(this.localClassName, "onCreate: ")
    }

    private fun initView() {
     //   tabLayoutItems = findViewById(R.id.tab_layout_items)

        //TabLayout tabLayout = ...;
//        tabLayoutItems.addTab(tabLayoutItems.newTab().setText("Tab 1"));
//        tabLayoutItems.addTab(tabLayoutItems.newTab().setText("Tab 2"));
       // tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
    }

    override fun search(term: String)
    {
        ArtistNetwork.artistService.getArtistsByFilter(term)//term, "music", "song", 50)
            .enqueue(
                object : Callback<ArtistResponse> {
                    override fun onResponse(
                        call: Call<ArtistResponse>,
                        response: Response<ArtistResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            Log.d(TAG, "onResponse: $body")
                            createDisplayResponse(body)
                        }else{
                            Log.d(TAG, "fail: $response")
                        }
                    }

                    override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            )
    }

    private fun createDisplayResponse(body: ArtistResponse?) {
        body?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.display_rock_container, RockFragment.newInstance(it))
                .commit()
        }


    }

    
}