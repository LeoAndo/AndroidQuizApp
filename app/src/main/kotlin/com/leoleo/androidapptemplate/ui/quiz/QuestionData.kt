package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.runtime.Immutable

@Immutable
internal data class QuestionData(
    val messageResId: Int,
    val answers: List<String>,
    val answerIndex: Int
)