package com.leoleo.androidapptemplate.data.quiz.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.leoleo.androidapptemplate.data.quiz.entity.COLUMN_TITLE
import com.leoleo.androidapptemplate.data.quiz.entity.CompletedQuizEntity
import com.leoleo.androidapptemplate.data.quiz.entity.TABLE_NAME_COMPLETED_QUIZ
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CompletedQuizDao {
    @Insert
    suspend fun insertQuizData(vararg entity: CompletedQuizEntity)

    @Update
    suspend fun updateQuizData(vararg entity: CompletedQuizEntity)

    @Query("SELECT * FROM $TABLE_NAME_COMPLETED_QUIZ")
    fun observeAllCompletedQuiz(): Flow<List<CompletedQuizEntity>>

    @Query("SELECT * FROM $TABLE_NAME_COMPLETED_QUIZ")
    suspend fun selectAllCompletedQuiz(): List<CompletedQuizEntity>

    @Query("SELECT * FROM $TABLE_NAME_COMPLETED_QUIZ WHERE $COLUMN_TITLE = :title")
    suspend fun selectCompletedQuizByTitle(title: String): List<CompletedQuizEntity>
}