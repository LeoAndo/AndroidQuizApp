package com.leoleo.androidapptemplate.data.di

import android.content.Context
import com.leoleo.androidapptemplate.data.quiz.QuizDatabase
import com.leoleo.androidapptemplate.data.quiz.dao.CompletedQuizDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    internal fun provideQuizDatabase(@ApplicationContext context: Context): QuizDatabase {
        return QuizDatabase.getInstance(context)
    }

    @Provides
    internal fun provideCompletedQuizDao(database: QuizDatabase): CompletedQuizDao {
        return database.completedCompletedQuestionDao()
    }
}