package com.example.koincompose.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.koincompose.data.dao.AttendanceDao
import com.example.koincompose.data.database.AppDatabase
import com.example.koincompose.data.model.Attendance
import com.example.koincompose.data.repository.AppRepository
import com.example.koincompose.data.repository.AppRepositoryImpl
import com.example.koincompose.data.viewmodel.AttendanceViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val appModule = module {
    single<AppDatabase> {
        Log.e("Cannot run here","Run2")
        provideDatabase(get()) }
    single<AttendanceDao> { get<AppDatabase>().attendanceDao() }
    single<AppRepository> { AppRepositoryImpl(get()) }
    viewModel {
        AttendanceViewModel(get())
    }
}


fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "attendance_db").build()
}