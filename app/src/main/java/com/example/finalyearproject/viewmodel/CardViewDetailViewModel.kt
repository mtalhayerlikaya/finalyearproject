package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.LikeRequest
import com.example.finalyearproject.model.LikeResponse
import com.example.finalyearproject.repository.LikeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewDetailViewModel
@Inject
constructor(val repo : LikeRepo): ViewModel() {

    private val responseLike = MutableLiveData<LikeResponse?>()
    val responseLike_ : LiveData<LikeResponse?>
        get() = responseLike


    fun sendLikeRequest(token:String,request:LikeRequest)=viewModelScope.launch {
        responseLike.postValue(repo.sendLikeAndUnlikeRequest(token,request))
    }

    fun clearResponse(){
        responseLike.value= null
    }


}