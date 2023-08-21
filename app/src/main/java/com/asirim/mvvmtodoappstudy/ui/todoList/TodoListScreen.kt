package com.asirim.mvvmtodoappstudy.ui.todoList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asirim.mvvmtodoappstudy.util.UiEvent

/**
 * ["We will not need 'UiEvent.PopBackStack' on this screen"](https://youtu.be/A7CGcFjQQtQ?t=3621)
 *
 * ["How do we capture the click event in the 'snackbar'? We do this with 'val snackbarResult = XXX'."](https://youtu.be/A7CGcFjQQtQ?t=3669)
 */
@Composable
fun TodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    todoListViewModel: TodoListViewModel = hiltViewModel()
) {

    // TODO (Ahmet) ---> I will try the 'initial' with that -> listOf(dummyCompleteProjectTodo, dummyGetJobTodo, dummyMoveToAnotherCityTodo)
    val allTodo = todoListViewModel.allTodo.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        todoListViewModel.uiEvent.collect { uiEvent ->

            when (uiEvent) {

                is UiEvent.Navigate -> onNavigate(uiEvent)

                is UiEvent.PopBackStack -> Unit

                is UiEvent.ShowSnackbar -> {

                    val snackbarResult = scaffoldState
                        .snackbarHostState
                        .showSnackbar(
                            message = uiEvent.message,
                            actionLabel = uiEvent.action
                        )

                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                        todoListViewModel
                            .onEvent(TodoListEvent.OnUndoDeleteClick)
                    }

                }

            }

        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { todoListViewModel.onEvent(TodoListEvent.OnAddTodoClick) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(allTodo.value) { todo ->
                TodoItem(
                    todo = todo,
                    onEvent = todoListViewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { todoListViewModel.onEvent(TodoListEvent.OnTodoClick(todo)) }
                        .padding(16.dp) // TODO (Ahmet) ---> This line may cause problems. If this line causes the problem, either delete it here or the '.padding(10.dp)' in TodoItem.
                )
            }
        }
    }
}