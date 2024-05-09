package com.example.koincompose.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koincompose.R
import com.example.koincompose.data.model.Attendance
import com.example.koincompose.data.viewmodel.AttendanceViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun GradingScreen(navController: NavController) {
    val vm: AttendanceViewModel = koinViewModel()
    val attendances = vm.attendanceRegisters.observeAsState(emptyList())
    val tempAttendances = remember { mutableStateListOf<Attendance>() }
    val scope = rememberCoroutineScope()
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
                    AttendanceGradingItemView(attendance, onValueChange = {
                        attendance.score = it
                    })
                }
            }
        }
        Button(onClick = {
            scope.launch {
                val job = GlobalScope.launch {
//                    val debug = tempAttendances
                    attendances.value.forEach { attendance ->
                        vm.updateAttendance(attendance)
                    }
                }
                job.join()
//                Toast.makeText(LocalContext.current,"Saved Score Successfully",Toast.LENGTH_SHORT).show()
                navController.navigate("main_screen")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.save_to_db))
        }
    }
}

@Composable
fun AttendanceGradingItemView(attendance: Attendance, onValueChange: (Float) -> Unit) {
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
                onValueChange = {
                    numberScore = it
                    numberScore.toFloatOrNull()?.let { it1 -> onValueChange(it1) }
                },
                placeholder = { Text("0-10") },
                modifier = Modifier.weight(0.3f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }

}