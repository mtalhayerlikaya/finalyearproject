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
import com.example.finalyearproject.model.RequestSignIn
import com.example.finalyearproject.viewmodel.LoginViewModel

import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.*


class LoginFragment : Fragment() {

    val scope = CoroutineScope(Dispatchers.IO+ Job())

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_login,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        RegisterText.setOnClickListener {
            val action =
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        forgotPassword.setOnClickListener {
            val action =
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_changePasswordFragment)
        }


        loginPageButton.setOnClickListener {
            val user = RequestSignIn(loginEmailText.text.trim().toString(),loginPagePassword.text.trim().toString())
            println(user)
            viewModel.signInRequest(user)

            viewModel.deneme()

        }

        observerFunctions()
    }

    private fun observerFunctions(){

        viewModel.responseSignInResponse.observe(viewLifecycleOwner, Observer {
            println("insde obeserver")
            println(it?.message)
            when (it.status) {
                "SUCCESS" -> {

                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(),"Welcome!", Toast.LENGTH_SHORT).show()
                    println("success")
                    println(it)
                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_loginFragment_to_homeFragment2)
                    }

                }

                "FAILED" -> {
                    println(it.data)
                    println("failed")
                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()


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