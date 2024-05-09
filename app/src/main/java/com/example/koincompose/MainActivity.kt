package com.example.koincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.koincompose.ui.screen.GradingScreen
import com.example.koincompose.ui.screen.RegisterScreen
import com.example.koincompose.ui.theme.KoinComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "main_screen"
                    ) {
                        composable("main_screen") {
                            MainScreen(navController)
                        }
                        composable("register_screen") {
                            RegisterScreen(navController)
                        }
                        composable("grading_screen") {
                            GradingScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
//    val vm: AttendanceViewModel = koinViewModel()
//    val initialData = listOf(
//        Attendance(id = 1, name = "John", score = null, register = false),
//        Attendance(id = 2, name = "Alice", score = null, register = false),
//        Attendance(id = 3, name = "Bob", score = null, register = false),
//        Attendance(id = 4, name = "Emma", score = null, register = false),
//        Attendance(id = 5, name = "Michael", score = null, register = false),
//        Attendance(id = 6, name = "Sophia", score = null, register = false),
//        Attendance(id = 7, name = "William", score = null, register = false),
//        Attendance(id = 8, name = "Olivia", score = null, register = false),
//        Attendance(id = 9, name = "James", score = null, register = false),
//        Attendance(id = 10, name = "Emily", score = null, register = false)
//    )
//
//
//    if (vm.attendances.observeAsState().value.isNullOrEmpty()) {
//        initialData.forEach() { attendance ->
//            vm.insert(attendance)
//        }
//    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.koin_assignment))
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("register_screen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.register_screen))
            }
            Button(
                onClick = { navController.navigate("grading_screen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.grading_screen))
            }
        }
    }
}

