package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finalyearproject.R
import com.example.finalyearproject.model.EmailRequest
import com.example.finalyearproject.viewmodel.ContinuePageViewModel
import com.example.finalyearproject.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_change_password_continue_page.*


class ChangePasswordContinuePageFragment : Fragment() {


    private lateinit var viewModel : ContinuePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password_continue_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ContinuePageViewModel::class.java)

        continueEmailButton.setOnClickListener {
            val email = EmailRequest(continueEmailText.text.trim().toString())
            viewModel.sendEmailRequest(email)
        }
        subcribeObserver()
    }

    private fun subcribeObserver(){
        viewModel.continuePageEmailResponse_.observe(viewLifecycleOwner, Observer {

            when(it?.status_code){

                "SUCCESS"-> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_changePasswordFragment_to_changePasswordFragment2)
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


}