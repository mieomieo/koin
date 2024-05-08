package com.example.koincompose.data.repository

import androidx.lifecycle.LiveData
import com.example.koincompose.data.dao.AttendanceDao
import com.example.koincompose.data.model.Attendance

class AppRepositoryImpl(private val attendanceDao: AttendanceDao) : AppRepository {
    override fun getAllAttendances(): LiveData<List<Attendance>> {
        return attendanceDao.getAllAttendances()
    }

    override fun getAllAttendanceRegisters(): LiveData<List<Attendance>> {
        return attendanceDao.getAllAttendanceRegister()
    }


    override suspend fun insert(attendance: Attendance) {
        attendanceDao.insertAttendance(attendance)
    }

    override suspend fun updateAttendance(attendance: Attendance) {
        attendanceDao.updateAttendance(attendance)
    }
}