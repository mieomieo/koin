package com.example.koincompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koincompose.data.dao.AttendanceDao
import com.example.koincompose.data.model.Attendance

@Database(entities = [Attendance::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun attendanceDao(): AttendanceDao
}