package com.example.lab_week_10.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_10.database.Total
import com.example.lab_week_10.repositories.TotalRepository
import kotlinx.coroutines.launch

class TotalViewModel(private val repository: TotalRepository) : ViewModel() {
    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total
    
    private var currentId: Long = 1L

    init {
        viewModelScope.launch {
            val totalFromDb = repository.getTotal()
            if (totalFromDb != null) {
                currentId = totalFromDb.id
                _total.postValue(totalFromDb.total)
                Log.d("TotalViewModel", "✅ Data loaded from DB - ID: ${totalFromDb.id}, Total: ${totalFromDb.total}")
            } else {
                val newTotal = Total(id = 1L, total = 0)
                repository.insert(newTotal)
                currentId = 1L
                _total.postValue(0)
                Log.d("TotalViewModel", "📝 New data created - ID: 1, Total: 0")
            }
        }
    }

    fun incrementTotal() {
        viewModelScope.launch {
            val currentTotal = _total.value ?: 0
            val newTotal = currentTotal + 1
            // Use the stored ID
            repository.update(Total(id = currentId, total = newTotal))
            _total.postValue(newTotal)
            Log.d("TotalViewModel", "➕ Data updated - ID: $currentId, Total: $newTotal")
        }
    }
}