package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalyearproject.R
import com.example.finalyearproject.adapter.AllTabRecyclerViewAdapter
import com.example.finalyearproject.databinding.FragmentProfileBinding
import com.example.finalyearproject.databinding.FragmentUpdateBinding
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.viewmodel.LoginViewModel
import com.example.finalyearproject.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_all.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onResume() {
        super.onResume()
        binding.profileNameText.text = Singleton.fullname
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)
        binding.profileNameText.text = Singleton.fullname

       /* requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })*/
        getRewardText.setOnClickListener {
            viewModel.getItem()
        }

        binding.updateProfilText.setOnClickListener {
            val direction = ProfileFragmentDirections.actionProfileFragment2ToUpdateFragment()
            Navigation.findNavController(requireView()).navigate(direction)
            println("clicked")
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