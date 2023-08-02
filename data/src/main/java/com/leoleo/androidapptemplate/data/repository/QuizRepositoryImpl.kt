package com.leoleo.androidapptemplate.data.repository

import com.leoleo.androidapptemplate.data.quiz.dao.CompletedQuizDao
import com.leoleo.androidapptemplate.data.quiz.entity.CompletedQuizEntity
import com.leoleo.androidapptemplate.data.quiz.entity.toModels
import com.leoleo.androidapptemplate.domain.model.CompletedQuiz
import com.leoleo.androidapptemplate.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class QuizRepositoryImpl @Inject constructor(private val dao: CompletedQuizDao) :
    QuizRepository {
    override fun observeAllCompletedQuiz(): Flow<List<CompletedQuiz>> {
        return dao.observeAllCompletedQuiz().map { it.toModels() }
    }

    override suspend fun addCompleteData(title: String, completedTime: Long) {
        val data = CompletedQuizEntity(title = title, completedTime = completedTime)
        if (dao.selectCompletedQuizByTitle(title).isEmpty()) {
            dao.insertQuizData(data)
        }
    }
}