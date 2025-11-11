package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {
    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total

    fun setTotal(value: Int) {
        _total.value = value
    }

    fun incrementTotal() {
        val current = _total.value ?: 0
        _total.value = current + 1
    }
}