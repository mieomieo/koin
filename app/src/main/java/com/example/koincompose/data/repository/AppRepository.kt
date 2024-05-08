package com.example.koincompose.data.repository

import androidx.lifecycle.LiveData
import com.example.koincompose.data.model.Attendance

interface AppRepository {
    fun getAllAttendances():LiveData<List<Attendance>>
    fun getAllAttendanceRegisters():LiveData<List<Attendance>>
    suspend fun insert(attendance: Attendance)
}