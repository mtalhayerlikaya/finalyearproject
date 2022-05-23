package com.example.finalyearproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.model.UpdateRequest
import com.example.finalyearproject.model.UpdateResponse
import com.example.finalyearproject.repository.UpdateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel
@Inject
constructor(val repo:UpdateRepository) : ViewModel() {

    private var updateMutLiveData = MutableLiveData<UpdateResponse?>()
    val updateLiveData : LiveData<UpdateResponse?>
        get() = updateMutLiveData

    init {
        updateMutLiveData = repo.returnRepoLiveData()
    }

    fun loadLiveData(updateRequest: UpdateRequest)=viewModelScope.launch{
        repo.sendUpdateRequest(updateRequest)
    }

    fun clearResponse(){
        updateMutLiveData.value= null
    }

}