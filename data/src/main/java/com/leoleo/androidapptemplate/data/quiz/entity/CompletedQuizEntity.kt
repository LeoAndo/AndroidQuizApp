package com.leoleo.androidapptemplate.data.quiz.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leoleo.androidapptemplate.domain.model.CompletedQuiz

internal const val TABLE_NAME_COMPLETED_QUIZ = "completed_quiz"
internal const val COLUMN_TITLE = "title"
internal const val COLUMN_COMPLETED_TIME = "completed_time"

@Entity(tableName = TABLE_NAME_COMPLETED_QUIZ)
internal data class CompletedQuizEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,
    @ColumnInfo(name = COLUMN_COMPLETED_TIME)
    val completedTime: Long,
)

internal fun List<CompletedQuizEntity>.toModels(): List<CompletedQuiz> {
    return this.mapIndexed { _, entity ->
        CompletedQuiz(
            title = entity.title,
            completedTime = entity.completedTime,
        )
    }
}

internal fun CompletedQuizEntity.toModel(): CompletedQuiz =
    CompletedQuiz(title = this.title, completedTime = this.completedTime)