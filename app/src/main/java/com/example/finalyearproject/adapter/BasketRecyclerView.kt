package com.example.finalyearproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.Product
import kotlinx.android.synthetic.main.basket_recyclerview_item.view.*

class BasketRecyclerView(val productList:ArrayList<Product>) : RecyclerView.Adapter<BasketRecyclerView.BasketViewHolder>() {
    class BasketViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_recyclerview_item,parent,false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.itemView.basket_fragment_product_name.text = productList[position].name
        holder.itemView.basket_fragment_price.text = productList[position].price
        holder.itemView.basket_fragment_quantity.text = productList[position].quantity



    }

    override fun getItemCount(): Int {
        return productList.size
    }
}