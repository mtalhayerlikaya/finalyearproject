package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.EmailResponse
import com.example.finalyearproject.model.RequestChangePassword
import com.example.finalyearproject.model.ResponseChangePassword
import com.example.finalyearproject.repository.ChangePasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ChangePasswordViewModel
@Inject
constructor(val repo : ChangePasswordRepository) : ViewModel() {



    private val resetPageResponse = MutableLiveData<ResponseChangePassword>()
    val resetPageResponse_ : LiveData<ResponseChangePassword>
        get() = resetPageResponse



    fun resetPassword(request:RequestChangePassword,token:String) = viewModelScope.launch{

        resetPageResponse.postValue(repo.resetPassword(token,request))

    }





}