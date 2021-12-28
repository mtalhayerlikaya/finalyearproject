package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.BasketRecyclerView
import com.example.finalyearproject.model.Product
import com.example.finalyearproject.util.Singleton
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })*/

        /*
        var name =  BasketFragmentArgs.fromBundle(arguments?.getBundle("name")).name
        var price = BasketFragmentArgs.fromBundle(arguments?.getBundle("price")).price
        var quantity = BasketFragmentArgs.fromBundle(arguments?.getBundle("quantity")).quantity

        if(name != null && price != null && quantity != null){
            println(name)
            println(price)
            println(quantity)
        }*/

        /*arguments?.let {
            var name = BasketFragmentArgs.fromBundle(it).name
            var price = BasketFragmentArgs.fromBundle(it).price
            var quantity = BasketFragmentArgs.fromBundle(it).quantity

            if(name != null && price != null && quantity != null){
               val product = Product(name,price,quantity)
                productList = ArrayList()
                productList.add(product)
                val basketRecycler = BasketRecyclerView(productList)
                val linearLayoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL,false)
                basketRecyclerView.adapter = basketRecycler
                basketRecyclerView.layoutManager = linearLayoutManager

            }

        }*/
        val basketRecycler = Singleton.basketItems?.let { BasketRecyclerView(it) }
        val linearLayoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL,false)
        basketRecyclerView.adapter = basketRecycler
        basketRecyclerView.layoutManager = linearLayoutManager

    }

}