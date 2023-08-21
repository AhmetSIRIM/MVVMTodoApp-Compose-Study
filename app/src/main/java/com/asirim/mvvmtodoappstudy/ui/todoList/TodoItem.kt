package com.asirim.mvvmtodoappstudy.ui.todoList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asirim.mvvmtodoappstudy.data.Todo
import com.asirim.mvvmtodoappstudy.ui.theme.MVVMTodoAppStudyTheme
import com.asirim.mvvmtodoappstudy.ui.theme.TodoColorEnum
import com.asirim.mvvmtodoappstudy.util.DummyData
import com.asirim.mvvmtodoappstudy.util.UiEvent

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .background(
                color = TodoColorEnum
                    .values()
                    .random().value
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                IconButton(
                    onClick = {
                        onEvent(TodoListEvent.OnDeleteTodoClick(todo))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            todo.description?.let {
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                Text(text = it)
            }
        }
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = {
                onEvent(TodoListEvent.OnDoneUpdateClick(todo, it))
            }
        )
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