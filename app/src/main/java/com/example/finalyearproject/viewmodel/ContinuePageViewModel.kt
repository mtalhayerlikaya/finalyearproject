package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.EmailRequest
import com.example.finalyearproject.model.EmailResponse
import com.example.finalyearproject.model.ResponseSignIn
import com.example.finalyearproject.repository.ContinuePageRepo
import com.example.finalyearproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinuePageViewModel
@Inject
constructor(val repo:ContinuePageRepo): ViewModel(){


    private val continuePageEmailResponse = MutableLiveData<EmailResponse?>()
    val continuePageEmailResponse_ : LiveData<EmailResponse?>
        get() = continuePageEmailResponse

    fun sendEmailRequest(request:EmailRequest) = viewModelScope.launch {

        continuePageEmailResponse.postValue(repo.sendEmail(request))

    }

    fun clearResponse(){
        continuePageEmailResponse.value= null
    }



}