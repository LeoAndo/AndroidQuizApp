package com.leoleo.androidapptemplate.ui.compact

import androidx.compose.runtime.Immutable
import com.leoleo.androidapptemplate.R

@Immutable
data class Data(
    val message: String,
    val answers: List<String>,
    val answerIndex: Int
)

@Immutable
enum class Question(
    val titleResId: Int,
    val data: List<Data>,
) {
    KADAI01(
        R.string.kadai_01,
        listOf(
            Data(
                message = "Androidの開発言語は？",
                answers = listOf("Java", "PHP", "Ruby", "Go", "Swift"),
                answerIndex = 0
            ),
            Data(
                message = "iOSの開発言語は？",
                answers = listOf("PHP", "Swift", "Ruby"),
                answerIndex = 1
            ),
            Data(
                message = "デザインツールは？",
                answers = listOf("word", "Xcode", "Figma", "Excel"),
                answerIndex = 2
            ),
        )
    ),
    KADAI02(
        titleResId = R.string.kadai_02,
        data = listOf(
            Data(
                message = "2 Androidの開発言語は？",
                answers = listOf(
                    "Java",
                    "PHP",
                    "Ruby",
                    "Go",
                    "Swift",
                    "PHP",
                    "Ruby",
                    "Go",
                    "Swift"
                ),
                answerIndex = 0
            ),
            Data(
                message = "2 iOSの開発言語は？",
                answers = listOf("PHP", "Swift", "Ruby", "Swift", "Ruby"),
                answerIndex = 1
            ),
            Data(
                message = "2 デザインツールは？",
                answers = listOf("word", "Xcode", "Figma", "Excel", "Xcode", "Figma", "Excel"),
                answerIndex = 2
            ),
        )
    ),
}