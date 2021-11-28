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
import com.example.finalyearproject.model.RequestSignUp

import com.example.finalyearproject.viewmodel.LoginViewModel
import com.example.finalyearproject.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.job


class SignUpFragment : Fragment() {

    private lateinit var viewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)

        signUpButton.setOnClickListener {
           var user = RequestSignUp(signUpName.text.trim().toString(),signUpEmail.text.trim().toString(),signUpPassword.text.trim().toString())
          viewModel.signUpRequest(user)
         //println(viewModel.signUpRequest(user))
        }

        observerFunctions()

    }


   private fun observerFunctions(){

        viewModel.responseSignUpResponse.observe(viewLifecycleOwner, Observer {
            when (it.data?.status) {
                "SUCCESS" -> {

                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(), it.data.status, Toast.LENGTH_SHORT).show()
                    println("success")
                    println(it)
                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_signUpFragment_to_homeFragment2)
                    }

                }

                "FAILED" -> {
                    println(it)
                    println("failed")
                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(),it.data.status,Toast.LENGTH_LONG).show()


                    /*
                    Status.LOADING -> {
                        fragmentBinding?.progressBar?.visibility = View.VISIBLE

                    }*/
                }
            }
        }
        )
    }








}