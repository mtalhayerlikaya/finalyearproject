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
import com.example.finalyearproject.view.VegetablesFragmentDirections
import kotlinx.android.synthetic.main.baked_recyclerview_item.view.*
import kotlinx.android.synthetic.main.greens_recyclerview_item.view.*
import kotlinx.android.synthetic.main.vegetables_recyclerview_item.view.*

class GreensRecyclerViewAdapter(val greenlist:ArrayList<ItemsData>) : RecyclerView.Adapter<GreensRecyclerViewAdapter.GreensViewHolder>() {
    class GreensViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreensViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.greens_recyclerview_item,parent,false)
        return GreensViewHolder(view)
    }

    override fun onBindViewHolder(holder: GreensViewHolder, position: Int) {
        holder.itemView.greensFragmentItemType.text = greenlist[position].type
        holder.itemView.greensFragmentItemName.text = greenlist[position].title
        holder.itemView.greensFragmentItemPrice.text = greenlist[position].price.toString()
        holder.itemView.greensCardView.setOnClickListener {


            val direction = HomeFragmentDirections.actionHomeFragment2ToCardViewDetailFragment(greenlist[position])
            Navigation.findNavController(it).navigate(direction)

            //Singleton.detailCardViewType = if (item.data?.get(position).type == null) "We have not reached the type now" else item.data?.get(position).type
            Singleton.detailCardViewTitle = if (greenlist.get(position).title == null) "We have not reached the title now" else greenlist.get(position).title
            Singleton.detailCardViewPrice = if (greenlist.get(position).price == null) "We have not reached the price now" else greenlist.get(position).price.toString()
            Singleton.selectedItemId = greenlist[position]._id

        }
    }

    override fun getItemCount(): Int {
        return greenlist.size
    }
}