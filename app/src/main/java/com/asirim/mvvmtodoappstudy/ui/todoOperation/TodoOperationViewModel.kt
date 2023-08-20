package com.asirim.mvvmtodoappstudy.ui.todoOperation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.asirim.mvvmtodoappstudy.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoOperationViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

}