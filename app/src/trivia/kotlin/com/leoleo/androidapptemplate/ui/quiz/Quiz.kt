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
                answerResId = R.array.quiz_01_question_01_answers,
                answerIndex = 0
            ),
            QuestionData(
                messageResId = R.string.quiz_01_question_02,
                answerResId = R.array.quiz_01_question_02_answers,
                answerIndex = 4
            ),
            QuestionData(
                messageResId = R.string.quiz_01_question_03,
                answerResId = R.array.quiz_01_question_03_answers,
                answerIndex = 3
            ),
        )
    ),
    Q02(
        R.string.quiz_02,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_02_question_01,
                answerResId = R.array.quiz_02_question_01_answers,
                answerIndex = 4
            ),
            QuestionData(
                messageResId = R.string.quiz_02_question_02,
                answerResId = R.array.quiz_02_question_02_answers,
                answerIndex = 2
            ),
            QuestionData(
                messageResId = R.string.quiz_02_question_03,
                answerResId = R.array.quiz_02_question_03_answers,
                answerIndex = 0
            ),
        ),
    ),
    Q03(
        R.string.quiz_03,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_03_question_01,
                answerResId = R.array.quiz_03_question_common_answers,
                answerIndex = 0
            ),
            QuestionData(
                messageResId = R.string.quiz_03_question_02,
                answerResId = R.array.quiz_03_question_common_answers,
                answerIndex = 1
            ),
            QuestionData(
                messageResId = R.string.quiz_03_question_03,
                answerResId = R.array.quiz_03_question_common_answers,
                answerIndex = 2
            ),
        ),
    ),
}