package com.example.lab_week_10.repositories

import com.example.lab_week_10.database.Total
import com.example.lab_week_10.database.TotalDao

class TotalRepository(private val totalDao: TotalDao) {

    suspend fun getTotal() = totalDao.getFirstTotal()

    suspend fun insert(total: Total) {
        totalDao.insert(total)
    }

    suspend fun update(total: Total) {
        totalDao.update(total)
    }
}