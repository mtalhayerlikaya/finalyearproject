package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.RequestUpdate
import com.example.finalyearproject.model.ResponseUpdate
import com.example.finalyearproject.repository.ChangePasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ChangePasswordViewModel
@Inject
constructor(val repo : ChangePasswordRepository) : ViewModel() {



    private val resetPageResponse = MutableLiveData<ResponseUpdate?>()
    val resetPageResponse_ : LiveData<ResponseUpdate?>
        get() = resetPageResponse



    fun resetPassword(request: RequestUpdate, token:String) = viewModelScope.launch{

        resetPageResponse.postValue(repo.resetPassword(token,request))

    }

    fun clearResponse(){
        resetPageResponse.value= null
    }





}