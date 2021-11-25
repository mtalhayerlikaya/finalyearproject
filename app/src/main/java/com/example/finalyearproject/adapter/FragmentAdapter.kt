package com.example.finalyearproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalyearproject.view.*

class FragmentAdapter(fragmentManager: FragmentManager,lifecycle:Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AllFragment()
            1 -> return FruitsFragment()
            2 -> return VegetablesFragment()
            3 -> return GreensFragment()
            4 -> return BakedFragment()

        }
        return Fragment()

    }
}