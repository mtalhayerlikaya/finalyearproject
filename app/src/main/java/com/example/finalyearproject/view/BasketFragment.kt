package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.BasketRecyclerView
import com.example.finalyearproject.databinding.FragmentBasketBinding
import com.example.finalyearproject.model.Product
import com.example.finalyearproject.util.Singleton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {

    private lateinit var binding:FragmentBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.basketBuyButton.setOnClickListener {
            Snackbar.make(binding.basketBuyButton,"products bought",Snackbar.LENGTH_SHORT).show()
        }


        val basketRecycler = Singleton.basketItems?.let { BasketRecyclerView(it) }
        val linearLayoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL,false)
        basketRecyclerView.adapter = basketRecycler
        basketRecyclerView.layoutManager = linearLayoutManager

       /* if(Singleton.basketItems != null){
            val basketRecycler = BasketRecyclerView(Singleton.basketItems!!)
            val linearLayoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL,false)
            basketRecyclerView.adapter = basketRecycler
            basketRecyclerView.layoutManager = linearLayoutManager
        }else{
            val basketRecycler =BasketRecyclerView(ArrayList<Product>())
            val linearLayoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL,false)
            basketRecyclerView.adapter = basketRecycler
            basketRecyclerView.layoutManager = linearLayoutManager
        }*/

    }

}