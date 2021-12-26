package com.example.activitynavigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val number1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val number2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val math: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}