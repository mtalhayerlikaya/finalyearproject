package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.ItemResponse
import com.example.finalyearproject.repository.RepositoryForTabs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabsViewModel
@Inject
constructor(val repo:RepositoryForTabs): ViewModel() {


    private val items_ = MutableLiveData<ItemResponse>()
    val item : LiveData<ItemResponse>
        get() = items_

    fun getItem() = viewModelScope.launch{
        items_.postValue(repo.getItems())
    }






}