package com.example.lesson6.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _liveData = MutableLiveData<Int>()

    val liveData: LiveData<Int>
        get() = _liveData

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _liveData.postValue(10)
            delay(1000)
            _liveData.postValue(20)
            delay(1000)
            _liveData.postValue(30)
            delay(1000)
            _liveData.postValue(40)
            delay(1000)
            _liveData.postValue(50)
            delay(1000)
        }
    }
}
