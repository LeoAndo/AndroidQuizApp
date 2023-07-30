package com.leoleo.androidapptemplate.data.di

import com.leoleo.androidapptemplate.data.repository.QuizRepositoryImpl
import com.leoleo.androidapptemplate.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindQuizRepository(impl: QuizRepositoryImpl): QuizRepository
}