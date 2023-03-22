package com.example.week2assessment.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.week2assessment.model.remote.ArtistResponse

class PagerAdapter (var context: Context, fm: FragmentManager,  private var mArtistItem: ArtistResponse ,var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {

      //  return RockFragment.newInstance(mArtistItem,term )

        return when(position){
            0->{RockFragment()}//.newInstance(mArtistItem,"rock" )}
            1->{RockFragment()}//.newInstance(mArtistItem,"classick" )}
            2-> {RockFragment()}//.newInstance(mArtistItem,"pop" )}
            else -> throw Exception("incorrect tab!! report to your admin") //{RockFragment.newInstance(mArtistItem,"rock" )}
        }
    }
}

