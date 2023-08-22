package com.asirim.mvvmtodoappstudy.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.asirim.mvvmtodoappstudy.data.TodoDao
import com.asirim.mvvmtodoappstudy.data.TodoDatabase
import com.asirim.mvvmtodoappstudy.util.DummyData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoDatabaseModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        @ApplicationContext
        applicationContext: Context,
        applicationScope: CoroutineScope
    ): TodoDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "database-todo"
        ).addCallback(object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                applicationScope.launch {
                    provideTodoDatabase(applicationContext, applicationScope)
                        .todoDao.createTodo(DummyData.DUMMY_LEARN_COMPOSE_TODO.dummyTodo)
                }
            }
        }).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(
        todoDatabase: TodoDatabase
    ): TodoDao {
        return todoDatabase.todoDao
    }

    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

}