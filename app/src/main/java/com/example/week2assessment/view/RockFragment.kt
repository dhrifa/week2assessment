package com.example.week2assessment.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2assessment.databinding.FragmentRockLayoutBinding
import com.example.week2assessment.model.remote.ArtistItem
import com.example.week2assessment.model.remote.ArtistResponse
import com.example.week2assessment.view.adapter.ArtistAdapter

private const val TAG = "RockFragment"
class RockFragment : Fragment() {

    private lateinit var binding: FragmentRockLayoutBinding

    companion object {
        const val DISPLAY = "DISPLAY"
        fun newInstance(artistResponse: ArtistResponse): RockFragment {
            val fragment = RockFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY, artistResponse)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRockLayoutBinding.inflate(
            inflater, container, false
        )
        initView()
        //binding.rvArtistList.adapter =ArtistAdapter()
        arguments?.getParcelable<ArtistResponse>(DISPLAY)?.let {
            Log.d(TAG, "onCreateView: $it")
            updateAdapter(it)
        }
        return binding.root
    }

    private fun updateAdapter(dataSet: ArtistResponse) {
        
        binding.rvArtistList.adapter = ArtistAdapter(parseListBookInfo(dataSet))
    }

    private fun parseListBookInfo(dataSet: ArtistResponse): List<ArtistItem> {
        return dataSet.results.map { artistItem ->
            ArtistItem(
                artistItem.artistName,
                artistItem.collectionName,
                artistItem.artworkUrl60,
                artistItem.trackPrice
//                ,
//                bookItem.volumeInfo.publishedDate,
//                bookItem.volumeInfo.imageLinks
            )
        }
    }

    private fun initView() {
        binding.rvArtistList.layoutManager = LinearLayoutManager(context)
//        binding.rvArtistList.adapter =ArtistAdapter(emptyList())
//            binding.rvArtistList.adapter =BookAdapter(emptyList()){
////            //trailing lambda
////        }
    }


}