package com.example.week2assessment.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {
    //    https://itunes.apple.com/
//        search
//        ?term=rock
//        &amp;media=music
//        &amp;entity=song
//        &amp;limit=50
    @GET(ArtistNetwork.ENDPOINT)

    fun getArtistsByFilter(
        @Query("term") termArtist: String,
//        @Query("amp;media") mediaType: String = "music",
//        @Query("amp;entity") entityResult: String = "song",
//        @Query("amp;limit") limitResult: Int = 50
    ): Call<ArtistResponse>


}