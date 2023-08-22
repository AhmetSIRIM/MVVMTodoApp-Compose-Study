package com.asirim.mvvmtodoappcomposestudy.util

import com.asirim.mvvmtodoappcomposestudy.data.Todo

enum class DummyData(
    val dummyTodo: Todo
) {

    DUMMY_LEARN_COMPOSE_TODO(
        Todo(
            title = "Learn Compose",
            description = "I like 'Compose' so I am eager to learn 'Compose'. I will study 'Compose'",
            decidedAt = "24 March 1997", // 24 March 1997
            isDone = false
        )
    ),

    DUMMY_GET_JOB_TODO(
        Todo(
            title = "Earn Money",
            description = "I have to earn money. I need it",
            decidedAt = "29 October 1923", // 29 October 1923
            isDone = false
        )
    ),

    DUMMY_MOVE_TO_ANOTHER_CITY_TODO(
        Todo(
            title = "I Don't Like Konya",
            description = "I will move the another city soon",
            decidedAt = "8 December 1943", // 8 December 1943
            isDone = false
        )
    ),

    DUMMY_CELEBRATE_GETTING_HIRED(
        Todo(
            title = "Celebrate Getting Hired",
            description = "Take your family out for a meal and celebrate the new job",
            decidedAt = "20 February 1967", // 20 February 1967
            isDone = false
        )
    ),

    DUMMY_BUY_THE_ELECTRIC_SCOOTER_TODO(
        Todo(
            title = "Buy an Electric Scooter",
            description = "Take a short ride and spend some time with yourself",
            decidedAt = "22 August 2023", // 22 August 2023
            isDone = false
        )
    )

}
