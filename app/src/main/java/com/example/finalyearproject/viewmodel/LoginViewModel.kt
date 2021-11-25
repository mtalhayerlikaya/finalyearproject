package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.SignIn
import com.example.finalyearproject.model.SignUp
import com.example.finalyearproject.model.UserSignIn
import com.example.finalyearproject.model.UserSignUp
import com.example.finalyearproject.repository.LoginPageRepository

import com.example.finalyearproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repo : LoginPageRepository
): ViewModel() {



    private val sgnInResponse = MutableLiveData<Resource<SignIn>>()
    val signInResponse : LiveData<Resource<SignIn>>
        get() = sgnInResponse

    private val sgnUpResponse = MutableLiveData<Resource<SignUp>>()
    val signUpResponse : LiveData<Resource<SignUp>>
        get() = sgnUpResponse




    fun signInRequest(userSignIn: UserSignIn) = viewModelScope.launch {
        sgnInResponse.value = repo.signInRequest(userSignIn)
    }

    fun signUpRequest(userSignUp: UserSignUp) = viewModelScope.launch {
        sgnUpResponse.value = repo.signUpRequest(userSignUp)
    }


}