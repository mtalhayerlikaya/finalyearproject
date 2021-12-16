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
import com.google.android.material.snackbar.Snackbar
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
            //validate(user)
            viewModel.signUpRequest(user)

        }

        observerFunctions()

    }


   private fun observerFunctions(){

        viewModel.responseSignUpResponse.observe(viewLifecycleOwner, Observer {
            println(it)
            when (it?.status) {
                "SUCCESS" -> {

                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(),"User created", Toast.LENGTH_SHORT).show()
                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_signUpFragment_to_loginFragment)
                    }
                    viewModel.clearResponse()
                }

                "FAILED" -> {

                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()

                    viewModel.clearResponse()
                    /*
                    Status.LOADING -> {
                        fragmentBinding?.progressBar?.visibility = View.VISIBLE

                    }*/
                }
            }
        }
        )
    }

    private fun validate(user:RequestSignUp):Boolean{

        if(user.full_name.isEmpty() || user.email.isEmpty() || user.password.isEmpty()){
            Toast.makeText(context,"one of the boxes are empty",Toast.LENGTH_SHORT).show()
            return false
        }

        if(user.password.length<8 || user.password.length>24){
            Toast.makeText(context,"Password must be at range 8-24",Toast.LENGTH_SHORT).show()
            return false
        }

        if(!user.email.contains("@gmail.com") || user.email.length<18){
            Toast.makeText(context,"mail must contain @gmail.com expression or must be 8 character at least",Toast.LENGTH_SHORT).show()
            return false
        }

        viewModel.signUpRequest(user)
        return true
    }



}