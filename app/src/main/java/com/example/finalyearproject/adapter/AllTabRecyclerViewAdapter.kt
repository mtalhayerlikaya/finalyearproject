package com.example.finalyearproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.ItemResponse
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.view.CardViewDetailFragment
import com.example.finalyearproject.view.HomeFragmentDirections
import com.example.finalyearproject.view.ProfileFragmentDirections
import kotlinx.android.synthetic.main.all_tab_recyclerview_item.view.*

class AllTabRecyclerViewAdapter(val item:ItemResponse,val size:Int): RecyclerView.Adapter<AllTabRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) :RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_tab_recyclerview_item,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.allFragmentItemType.text = item.data!!.get(position).type
        holder.itemView.allFragmentItemImg.setImageResource(R.drawable.ic_launcher_background)
        holder.itemView.allFragmentItemName.text = item.data.get(position).title
        holder.itemView.allFragmentItemPrice.text = "$"+item.data.get(position).price.toString()
        holder.itemView.productCardView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragment2ToCardViewDetailFragment( item.data.get(position))
            Navigation.findNavController(it).navigate(direction)

            //Singleton.detailCardViewType = if (item.data?.get(position).type == null) "We have not reached the type now" else item.data?.get(position).type
            Singleton.detailCardViewTitle = if (item.data?.get(position).title == null) "We have not reached the title now" else item.data?.get(position).title
            Singleton.detailCardViewPrice = if (item.data?.get(position).price == null) "We have not reached the price now" else item.data?.get(position).price.toString()
            Singleton.selectedItemId = item.data[position]._id

        }
    }

    override fun getItemCount(): Int {
        return size
    }
}