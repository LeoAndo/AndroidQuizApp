package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.runtime.Immutable

@Immutable
internal data class QuestionData(
    val messageResId: Int, // TODO R.string.xxxx
    val answerResId: Int,  // TODO R.array.xxxx
    val answerIndex: Int
)