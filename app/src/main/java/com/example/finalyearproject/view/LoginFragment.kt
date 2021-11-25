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

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.R
import com.example.finalyearproject.model.UserSignIn
import com.example.finalyearproject.viewmodel.LoginViewModel

import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.*
import javax.inject.Inject


class LoginFragment : Fragment() {


      private lateinit var viewModel: LoginViewModel


      @Inject
      lateinit var api:RetrofitApi

      val scope = CoroutineScope(Job() + Dispatchers.IO)

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

        //val userToSignIn = UserSignIn("jeffbezos@gmail.com","deneme123123")

        //println("onviewcreated")

        RegisterText.setOnClickListener {
            val action =
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        signInButton.setOnClickListener {
           // var user = UserSignIn(userEmail, userPassword)
           // viewModel.signInRequest(user)

            var user = UserSignIn(signInEmail.text.toString(),signInPassword.text.toString())
            viewModel.signInRequest(user)

           // println(signInName.text)
           // println(signInPassword.text)

        }

        observerFunctions()

    }

    fun observerFunctions(){

        viewModel.signInResponse.observe(viewLifecycleOwner, Observer {
            when (it.data?.status) {
                "SUCCESS" -> {

                   // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                    Toast.makeText(requireContext(), it.data.message ?: "Error", Toast.LENGTH_SHORT).show()
                    println("asdasd")
                    println(it)
                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_loginFragment_to_homeFragment2)
                    }

                }

                "FAILED" -> {
                    println(it)
                   // view?.let { it1 -> Snackbar.make(it1,it.message.toString(),Snackbar.LENGTH_LONG).show() }
                     Toast.makeText(requireContext(), it.data.message ?: "Error", Toast.LENGTH_LONG).show()


                /*
                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE

                }*/
                }
            }
        }
        )
    }




    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }



}