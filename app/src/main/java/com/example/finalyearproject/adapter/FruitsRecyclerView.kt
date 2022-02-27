package com.example.finalyearproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.ItemResponse
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.view.FruitsFragmentDirections
import com.example.finalyearproject.view.HomeFragmentDirections
import com.example.finalyearproject.view.ProfileFragmentDirections
import kotlinx.android.synthetic.main.baked_recyclerview_item.view.*
import kotlinx.android.synthetic.main.fruits_recyclerview_item.view.*
import kotlinx.android.synthetic.main.greens_recyclerview_item.view.*

class FruitsRecyclerView(val itemList:ArrayList<ItemsData>) : RecyclerView.Adapter<FruitsRecyclerView.FruitViewHolder>() {
    class FruitViewHolder(view: View) :RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruits_recyclerview_item,parent,false)
        return FruitViewHolder(view)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.itemView.fruitsFragmentItemType.text = itemList[position].type
        holder.itemView.fruitsFragmentItemName.text = itemList[position].title
        holder.itemView.fruitsFragmentItemPrice.text = itemList[position].price.toString()
        holder.itemView.fruitsCardView.setOnClickListener {


            val direction = HomeFragmentDirections.actionHomeFragment2ToCardViewDetailFragment(itemList[position])
            Navigation.findNavController(it).navigate(direction)

            //Singleton.detailCardViewType = if (item.data?.get(position).type == null) "We have not reached the type now" else item.data?.get(position).type
            Singleton.detailCardViewTitle = if (itemList.get(position).title == null) "We have not reached the title now" else itemList.get(position).title
            Singleton.detailCardViewPrice = if (itemList.get(position).price == null) "We have not reached the price now" else itemList.get(position).price.toString()
            Singleton.selectedItemId = itemList[position]._id

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}