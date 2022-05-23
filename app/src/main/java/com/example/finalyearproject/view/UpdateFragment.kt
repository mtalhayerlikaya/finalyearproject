package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalyearproject.R
import com.example.finalyearproject.databinding.FragmentUpdateBinding
import com.example.finalyearproject.model.UpdateRequest
import com.example.finalyearproject.util.Singleton
import com.example.finalyearproject.viewmodel.LoginViewModel
import com.example.finalyearproject.viewmodel.UpdateViewModel
import com.google.android.material.snackbar.Snackbar


class UpdateFragment : Fragment() {

    private lateinit var binding:FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_update, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(UpdateViewModel::class.java)

        binding.update.setOnClickListener {
            if(handleEditText()){
                val updateRequest = UpdateRequest(Singleton.token!!,binding.editTextUsername.text.toString(),binding.editTextPassword.text.toString())
                println(updateRequest)
                viewModel.loadLiveData(updateRequest)
            }
        }


        registerToObserver()
    }

    fun registerToObserver(){
        viewModel.updateLiveData.observe(viewLifecycleOwner, Observer {
            when(it?.status_code){
                "SUCCESS"->{
                    Snackbar.make(requireContext(),binding.editTextPassword,"password succefully changed",Snackbar.LENGTH_LONG).show()
                    Singleton.fullname = binding.editTextUsername.text.toString()
                    viewModel.clearResponse()
                }
                "FAILED"->{
                    Snackbar.make(requireContext(),binding.editTextPassword,"an error occurred",Snackbar.LENGTH_LONG).show()
                    viewModel.clearResponse()
                }
            }

        })
    }

    fun handleEditText():Boolean{

        if(binding.editTextUsername.text.equals("") && binding.editTextPassword.text.equals("")){
            Snackbar.make(requireContext(),binding.editTextPassword,"One of the boxes are empty",Snackbar.LENGTH_LONG).show()
            return false
        }
        return true
    }


}