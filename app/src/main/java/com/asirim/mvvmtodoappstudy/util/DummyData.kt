package com.asirim.mvvmtodoappstudy.util

import com.asirim.mvvmtodoappstudy.data.Todo

enum class DummyData(
    val dummyTodo: Todo
) {

    DUMMY_LEARN_COMPOSE_TODO(
        Todo(
            title = "Learn Compose",
            description = "I like 'Compose' so I am eager to learn 'Compose'. I will study 'Compose'",
            isDone = false
        )
    ),

    DUMMY_GET_JOB_TODO(
        Todo(
            title = "Earn Money",
            description = "I have to earn money. I need it",
            isDone = false
        )
    ),

    DUMMY_MOVE_TO_ANOTHER_CITY_TODO(
        Todo(
            title = "I Don't Like Konya",
            description = "I will move the another city soon",
            isDone = false
        )
    ),

    DUMMY_CELEBRATE_GETTING_HIRED(
        Todo(
            title = "Celebrate Getting Hired",
            description = "Take your family out for a meal and celebrate the new job",
            isDone = false
        )
    ),

    DUMMY_BUY_THE_ELECTRIC_SCOOTER_TODO(
        Todo(
            title = "Buy an Electric Scooter",
            description = "Take a short ride and spend some time with yourself",
            isDone = false
        )
    )

}