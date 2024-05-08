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
    private var _attendanceRegisters = MutableLiveData<List<Attendance>>()
    val attendanceRegisters get() = _attendanceRegisters

    init {
        getAllAttendances()
        getAllAttendanceRegisters()
    }

    private fun getAllAttendances() {
        viewModelScope.launch {
            appRepository.getAllAttendances().observeForever { attendances ->
                _attendances.value = attendances
            }
        }
    }
    private fun getAllAttendanceRegisters() {
        viewModelScope.launch {
            appRepository.getAllAttendanceRegisters().observeForever { attendances ->
                attendanceRegisters.value = attendances
            }
        }
    }

    fun insert(attendance: Attendance) {
        viewModelScope.launch() {
            appRepository.insert(attendance)
        }
    }
    fun updateAttendance(attendance: Attendance){
        viewModelScope.launch {
            appRepository.updateAttendance(attendance)
        }
    }
}
