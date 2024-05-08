package com.example.koincompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koincompose.data.model.Attendance
import com.example.koincompose.data.viewmodel.AttendanceViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.koincompose.R

@Preview
@Composable
fun GradingScreen() {
    val vm: AttendanceViewModel = koinViewModel()
    val attendances = vm.attendanceRegisters.observeAsState(emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)

    ) {
        Text(
            text = stringResource(id = R.string.grading_screen), modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
        )
        attendances.value?.let {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(it) { attendance ->
                    AttendanceGradingItemView(attendance)
                }
            }
        }
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.save_to_db))
        }
    }
}

@Composable
fun AttendanceGradingItemView(attendance: Attendance) {
    var numberScore by remember {
        mutableStateOf(attendance.score?.toString() ?: "")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(
                text = attendance.name,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = numberScore,
                onValueChange = { numberScore = it },
                placeholder = { Text("0-10") },
                modifier = Modifier.weight(0.3f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }

}