package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.AllTabRecyclerViewAdapter
import com.example.finalyearproject.adapter.MyPagerAdapter
import com.example.finalyearproject.util.Singleton

import com.example.finalyearproject.viewmodel.TabsViewModel
import kotlinx.android.synthetic.main.fragment_all.*
import kotlinx.android.synthetic.main.fragment_all.view.*


class AllFragment : Fragment() {

    private lateinit var viewModel : TabsViewModel
    private lateinit var adapter:AllTabRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(TabsViewModel::class.java)

        viewModel.getİtem()



        swipeRefresh.setOnRefreshListener {
            viewModel.getİtem()
            swipeRefresh.isRefreshing = false
        }


        subscribeToObservers()
    }

    private fun subscribeToObservers(){

        viewModel.item.observe(viewLifecycleOwner, Observer {
           // println("observe")
           when(it.status_code){

               "SUCCESS"->{

                   Singleton.items = it.data
                   adapter = AllTabRecyclerViewAdapter(it, it.data!!.size)
                   allTabRecyclerView.adapter = adapter
                   allTabRecyclerView.layoutManager = GridLayoutManager(activity?.baseContext,2)
                   //println(it.data)
               }

               "FAILED"->{
                   println("products could not be loaded")
               }

           }



        })


    }


}