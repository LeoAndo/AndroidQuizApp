package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.runtime.Immutable

@Immutable
internal data class QuizUiState(
    val isFinishedQuiz: Boolean = false,
    val collectAnswerCount: Int = 0,
    val selectedQuestion: Quiz = Quiz.Q01,
    val errorMessage: String? = null,
)
