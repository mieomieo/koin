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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koincompose.data.model.Attendance

@Preview
@Composable
fun GradingScreen() {
    val attendances = listOf(
        Attendance(id = 1, name = "John", score = 85.5f, register = true),
        Attendance(id = 2, name = "Alice", score = 90.0f, register = true),
        Attendance(id = 3, name = "Bob", score = 78.2f, register = false),
        Attendance(id = 4, name = "Emma", score = 95.7f, register = true),
        Attendance(id = 5, name = "Michael", score = 81.3f, register = false),
        Attendance(id = 6, name = "Sophia", score = 88.9f, register = true),
        Attendance(id = 7, name = "William", score = 75.6f, register = false),
        Attendance(id = 8, name = "Olivia", score = 92.4f, register = true),
        Attendance(id = 9, name = "James", score = 79.8f, register = true),
        Attendance(id = 10, name = "Emily", score = 87.1f, register = false)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)

    ) {
        Text(
            text = "Grading", modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(attendances) {
                AttendanceGradingItemView(it)
            }
        }
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save to db")
        }
    }
}

@Composable
fun AttendanceGradingItemView(attendance: Attendance) {
    var numberScore by remember {
        mutableStateOf("")
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