package com.example.koincompose.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.koincompose.data.model.Attendance
import com.example.koincompose.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AttendanceViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var _attendances = MutableLiveData<List<Attendance>>()
    val attendances get() = _attendances

    init {
        getAllAttendances()
    }

    private fun getAllAttendances() {
        _attendances.value = appRepository.getAllAttendances().value
    }

    fun insert(attendance: Attendance) {
        viewModelScope.launch() {
            appRepository.insert(attendance)
        }
    }
}
//val initialData = listOf(
//    Attendance(id = 1, name = "John", score = 85.5f, register = true),
//    Attendance(id = 2, name = "Alice", score = 90.0f, register = true),
//    Attendance(id = 3, name = "Bob", score = 78.2f, register = false),
//    Attendance(id = 4, name = "Emma", score = 95.7f, register = true),
//    Attendance(id = 5, name = "Michael", score = 81.3f, register = false),
//    Attendance(id = 6, name = "Sophia", score = 88.9f, register = true),
//    Attendance(id = 7, name = "William", score = 75.6f, register = false),
//    Attendance(id = 8, name = "Olivia", score = 92.4f, register = true),
//    Attendance(id = 9, name = "James", score = 79.8f, register = true),
//    Attendance(id = 10, name = "Emily", score = 87.1f, register = false)
//)