package com.example.finalyearproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.finalyearproject.view.*

class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pageTitle = arrayListOf<String>("All","Fruits","Vegetables","Greens","Baked")
//FragmentPagerAdapter(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

    override fun getCount(): Int {
        return pageTitle.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> FruitsFragment()
            2 -> VegetablesFragment()
            3 -> GreensFragment()
            4 -> BakedFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitle[position]
    }



/*
    fun addPage(){
        pageFragment.add(AllFragment())
        pageTitle.add("All")
        pageFragment.add(FruitsFragment())
        pageTitle.add("Fruits")
        pageFragment.add(VegetablesFragment())
        pageTitle.add("Vegetables")
        pageFragment.add(GreensFragment())
        pageTitle.add("Greens")
        pageFragment.add(BakedFragment())
        pageTitle.add("Baked")
    }

*/


}