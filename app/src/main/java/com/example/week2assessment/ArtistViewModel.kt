package com.example.week2assessment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week2assessment.model.remote.ArtistItem
import com.example.week2assessment.model.remote.ArtistNetwork
import com.example.week2assessment.model.remote.ArtistResponse
import com.example.week2assessment.view.RockFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ArtistViewModel"
class ArtistViewModel : ViewModel() {
    private val _artistResult: MutableLiveData<ArtistResponse> = MutableLiveData()
    val artistResult: LiveData<ArtistResponse>
        get() = _artistResult

    private lateinit var fragment: RockFragment

//    initf(inst: Rock){
//        this.fr = instt
//    }
    fun search(term: String) {//: ArtistResponse?

        ArtistNetwork.artistService.getArtistsByFilter(term)//termRock, "music", "song", 50)
            .enqueue(
                object : Callback<ArtistResponse> {
                    override fun onResponse(
                        call: Call<ArtistResponse>,
                        response: Response<ArtistResponse>
                    ) {
                        if (response.isSuccessful) {
                           _artistResult.value = response.body()
                            Log.d(TAG, "onResponse: ${_artistResult.value}")
//fragment.up
                        }
                        else {
                            Log.d(TAG, "fail: $response")
                        }
                    }

                    override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                    }
                }
            )

    }



}