package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finalyearproject.R
import com.example.finalyearproject.model.UserSignIn
import com.example.finalyearproject.model.UserSignUp
import com.example.finalyearproject.util.Url

import com.example.finalyearproject.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {

    private lateinit var viewModel : LoginViewModel

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

        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        signUpButton.setOnClickListener {
           var user = UserSignUp(signUpName.text.toString(),signUpDateOfBirth.text.toString(),signUpEmail.text.toString(),signUpPassword.text.toString())
           viewModel.signUpRequest(user)
            println(user)
        }

        observerFunctions()

    }


    fun observerFunctions(){

        viewModel.signUpResponse.observe(viewLifecycleOwner, Observer {
            when (it.data?.status) {
                "SUCCESS" -> {

                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                    println("asdasd")
                    println(it)
                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_signUpFragment_to_homeFragment2)
                    }

                }

                "FAILED" -> {
                    println(it)
                    // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(),it.data.message,Toast.LENGTH_LONG).show()


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