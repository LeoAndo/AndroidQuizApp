package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.runtime.Immutable
import com.leoleo.androidapptemplate.R

@Immutable
internal data class QuestionData(
    val messageResId: Int,
    val answers: List<String>,
    val answerIndex: Int
)

@Immutable
internal enum class Quiz(
    val titleResId: Int,
    val data: List<QuestionData>,
) {
    Q01(
        R.string.quiz_01,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_01_msg_01,
                answers = listOf("Java", "PHP", "Ruby", "Go", "Swift"),
                answerIndex = 0
            ),
            QuestionData(
                messageResId = R.string.quiz_01_msg_02,
                answers = listOf("PHP", "Swift", "Ruby"),
                answerIndex = 1
            ),
            QuestionData(
                messageResId = R.string.quiz_01_msg_03,
                answers = listOf("word", "Xcode", "Figma", "Excel"),
                answerIndex = 2
            ),
        )
    ),
}