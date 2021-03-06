package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finalyearproject.R
import com.example.finalyearproject.model.RequestUpdate
import com.example.finalyearproject.viewmodel.ChangePasswordViewModel
import kotlinx.android.synthetic.main.fragment_change_password.*


class ChangePasswordFragment : Fragment() {

    private lateinit var viewModel : ChangePasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ChangePasswordViewModel::class.java)

        resetPageButton.setOnClickListener {
            val request = RequestUpdate(resetPageToken.text.trim().toString(),resetPageNewPassword.text.trim().toString())
            viewModel.resetPassword(request,resetPageToken.text.trim().toString())
           // validate(request)
        }
        subcribeObserver()
    }

    fun subcribeObserver(){

        viewModel.resetPageResponse_.observe(viewLifecycleOwner, Observer {
            when(it?.status_code){

                "SUCCESS" -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()

                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_changePasswordFragment2_to_loginFragment)
                    }
                    viewModel.clearResponse()
                }

                "FAILED" -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    viewModel.clearResponse()
                }

            }
        })


    }
/*
    private fun validate(request: RequestUpdate):Boolean{

        if(resetPageToken.text.trim().toString().isEmpty() || resetPageNewPassword.text.toString().isEmpty()){
            Toast.makeText(context,"one of the boxes are empty",Toast.LENGTH_SHORT).show()
            return false
        }

        if(resetPageNewPassword.text.toString().length<6 || resetPageNewPassword.text.toString().length>24){
            Toast.makeText(context,"Password must be at range 6-24 or token is wrong",Toast.LENGTH_SHORT).show()
            return false
        }

        viewModel.resetPassword(request,resetPageToken.text.trim().toString())
        return true
    }*/

}