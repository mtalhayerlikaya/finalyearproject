package com.example.finalyearproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.view.FruitsFragmentDirections
import com.example.finalyearproject.view.VegetablesFragmentDirections
import kotlinx.android.synthetic.main.all_tab_recyclerview_item.view.*
import kotlinx.android.synthetic.main.baked_recyclerview_item.view.*
import kotlinx.android.synthetic.main.vegetables_recyclerview_item.view.*

class VegetablesRecyclerView(val vegetablesList:ArrayList<ItemsData>) : RecyclerView.Adapter<VegetablesRecyclerView.VegetablesViewHolder>() {
    class VegetablesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetablesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vegetables_recyclerview_item,parent,false)
        return VegetablesViewHolder(view)
    }

    override fun onBindViewHolder(holder: VegetablesViewHolder, position: Int) {
        holder.itemView.vegetablesFragmentItemType.text = vegetablesList[position].type
        holder.itemView.vegetablesFragmentItemName.text = vegetablesList[position].title
        holder.itemView.vegetablesFragmentItemPrice.text = vegetablesList[position].price.toString()
        holder.itemView.vegetablesCardView.setOnClickListener {
            /*val action =
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment2_to_cardViewDetailFragment)*/

            val direction = VegetablesFragmentDirections.actionVegetablesFragmentToCardViewDetailFragment(vegetablesList[position])
            Navigation.findNavController(it).navigate(direction)

            //Singleton.detailCardViewType = if (item.data?.get(position).type == null) "We have not reached the type now" else item.data?.get(position).type
            Singleton.detailCardViewTitle = if (vegetablesList.get(position).title == null) "We have not reached the title now" else vegetablesList.get(position).title
            Singleton.detailCardViewPrice = if (vegetablesList.get(position).price == null) "We have not reached the price now" else vegetablesList.get(position).price.toString()
            Singleton.selectedItemId = vegetablesList[position]._id

        }
    }

    override fun getItemCount(): Int {
       return  vegetablesList.size
    }


}