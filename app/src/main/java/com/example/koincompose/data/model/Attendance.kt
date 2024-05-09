package com.example.koincompose.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName="table_attendance")
@Parcelize
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val name:String,
    var score: Float?,
    var register:Boolean
): Parcelable