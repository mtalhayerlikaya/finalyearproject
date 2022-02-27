package com.example.finalyearproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.all_tab_recyclerview_item.view.*
import kotlinx.android.synthetic.main.baked_recyclerview_item.view.*

class BakedTabRecyclerView(val bakedList : ArrayList<ItemsData>) : RecyclerView.Adapter<BakedTabRecyclerView.BakedViewHolder>() {
    class BakedViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BakedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.baked_recyclerview_item,parent,false)
        return BakedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BakedViewHolder, position: Int) {
        holder.itemView.bakedFragmentItemType.text = bakedList[position].type
        holder.itemView.bakedFragmentItemName.text = bakedList[position].title
        holder.itemView.bakedFragmentItemPrice.text = bakedList[position].price.toString()
        holder.itemView.bakedCardView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragment2ToCardViewDetailFragment(bakedList[position])
            Navigation.findNavController(it).navigate(direction)

            //Singleton.detailCardViewType = if (item.data?.get(position).type == null) "We have not reached the type now" else item.data?.get(position).type
            Singleton.detailCardViewTitle = if (bakedList.get(position).title == null) "We have not reached the title now" else bakedList.get(position).title
            Singleton.detailCardViewPrice = if (bakedList.get(position).price == null) "We have not reached the price now" else bakedList.get(position).price.toString()
            Singleton.selectedItemId = bakedList[position]._id

        }

    }

    override fun getItemCount(): Int {
        return bakedList.size
    }
}