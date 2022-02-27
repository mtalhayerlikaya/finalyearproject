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
import com.example.finalyearproject.adapter.BakedTabRecyclerView
import com.example.finalyearproject.adapter.FruitsRecyclerView
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.viewmodel.TabsViewModel
import kotlinx.android.synthetic.main.fragment_baked.*
import kotlinx.android.synthetic.main.fragment_fruits.*
import kotlinx.android.synthetic.main.fragment_greens.*

class FruitsFragment : Fragment() {

    private lateinit var viewModel : TabsViewModel
    private lateinit var adapter: FruitsRecyclerView
    private lateinit var fruitItemsList:ArrayList<ItemsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fruits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fruitItemsList = ArrayList()

        viewModel = ViewModelProvider(requireActivity()).get(TabsViewModel::class.java)

        viewModel.getItem()

        subscribeToObservers()

    }


    private fun subscribeToObservers(){

        viewModel.item.observe(viewLifecycleOwner, Observer {
            // println("observe")
            when(it.status_code){

                "SUCCESS"->{

                    if(fruitItemsList.isEmpty()) {

                        it.data!!.forEach {
                            if (it.type == "fruit") {
                                fruitItemsList.add(it)
                            }
                        }
                        println("fruits " + fruitItemsList.size)
                        adapter = FruitsRecyclerView(fruitItemsList)
                        fruitsTabRecyclerView.adapter = adapter
                        fruitsTabRecyclerView.layoutManager =
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