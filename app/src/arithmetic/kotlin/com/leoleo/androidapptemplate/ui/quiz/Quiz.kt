package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.runtime.Immutable
import com.leoleo.androidapptemplate.R

@Immutable
internal enum class Quiz(
    val titleResId: Int,
    val data: List<QuestionData>,
) {
    Q01(
        R.string.quiz_01,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_01_question_01,
                answers = listOf("2", "3", "4", "5", "6"),
                answerIndex = 0
            ),
            QuestionData(
                messageResId = R.string.quiz_01_question_02,
                answers = listOf("12", "16", "18"),
                answerIndex = 1
            ),
            QuestionData(
                messageResId = R.string.quiz_01_question_03,
                answers = listOf("155", "152", "151", "150"),
                answerIndex = 2
            ),
        )
    ),

    Q02(
        R.string.quiz_02,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_02_question_01,
                answers = listOf("2", "8", "1", "0"),
                answerIndex = 3
            ),
            QuestionData(
                messageResId = R.string.quiz_02_question_02,
                answers = listOf("2", "0", "1", "8"),
                answerIndex = 1
            ),
            QuestionData(
                messageResId = R.string.quiz_02_question_03,
                answers = listOf("-92", "90", "95", "-95"),
                answerIndex = 3
            ),
        )
    ),
    Q03(
        R.string.quiz_03,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_03_question_01,
                answers = listOf("2", "8", "1", "0"),
                answerIndex = 2
            ),
            QuestionData(
                messageResId = R.string.quiz_03_question_02,
                answers = listOf("2", "64", "1", "8"),
                answerIndex = 1
            ),
            QuestionData(
                messageResId = R.string.quiz_03_question_03,
                answers = listOf("3440", "3442", "3444", "3448"),
                answerIndex = 2
            ),
        )
    ),
}