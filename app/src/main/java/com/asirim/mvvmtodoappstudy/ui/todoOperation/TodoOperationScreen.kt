package com.asirim.mvvmtodoappstudy.ui.todoOperation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asirim.mvvmtodoappstudy.util.UiEvent

@Composable
fun TodoOperationScreen(
    onPopBackStack: () -> Unit,
    todoOperationViewModel: TodoOperationViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        todoOperationViewModel.uiEvent.collect { uiEvent ->

            when (uiEvent) {

                is UiEvent.Navigate -> Unit

                is UiEvent.PopBackStack -> onPopBackStack()

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        actionLabel = uiEvent.action
                    )
                }

            }

        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { todoOperationViewModel.onEvent(TodoOperationEvent.OnSaveTodoClick) }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = todoOperationViewModel.title,
                onValueChange = {
                    todoOperationViewModel.onEvent(TodoOperationEvent.OnTitleChangeClick(it))
                },
                placeholder = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = todoOperationViewModel.description,
                onValueChange = {
                    todoOperationViewModel.onEvent(TodoOperationEvent.OnDescriptionChangeClick(it))
                },
                placeholder = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}