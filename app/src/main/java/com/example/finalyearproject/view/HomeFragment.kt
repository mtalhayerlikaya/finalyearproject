package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.FragmentAdapter
import com.example.finalyearproject.adapter.MyPagerAdapter
import com.example.finalyearproject.viewmodel.ChangePasswordViewModel
import com.example.finalyearproject.viewmodel.TabsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    private lateinit var adapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyPagerAdapter(childFragmentManager)
        view_pager.adapter = adapter
        //view_pager.offscreenPageLimit=1

        tab_layout.setupWithViewPager(view_pager)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }

        })


    }




}