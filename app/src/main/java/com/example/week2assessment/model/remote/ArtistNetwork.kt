package com.example.week2assessment.model.remote

import android.hardware.usb.UsbEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArtistNetwork {
//        https://itunes.apple.com/
//        search
//        ?term=rock
//        &amp;media=music
//        &amp;entity=song
//        &amp;limit=50

//        search?term=rock&amp;media=music&amp;entity=song&amp;limit=50

    const val BASE_URL = "https://itunes.apple.com/"
    const val ENDPOINT = "search"//?amp;media=music&amp;entity=song&amp;limit=50"// "search"

    val artistService: ArtistService by lazy {
        initRetrofit().create(ArtistService::class.java)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
