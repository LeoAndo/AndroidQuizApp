package com.leoleo.androidapptemplate.ui.quiz.completed

import androidx.compose.runtime.Immutable
import com.leoleo.androidapptemplate.domain.model.CompletedQuiz


sealed interface CompletedQuizUiState

object Initial : CompletedQuizUiState
@Immutable
data class Data(val dataList: List<CompletedQuiz>) : CompletedQuizUiState
object Empty : CompletedQuizUiState

@Immutable
data class Error(val errorMessage: String) : CompletedQuizUiState
