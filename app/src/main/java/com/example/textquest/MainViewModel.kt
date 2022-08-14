package com.example.textquest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val repository: Repository): ViewModel() {

    val liveData = MutableLiveData<ScreenUi>()
    fun nextScreen(id: String) {
        val screenUi = repository.nextScreen(id)
        liveData.value = screenUi
    }
}