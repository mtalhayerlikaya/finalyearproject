package com.example.finalyearproject.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.AllTabRecyclerViewAdapter
import com.example.finalyearproject.adapter.BakedTabRecyclerView
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.model.Product
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.viewmodel.TabsViewModel
import kotlinx.android.synthetic.main.fragment_all.*
import kotlinx.android.synthetic.main.fragment_baked.*


class BakedFragment : Fragment() {

    private lateinit var viewModel : TabsViewModel
    private lateinit var adapter: BakedTabRecyclerView
    private lateinit var bakedItemsList:ArrayList<ItemsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_baked, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bakedItemsList = ArrayList()

        viewModel = ViewModelProvider(requireActivity()).get(TabsViewModel::class.java)

        viewModel.getItem()

        subscribeToObservers()
    }


    private fun subscribeToObservers(){

        viewModel.item.observe(viewLifecycleOwner, Observer {
            // println("observe")
            when(it.status_code){

                "SUCCESS"->{
                    if(bakedItemsList.isEmpty()) {
                        it.data!!.forEach {
                            if (it.type == "baked") {
                                bakedItemsList.add(it)
                            }
                        }
                        println("baked " + bakedItemsList.size)
                        adapter = BakedTabRecyclerView(bakedItemsList)
                        bakedTabRecyclerView.adapter = adapter
                        bakedTabRecyclerView.layoutManager =
                            GridLayoutManager(activity?.baseContext, 2)
                    }
                }

                "FAILED"->{
                    println("products could not be loaded")
                }

            }



        })


    }


}