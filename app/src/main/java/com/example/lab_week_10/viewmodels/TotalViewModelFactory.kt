package com.example.lab_week_10.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.repositories.TotalRepository

class TotalViewModelFactory(private val repository: TotalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TotalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TotalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}