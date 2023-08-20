package com.asirim.mvvmtodoappstudy.di

import android.content.Context
import androidx.room.Room
import com.asirim.mvvmtodoappstudy.data.TodoDao
import com.asirim.mvvmtodoappstudy.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoDatabaseModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        @ApplicationContext
        applicationContext: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "database-todo"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(
        todoDatabase: TodoDatabase
    ): TodoDao {
        return todoDatabase.todoDao
    }

}