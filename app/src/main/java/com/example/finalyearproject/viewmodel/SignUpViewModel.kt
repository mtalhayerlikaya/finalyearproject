package com.example.finalyearproject.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.repository.LoginPageRepository
import com.example.finalyearproject.repository.SignUpPageRepository
import com.example.finalyearproject.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(val repo: SignUpPageRepository) : ViewModel() {


    private val sgnUpResponse = MutableLiveData<ResponseSignUp?>()
    val responseSignUpResponse : LiveData<ResponseSignUp?>
        get() = sgnUpResponse



    fun signUpRequest(requestSignUp: RequestSignUp) = viewModelScope.launch {

            sgnUpResponse.postValue(repo.signUpRequest(requestSignUp))

    }

    fun clearResponse(){
        sgnUpResponse.value= null
    }



}