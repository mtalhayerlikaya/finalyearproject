package com.example.finalyearproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalyearproject.Api.RetrofitApi


import com.example.finalyearproject.R
import com.example.finalyearproject.model.RequestSignIn
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.repository.LoginPageRepository
import com.example.finalyearproject.util.Url

import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    val scope = CoroutineScope(Dispatchers.IO+ Job())
    @Inject
    lateinit var api:RetrofitApi
    @Inject
    lateinit var repo:LoginPageRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

        handleBottomNavBar()


        val retrofit = Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

         val userToSignIn = RequestSignIn("jeffbezos@gmail.com","123456")
         val userToSignUp = RequestSignUp("Jeff Bezos","jeffbezosx@gmail.com","123456")
/*
        scope.launch {
            val response = retrofit.sendSignInRequest(userToSignIn)
            println(response)
            println(response.body())
         }


        scope.launch {
            val response = retrofit.sendSignUpRequest(userToSignUp)
          //  println(response)
          //  println(response.message())
         //   println(response.body())
        }
*/


    }




    private fun handleBottomNavBar(){
        bottom_navigation_menu.visibility = View.GONE

        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState)

                when (f) {
                    is ChangePasswordContinuePageFragment -> bottom_navigation_menu.visibility = View.GONE
                    is ChangePasswordFragment->bottom_navigation_menu.visibility = View.GONE
                    is LoginFragment -> bottom_navigation_menu.visibility = View.GONE
                    is SignUpFragment -> bottom_navigation_menu.visibility = View.GONE
                    else -> bottom_navigation_menu.visibility = View.VISIBLE

                }

            }

        }, true)
    }



}