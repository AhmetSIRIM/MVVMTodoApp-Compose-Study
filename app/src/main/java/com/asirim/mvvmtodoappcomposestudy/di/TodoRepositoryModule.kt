package com.asirim.mvvmtodoappcomposestudy.di

import com.asirim.mvvmtodoappcomposestudy.data.TodoRepository
import com.asirim.mvvmtodoappcomposestudy.data.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class TodoRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository

}