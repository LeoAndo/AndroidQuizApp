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
                answers = listOf(
                    "George Washington",
                    "John Adams",
                    "John Quincy Adams",
                    "Andrew Johnson",
                    "Donald John Trump"
                ),
                answerIndex = 0
            ),
            QuestionData(
                messageResId = R.string.quiz_01_question_02,
                answers = listOf("Chicago", "Louisiana", "Michigan", "Georgia", "Washington D.C."),
                answerIndex = 4
            ),
            QuestionData(
                messageResId = R.string.quiz_01_question_03,
                answers = listOf("Japanese fencing", "McDonald\'s", "Sumo", "MLB"),
                answerIndex = 3
            ),
        )
    ),
    Q02(
        R.string.quiz_02,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_02_question_01,
                answers = listOf("190", "200", "201", "192", "198", "188"),
                answerIndex = 4
            ),
            QuestionData(
                messageResId = R.string.quiz_02_question_02,
                answers = listOf("190", "200", "206", "192", "198", "188", "210"),
                answerIndex = 2
            ),
            QuestionData(
                messageResId = R.string.quiz_02_question_03,
                answers = listOf("193", "200", "185"),
                answerIndex = 0
            ),
        ),
    ),
    Q03(
        R.string.quiz_03,
        listOf(
            QuestionData(
                messageResId = R.string.quiz_03_question_01,
                answers = listOf("Google", "Apple", "Microsoft", "Amazon", "Meta", "Yahoo", "Sony"),
                answerIndex = 0
            ),
            QuestionData(
                messageResId = R.string.quiz_03_question_02,
                answers = listOf("Google", "Apple", "Microsoft", "Amazon", "Meta", "Yahoo", "Sony"),
                answerIndex = 1
            ),
            QuestionData(
                messageResId = R.string.quiz_03_question_03,
                answers = listOf("Google", "Apple", "Microsoft", "Amazon", "Meta", "Yahoo", "Sony"),
                answerIndex = 2
            ),
        ),
    ),
}