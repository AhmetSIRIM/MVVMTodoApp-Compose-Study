package com.asirim.mvvmtodoappstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asirim.mvvmtodoappstudy.ui.theme.MVVMTodoAppStudyTheme
import com.asirim.mvvmtodoappstudy.ui.todoList.TodoItem
import com.asirim.mvvmtodoappstudy.ui.todoList.TodoListScreen
import com.asirim.mvvmtodoappstudy.ui.todoOperation.TodoOperationScreen
import com.asirim.mvvmtodoappstudy.util.DummyData
import com.asirim.mvvmtodoappstudy.util.Routes
import com.asirim.mvvmtodoappstudy.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppStudyTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.TODO_LIST
                ) {

                    composable(Routes.TODO_LIST) {
                        TodoListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }

                    composable(
                        route = Routes.TODO_OPERATION + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        TodoOperationScreen(
                            onPopBackStack = { navController.popBackStack() }
                        )
                    }

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