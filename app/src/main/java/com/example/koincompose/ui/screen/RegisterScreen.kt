package com.example.koincompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koincompose.data.model.Attendance
import com.example.koincompose.data.viewmodel.AttendanceViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.example.koincompose.R

@Preview
@Composable
fun RegisterScreen() {
    val vm: AttendanceViewModel = koinViewModel()
    val attendances = vm.attendances.observeAsState(initial = emptyList())
    val selectedAttendances = remember { mutableListOf<Attendance>() }
    Column {
        Text(
            text = stringResource(id = R.string.register), modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
        )
        attendances.value?.let {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(it) { attendance ->
                    AttendanceItemView(attendance = attendance, onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedAttendances += attendance
                        } else {
                            selectedAttendances -= attendance
                        }
                    })
                }
            }
        }

        Button(onClick = {
            selectedAttendances.forEach { selectedAttendance ->
                vm.updateAttendance(selectedAttendance)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.save_to_prefs))
        }
    }


}

@Composable
fun AttendanceItemView(attendance: Attendance, onCheckedChange: (Boolean) -> Unit) {
    var checkValue by remember { mutableStateOf(attendance.register) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = checkValue,
                onCheckedChange = { isChecked ->
                    checkValue = isChecked
                    onCheckedChange(isChecked)
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = attendance.name,
                modifier = Modifier.weight(1f) // Giãn text để nó chiếm hết phần còn lại của Row
            )
        }
    }
}