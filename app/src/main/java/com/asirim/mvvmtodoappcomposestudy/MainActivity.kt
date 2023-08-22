package com.asirim.mvvmtodoappcomposestudy

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
import com.asirim.mvvmtodoappcomposestudy.ui.theme.MVVMTodoAppComposeStudyTheme
import com.asirim.mvvmtodoappcomposestudy.ui.todoList.TodoItem
import com.asirim.mvvmtodoappcomposestudy.ui.todoList.TodoListScreen
import com.asirim.mvvmtodoappcomposestudy.ui.todoOperation.TodoOperationScreen
import com.asirim.mvvmtodoappcomposestudy.util.DummyData
import com.asirim.mvvmtodoappcomposestudy.util.Routes
import com.asirim.mvvmtodoappcomposestudy.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppComposeStudyTheme {

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
    MVVMTodoAppComposeStudyTheme {

        Column {
            TodoItem(
                todo = DummyData.DUMMY_LEARN_COMPOSE_TODO.dummyTodo,
                onEvent = { UiEvent.PopBackStack })
            TodoItem(
                todo = DummyData.DUMMY_GET_JOB_TODO.dummyTodo,
                onEvent = { UiEvent.PopBackStack })
            TodoItem(
                todo = DummyData.DUMMY_MOVE_TO_ANOTHER_CITY_TODO.dummyTodo,
                onEvent = { UiEvent.PopBackStack })
        }

    }
}