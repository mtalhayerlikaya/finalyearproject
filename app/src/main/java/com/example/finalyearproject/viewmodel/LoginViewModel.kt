package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.ResponseSignIn
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.model.RequestSignIn
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.repository.LoginPageRepository

import com.example.finalyearproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repo : LoginPageRepository
): ViewModel() {



    private val sgnInResponse = MutableLiveData<ResponseSignIn>()
    val responseSignInResponse : LiveData<ResponseSignIn>
        get() = sgnInResponse


    fun signInRequest(requestSignIn: RequestSignIn) = viewModelScope.launch {
        sgnInResponse.value = repo.signInRequest(requestSignIn)
    }

    fun deneme(){

        println("deneme")

        //sgnInResponse.postValue(repo.signInRequest(requestSignIn))

    }




}