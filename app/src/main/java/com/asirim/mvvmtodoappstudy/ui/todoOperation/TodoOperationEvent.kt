package com.asirim.mvvmtodoappstudy.ui.todoOperation

sealed class TodoOperationEvent {
    data class OnTitleChangeClick(
        val title: String
    ) : TodoOperationEvent()


    data class OnDescriptionChangeClick(
        val description: String
    ) : TodoOperationEvent()

    object OnSaveTodoOperationClick : TodoOperationEvent()

}