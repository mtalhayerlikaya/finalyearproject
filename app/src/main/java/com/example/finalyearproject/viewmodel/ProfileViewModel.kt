package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.ItemResponse
import com.example.finalyearproject.repository.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(val repo: ProfileRepo): ViewModel() {

    private val itemsForProfile_ = MutableLiveData<ItemResponse?>()
    val itemsForProfile : LiveData<ItemResponse?>
        get() = itemsForProfile_

    fun getItem()=viewModelScope.launch{
        itemsForProfile_.value = repo.getItems()
    }

    fun clearResponse(){
        itemsForProfile_.value= null
    }

}