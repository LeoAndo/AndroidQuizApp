package com.leoleo.androidapptemplate.domain.repository

import com.leoleo.androidapptemplate.domain.model.CompletedQuiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun observeAllCompletedQuiz(): Flow<List<CompletedQuiz>>
    suspend fun addCompleteData(title: String, completedTime: Long)
}