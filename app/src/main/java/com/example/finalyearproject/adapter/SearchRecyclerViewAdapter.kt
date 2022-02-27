package com.example.finalyearproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.R
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.util.Singleton
import kotlinx.android.synthetic.main.all_tab_recyclerview_item.view.*
import kotlinx.android.synthetic.main.search_reyclerview_item.view.*
import kotlinx.android.synthetic.main.vegetables_recyclerview_item.view.*

class SearchRecyclerViewAdapter(val searchList:ArrayList<ItemsData>) : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>() {
    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_reyclerview_item,parent,false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemView.searchFragmentItemName.text = searchList!!.get(position).title
        holder.itemView.searchFragmentItemImg.setImageResource(R.drawable.ic_launcher_background)
        holder.itemView.searchFragmentItemPrice.text = "$"+searchList!!.get(position).price.toString()
        holder.itemView.searchFragmentItemType.text = searchList!!.get(position).type
        holder.itemView.searchCardView.setOnClickListener {
            val action =
                Navigation.findNavController(it)
                    .navigate(R.id.action_searchFragment3_to_cardViewDetailFragment)

            //Singleton.detailCardViewType = if (item.data?.get(position).type == null) "We have not reached the type now" else item.data?.get(position).type
            Singleton.detailCardViewTitle = if (searchList.get(position).title == null) "We have not reached the title now" else searchList.get(position).title
            Singleton.detailCardViewPrice = if (searchList.get(position).price == null) "We have not reached the price now" else searchList.get(position).price.toString()
            Singleton.selectedItemId = searchList[position]._id

        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }




}