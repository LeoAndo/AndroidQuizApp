package com.leoleo.androidapptemplate.ui.completed

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoleo.androidapptemplate.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CompletedTasksViewModel @Inject constructor(
    private val repository: QuizRepository,
) : ViewModel() {
    var uiState by mutableStateOf<CompletedQuizUiState>(Initial)
        private set

    init {
        Log.e(TAG, "call init")
        viewModelScope.launch {
            repository.observeAllCompletedQuiz()
                .catch { throwable ->
                    uiState = Error(errorMessage = throwable.message ?: "error.")
                }
                .collect { dataList ->
                    uiState = if (dataList.isEmpty()) {
                        Empty
                    } else {
                        Data(dataList = dataList)
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "call onCleared")
    }

    companion object {
        private const val TAG = "CompletedQuizViewModel"
    }
}