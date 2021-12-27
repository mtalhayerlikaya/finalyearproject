package com.example.finalyearproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.view.LikeFragment
import kotlinx.android.synthetic.main.all_tab_recyclerview_item.view.*
import kotlinx.android.synthetic.main.like_recyclerview_item.view.*

class LikeRecyclerView(val latestLikedItems:ArrayList<ItemsData> ): RecyclerView.Adapter<LikeRecyclerView.LikeViewHolder>() {

    class LikeViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.like_recyclerview_item,parent,false)
        return LikeViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {

                 //holder.itemView.like_fragment_price.text = it.get(position)
                 holder.itemView.like_fragment_price.text = latestLikedItems[position].price.toString()
                 holder.itemView.like_fragment_product_name.text = latestLikedItems[position].title

    }

    override fun getItemCount(): Int {
        return latestLikedItems.size
    }

}