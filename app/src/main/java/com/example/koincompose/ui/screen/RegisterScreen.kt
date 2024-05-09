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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koincompose.R
import com.example.koincompose.data.model.Attendance
import com.example.koincompose.data.viewmodel.AttendanceViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val vm: AttendanceViewModel = koinViewModel()
    val scope = rememberCoroutineScope()
    val attendances = vm.attendances.observeAsState(initial = emptyList())
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
                             attendance.register=true
                        } else {
                            attendance.register=false
                        }
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
                navController.navigate("main_screen")
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
                modifier = Modifier.weight(1f)
            )
        }
    }
}