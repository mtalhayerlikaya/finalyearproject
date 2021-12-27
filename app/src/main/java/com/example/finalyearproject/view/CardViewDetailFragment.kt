package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalyearproject.R
import com.example.finalyearproject.model.LikeRequest
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.util.Singleton.likedItems
import com.example.finalyearproject.util.Singleton.selectedItemId
import com.example.finalyearproject.viewmodel.CardViewDetailViewModel
import com.example.finalyearproject.viewmodel.ChangePasswordViewModel
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_card_view_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class CardViewDetailFragment : Fragment() {

    private lateinit var viewModel:CardViewDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_view_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(CardViewDetailViewModel::class.java)


        detailFragmentPrice.text = "$"+Singleton.detailCardViewPrice
        detailFragmentTitle.text = Singleton.detailCardViewTitle
        ifItemLiked()
        var request = Singleton.selectedItemId?.let { LikeRequest(it) }
        if (request != null) {
            handleInsreaseAndDecreaseQuantity(request)
        }

    subscribeToObserver()
    }

    private fun subscribeToObserver(){

        viewModel.responseLike_.observe(viewLifecycleOwner, Observer {
            when(it?.status_code){
                "SUCCESS"->{
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
                    viewModel.clearResponse()
                }

                "FAILED"->{
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
                    viewModel.clearResponse()
                }

            }
        })

    }




    private fun handleInsreaseAndDecreaseQuantity(request:LikeRequest){
        decreaseButton.setOnClickListener {
            if(Integer.parseInt(quantityTextView.text.toString())>0){
                val quantity = Integer.parseInt(quantityTextView.text.toString())-1
                quantityTextView.text=quantity.toString()
            }
        }

        increaseButton.setOnClickListener {
            var quantity = Integer.parseInt(quantityTextView.text.toString())+1
            quantityTextView.text=quantity.toString()

        }

        detailFragmentAddBasket.setOnClickListener {
            if(Integer.parseInt(quantityTextView.text.toString())<=0){
                Toast.makeText(requireContext(),"You have to pick one product at least",Toast.LENGTH_SHORT).show()
            }
        }


           toolbar.likeItemButton.setOnClickListener {
               println("clicked")
              if(selectedItemId != null && likedItems != null){
                  // item is not liked , like item
                  if(!likedItems!!.contains(selectedItemId)){
                      likedItems!!.add(selectedItemId!!)
                      it.likeItemButton.setImageResource(R.drawable.outline_favorite_24)
                      Singleton.token?.let { it1 -> viewModel.sendLikeRequest(it1,request) }
                  }else{//unlike item
                      likedItems!!.remove(selectedItemId)
                      it.likeItemButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                      Singleton.token?.let { it1 -> viewModel.sendLikeRequest(it1,request) }
                  }
              }

           }

    }



    private fun ifItemLiked(){
        if(selectedItemId != null && likedItems != null){
            if(likedItems!!.contains(selectedItemId)){
                likeItemButton.setImageResource(R.drawable.outline_favorite_24)
            }
        }
    }



}