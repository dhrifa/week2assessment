package com.example.week2assessment.view


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2assessment.ArtistViewModel
import com.example.week2assessment.R
import com.example.week2assessment.databinding.FragmentRockLayoutBinding
import com.example.week2assessment.model.remote.ArtistItem
import com.example.week2assessment.model.remote.ArtistResponse
import com.example.week2assessment.view.adapter.ArtistAdapter
import androidx.fragment.app.activityViewModels

private const val TAG = "RockFragment"
const val TERM = "TERM"

class RockFragment() : Fragment() {
    val viewModel: ArtistViewModel by activityViewModels()
    private lateinit var binding: FragmentRockLayoutBinding
    private lateinit var bridge: ISearchArtist

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is ISearchArtist -> bridge = context
            else -> throw IllegalAccessException("Incorrect Host Activity")
        }
    }

    companion object {
        const val DISPLAY = "DISPLAY"
        const val TERM = "TERM"
        var term: String = "rock"
        fun newInstance(artistResponse: ArtistResponse, term: String): RockFragment {
            this.term = term
            val fragment = RockFragment()
            val bundle = Bundle()
            bundle.putString(TERM, term)
            bundle.putParcelable(DISPLAY, artistResponse)
            fragment.arguments = bundle
            return fragment
        }

        fun List<ArtistItem>.parseListArtist() =
            map { artistItem ->
                ArtistItem(
                    artistItem.artistName,
                    artistItem.collectionName,
                    artistItem.artworkUrl60,
                    artistItem.trackPrice,
                    artistItem.previewUrl
                )
            }

        fun parseListArtistOld(dataSet: ArtistResponse): List<ArtistItem> {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRockLayoutBinding.inflate(
            inflater, container, false
        )
        Log.d(TAG, "onCreateView: ")

        // term =  arguments?.getString(TERM) ?: "rock"
        initView()
        arguments?.getParcelable<ArtistResponse>(DISPLAY)?.let {
            Log.d(TAG, "onCreateView: $it")
            //   clickMusicPlayer() =>click item
            updateAdapter(it)
        }
//        vm.init(this)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        vm.get
    }

    private fun updateAdapter(dataSet: ArtistResponse) {
        val intent = Intent(Intent.ACTION_VIEW)//(parseListArtist(dataSet))

        binding.rvArtistList.layoutManager = LinearLayoutManager(context)
        Log.d(TAG, "old parsing List<ArtistItem> : ${dataSet.results.parseListArtist()}")
        binding.rvArtistList.adapter = ArtistAdapter(dataSet.results.parseListArtist()) {
            Toast.makeText(requireContext(), it.artistName.toString(), Toast.LENGTH_SHORT).show()
            mediaPlayerWithIntent(it.previewUrl)
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.display_rock_container, RockFragment())//dataSet, term))
//            .commit()
//            val webpage: Uri = Uri.parse(Uri.parse(it.) previewUrl)
//            intent.setDataAndType(Uri.parse(previewUrl), "audio/*")
//            ContextCompat.startActivity(paren, intent)
        }
    }

    private fun mediaPlayerWithIntent(previewUrl: String) {
        val webpage: Uri = Uri.parse(previewUrl)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        intent.setDataAndType(Uri.parse(previewUrl), "audio/*")
        startActivity(intent)
    }


    private fun clickMusicPlayer(/*bookVolumeInfo: ArtistItem*/) {
        Toast.makeText(context, "for playing music", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        //call network
    }


    private fun initView() {
        when (term) {
            "rock" -> {
                binding.rvArtistList.setBackgroundColor(Color.parseColor("#FF4043"))
            }
            //requireContext().resources.getString(R.string.rock) -> {binding.rvArtistList.setBackgroundColor(Color.RED)}

            "classick" -> {
                binding.rvArtistList.setBackgroundColor(Color.parseColor("#31A6FF"))
            }
            //requireContext().resources.getString(R.string.classic) -> {binding.rvArtistList.setBackgroundColor(Color.GREEN)}

            "pop" -> {
                binding.rvArtistList.setBackgroundColor(Color.parseColor("#FF749F"))
            }
            //requireContext().resources.getString(R.string.pop) -> {binding.rvArtistList.setBackgroundColor(Color.BLUE)}

        }
    }


}