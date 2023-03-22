package com.example.week2assessment.view.adapter

import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week2assessment.databinding.ArtistItemLayoutBinding
import com.example.week2assessment.model.remote.ArtistItem
import com.squareup.picasso3.Picasso


private const val TAG = "ArtistAdapter"
var isPlaying: Boolean = false// true
var isPreviousPlaying: String = ""
var mediaPlayer: MediaPlayer? = null

class ArtistAdapter(
    private var dataSet: List<ArtistItem>,
    private val openDetails: (( ArtistItem) -> Unit)? = null
) :
   RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {


     class ArtistViewHolder(private var binding:  ArtistItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentArtist: ArtistItem,position: Int ,openDetails: ((ArtistItem) -> Unit)) {
            binding.root.setOnClickListener {
               // this.bindingAdapterPosition
                Log.d(TAG, "holder.adapterPosition : $position")
                openDetails(currentArtist)
            }
            //region method1
//            binding.root.setOnClickListener {
//                //play music
//                playMusic(currentArtist.previewUrl)
//                Log.d(TAG, "bind: ${currentArtist.previewUrl}")
//            }
            //endregion
            binding.tvCollectionArtist.text = currentArtist.collectionName
            binding.tvNameArtist.text = currentArtist.artistName
            binding.tvPrice.text = "${currentArtist.trackPrice} USD"
            Picasso.Builder(binding.imageViewArtist.context)
                .build()
                .load(currentArtist.artworkUrl60)
                .into(binding.imageViewArtist)
//region tto finish
//            binding.ibStopMusic.setOnClickListener {
//                isPlaying = false
//                stopMusic()
//            }
//            binding.ibPlayMusic.setOnClickListener {
//                if (isPlaying) stopMusic()
//                isPlaying = true
//                startMediaPlayer(currentArtist.previewUrl)
//            }
            //endregion
        }


        private fun playMusic(previewUrl: String) {

            Log.d(TAG, "playMusic: $isPlaying")
            when {
                isPlaying && previewUrl == isPreviousPlaying -> {
                    stopMusic()
                    isPlaying = false
                }
                isPlaying -> {
                    stopMusic()
                    startMediaPlayer(previewUrl)
                }
                else -> startMediaPlayer(previewUrl)
            }
        }

        private fun stopMusic() {
            binding.ibStopMusic.visibility = View.GONE
            binding.ibPlayMusic.visibility = View.GONE
            binding.root.setBackgroundColor(Color.WHITE)
            mediaPlayer?.release()
        }

        private fun startMediaPlayer(previewUrl: String) {
            binding.ibStopMusic.visibility = View.VISIBLE
            binding.ibPlayMusic.visibility = View.VISIBLE
            binding.root.setBackgroundColor(Color.GREEN)
            isPlaying = true
            isPreviousPlaying = previewUrl
            mediaPlayer = MediaPlayer()
            mediaPlayer?.apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(previewUrl)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            ArtistItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

     override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        //   (holder as ArtistViewHolder).bind(dataSet[position], openDetails!!)

            openDetails?.let {
                holder.bind(dataSet[position],holder.adapterPosition, openDetails)
        }

        //region tto finish
//        binding.ibStopMusic.setOnClickListener {
//            isPlaying = false
//            stopMusic()
//        }
//        binding.ibPlayMusic.setOnClickListener {
//            if (isPlaying) stopMusic()
//            isPlaying = true
//            startMediaPlayer(currentArtist.previewUrl)
//        }
        //endregion
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }



}


//ca marche mais plusier a la fois
//            var mediaPlayer: MediaPlayer = MediaPlayer().also {
//                it.setAudioStreamType(AudioManager.STREAM_MUSIC)
//            }
//            try {
//                mediaPlayer.setDataSource(previewUrl)
//                mediaPlayer.prepare()
//                mediaPlayer.start()
//
//            } catch (e: Exception) {
//
//                e.printStackTrace()
//            }
//            Toast.makeText(binding.root.context, "Audio started playing..", Toast.LENGTH_SHORT)
//                .show()
