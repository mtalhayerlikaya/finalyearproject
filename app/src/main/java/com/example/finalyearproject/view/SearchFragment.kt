package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.AllTabRecyclerViewAdapter
import com.example.finalyearproject.adapter.SearchRecyclerViewAdapter
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.util.Singleton
import kotlinx.android.synthetic.main.fragment_all.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

   private lateinit var searchList:ArrayList<ItemsData>
   private lateinit var adapter : SearchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })

        searchList = ArrayList()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                Singleton.items!!.forEach {
                   if(!newText.equals("")){
                       if(it.title.toLowerCase().contains(newText.toString())){
                           searchList.add(it)
                           searchRecyclerview.adapter?.notifyDataSetChanged()

                       }
                   }

               }
                //searchList.clear()
                return false
            }

        })

        adapter = SearchRecyclerViewAdapter(searchList)
        searchRecyclerview.adapter = adapter
        searchRecyclerview.layoutManager = GridLayoutManager(activity?.baseContext,2)





    }


}