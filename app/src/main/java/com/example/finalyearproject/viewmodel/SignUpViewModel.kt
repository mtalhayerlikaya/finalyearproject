package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.repository.LoginPageRepository
import com.example.finalyearproject.repository.SignUpPageRepository
import com.example.finalyearproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(val repo: SignUpPageRepository) : ViewModel() {


    private val sgnUpResponse = MutableLiveData<Resource<ResponseSignUp>>()
    val responseSignUpResponse : LiveData<Resource<ResponseSignUp>>
        get() = sgnUpResponse



    fun signUpRequest(requestSignUp: RequestSignUp) = viewModelScope.launch {

        sgnUpResponse.postValue(repo.signUpRequest(requestSignUp))

    }






}