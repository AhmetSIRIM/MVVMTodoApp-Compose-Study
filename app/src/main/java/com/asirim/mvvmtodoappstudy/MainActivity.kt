package com.asirim.mvvmtodoappstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.asirim.mvvmtodoappstudy.ui.theme.MVVMTodoAppStudyTheme
import com.asirim.mvvmtodoappstudy.ui.todoList.TodoItem
import com.asirim.mvvmtodoappstudy.util.DummyData
import com.asirim.mvvmtodoappstudy.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppStudyTheme {

                Column {
                    TodoItem(
                        todo = DummyData.dummyCompleteProjectTodo,
                        onEvent = { UiEvent.PopBackStack })
                    TodoItem(
                        todo = DummyData.dummyGetJobTodo,
                        onEvent = { UiEvent.PopBackStack })
                    TodoItem(
                        todo = DummyData.dummyMoveToAnotherCityTodo,
                        onEvent = { UiEvent.PopBackStack })
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMTodoAppStudyTheme {

        Column {
            TodoItem(
                todo = DummyData.dummyCompleteProjectTodo,
                onEvent = { UiEvent.PopBackStack })
            TodoItem(
                todo = DummyData.dummyGetJobTodo,
                onEvent = { UiEvent.PopBackStack })
            TodoItem(
                todo = DummyData.dummyMoveToAnotherCityTodo,
                onEvent = { UiEvent.PopBackStack })
        }

    }
}