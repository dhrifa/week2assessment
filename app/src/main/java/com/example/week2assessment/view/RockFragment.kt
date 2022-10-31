package com.example.week2assessment.view


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2assessment.R
import com.example.week2assessment.databinding.FragmentRockLayoutBinding
import com.example.week2assessment.model.remote.ArtistItem
import com.example.week2assessment.model.remote.ArtistResponse
import com.example.week2assessment.view.adapter.ArtistAdapter

private const val TAG = "RockFragment"
class RockFragment() : Fragment() {

    private lateinit var binding: FragmentRockLayoutBinding

    companion object {
        const val DISPLAY = "DISPLAY"
        lateinit var term : String
        fun newInstance(artistResponse: ArtistResponse, term: String): RockFragment {
           this.term =term
            val fragment = RockFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY, artistResponse)
            fragment.arguments = bundle
            return fragment
        }

         fun parseListBookInfo(dataSet: ArtistResponse): List<ArtistItem> {
            return dataSet.results.map { artistItem ->
                ArtistItem(
                    artistItem.artistName,
                    artistItem.collectionName,
                    artistItem.artworkUrl60,
                    artistItem.trackPrice,
                    artistItem.previewUrl
                )
            }
        }
    }

    override fun  onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRockLayoutBinding.inflate(
            inflater, container, false
        )
        initView()
        arguments?.getParcelable<ArtistResponse>(DISPLAY)?.let {
            Log.d(TAG, "onCreateView: $it")
            updateAdapter(it)
        }
        return binding.root
    }



    private fun updateAdapter(dataSet: ArtistResponse) {
        
        binding.rvArtistList.adapter = ArtistAdapter(parseListBookInfo(dataSet))
    }



    private fun initView() {
        when (term) {
            "rock" -> {binding.rvArtistList.setBackgroundColor(Color.parseColor("#FF4043"))}
            //requireContext().resources.getString(R.string.rock) -> {binding.rvArtistList.setBackgroundColor(Color.RED)}

            "classick" -> {binding.rvArtistList.setBackgroundColor(Color.parseColor("#31A6FF"))}
            //requireContext().resources.getString(R.string.classic) -> {binding.rvArtistList.setBackgroundColor(Color.GREEN)}

            "pop" -> {binding.rvArtistList.setBackgroundColor(Color.parseColor("#FF749F"))}
            //requireContext().resources.getString(R.string.pop) -> {binding.rvArtistList.setBackgroundColor(Color.BLUE)}

        }

        binding.rvArtistList.layoutManager = LinearLayoutManager(context)
    }


}