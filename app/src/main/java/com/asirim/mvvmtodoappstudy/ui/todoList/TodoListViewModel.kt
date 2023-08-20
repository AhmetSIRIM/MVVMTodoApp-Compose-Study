package com.asirim.mvvmtodoappstudy.ui.todoList

import androidx.lifecycle.ViewModel
import com.asirim.mvvmtodoappstudy.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

}