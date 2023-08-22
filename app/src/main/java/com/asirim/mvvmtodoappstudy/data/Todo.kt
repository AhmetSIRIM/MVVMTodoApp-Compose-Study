package com.asirim.mvvmtodoappstudy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(

    /** ["Room Library will handle this field so this will fill the Room Library even though it is nullable."](https://youtu.be/A7CGcFjQQtQ?t=460) */
    @PrimaryKey
    val id: Int? = null,

    val title: String,

    val description: String?,

    var decidedAt: String,

    val isDone: Boolean

)