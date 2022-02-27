package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.AllTabRecyclerViewAdapter
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.viewmodel.LoginViewModel
import com.example.finalyearproject.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_all.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)


       /* requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })*/
        getRewardText.setOnClickListener {
            viewModel.getItem()
        }

        registerToObserver()
    }


    private fun registerToObserver(){
        viewModel.itemsForProfile.observe(viewLifecycleOwner, Observer {
            var sizeOfList = it?.data?.size?.minus(1)

            when(it?.status_code){

                "SUCCESS"->{
                    var rnds = (0..sizeOfList!!).random()
                    val item = it!!.data?.get(rnds)

                    val direction = ProfileFragmentDirections.actionProfileFragment2ToCardViewDetailFragment(item!!)
                    Navigation.findNavController(requireView()).navigate(direction)



                    viewModel.clearResponse()
                }

                "FAILED"->{
                    println("products could not be loaded")
                    viewModel.clearResponse()
                }

            }
        })
    }


}