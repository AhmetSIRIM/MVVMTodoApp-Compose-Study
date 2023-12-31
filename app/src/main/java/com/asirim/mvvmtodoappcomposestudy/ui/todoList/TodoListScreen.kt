package com.asirim.mvvmtodoappcomposestudy.ui.todoList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asirim.mvvmtodoappcomposestudy.ui.theme.LightGreen
import com.asirim.mvvmtodoappcomposestudy.util.DummyData
import com.asirim.mvvmtodoappcomposestudy.util.UiEvent

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
        modifier = Modifier.padding(top = 6.dp),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = {
                        todoListViewModel.onEvent(
                            TodoListEvent.OnAddSampleTodoTodoClick(
                                DummyData.values().random().dummyTodo
                            )
                        )
                    },
                    backgroundColor = LightGreen
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Add Sample Todo"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(
                    onClick = { todoListViewModel.onEvent(TodoListEvent.OnAddTodoClick) },
                    backgroundColor = LightGreen
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(allTodo.value) { todo ->
                TodoItem(
                    todo = todo,
                    onEvent = todoListViewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { todoListViewModel.onEvent(TodoListEvent.OnTodoClick(todo)) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}