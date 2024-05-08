package com.example.koincompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.koincompose.data.model.Attendance

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM table_attendance")
    fun getAllAttendances():LiveData<List<Attendance>>

    @Query("SELECT * FROM table_attendance WHERE register")
    fun getAllAttendanceRegister():LiveData<List<Attendance>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: Attendance)
    @Update
    suspend fun updateAttendance(attendance: Attendance)
}