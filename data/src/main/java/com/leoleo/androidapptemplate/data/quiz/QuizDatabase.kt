package com.leoleo.androidapptemplate.data.quiz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leoleo.androidapptemplate.data.quiz.dao.CompletedQuizDao
import com.leoleo.androidapptemplate.data.quiz.entity.CompletedQuizEntity

@Database(
    entities = [CompletedQuizEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class QuizDatabase : RoomDatabase() {
    abstract fun completedCompletedQuestionDao(): CompletedQuizDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null
        fun getInstance(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context, QuizDatabase::class.java,
                    "quiz.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}