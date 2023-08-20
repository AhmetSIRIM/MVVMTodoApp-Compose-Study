package com.asirim.mvvmtodoappstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asirim.mvvmtodoappstudy.ui.theme.MVVMTodoAppStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppStudyTheme {

            }
        }
    }
}