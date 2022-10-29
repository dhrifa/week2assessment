package com.example.week2assessment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week2assessment.databinding.ArtistItemLayoutBinding
import com.example.week2assessment.model.remote.ArtistItem
import com.example.week2assessment.model.remote.ArtistResponse
import com.squareup.picasso3.Picasso

class ArtistAdapter(private var dataSet: /*ArtistResponse*/ List<ArtistItem>) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {


    class ArtistViewHolder(private var binding: ArtistItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentArtist: ArtistItem) {
            binding.root.setOnClickListener {
                //play music
            }
            binding.tvCollectionArtist.text = currentArtist.collectionName
            binding.tvNameArtist.text = currentArtist.artistName
            binding.tvPrice.text = currentArtist.trackPrice.toString()
            Picasso.Builder(binding.imageViewArtist.context)
                .build()
                .load(currentArtist.artworkUrl60)
                .into(binding.imageViewArtist)
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
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}