package com.leoleo.androidapptemplate.ui.quiz

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoleo.androidapptemplate.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class QuizViewModel @Inject constructor(
    private val repository: QuizRepository,
) : ViewModel() {
    var uiState by mutableStateOf(QuizUiState())
        private set
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        uiState = uiState.copy(errorMessage = throwable.message ?: "error.")
    }

    init {
        Log.d(TAG, "call init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "call onCleared")
    }

    fun getCompletedQuizList() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getCompletedQuizList()
        }
    }

    fun changeUiState(errorMessage: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            uiState = uiState.copy(errorMessage = errorMessage)
        }
    }

    fun changeUiState(index: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val selectedQuestion = Quiz.values().first { question -> question.ordinal == index }
            uiState = uiState.copy(
                selectedQuestion = selectedQuestion,
                collectAnswerCount = 0,
                isFinishedQuiz = false,
                errorMessage = null,
            )
        }
    }

    fun resetUiState() {
        viewModelScope.launch(coroutineExceptionHandler) {
            uiState =
                uiState.copy(collectAnswerCount = 0, isFinishedQuiz = false, errorMessage = null)
        }
    }

    fun changeUiState(currentPage: Int, index: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            uiState = uiState.copy(
                errorMessage = null,
                isFinishedQuiz = uiState.selectedQuestion.data.size <= currentPage + 1,
                collectAnswerCount =
                if (uiState.selectedQuestion.data[currentPage].answerIndex == index)
                    uiState.collectAnswerCount + 1 else uiState.collectAnswerCount
            )
        }
    }

    companion object {
        private const val TAG = "QuizViewModel"
    }
}