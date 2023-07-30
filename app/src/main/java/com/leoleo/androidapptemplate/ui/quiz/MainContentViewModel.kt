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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
internal class MainContentViewModel @Inject constructor(
    private val repository: QuizRepository,
) : ViewModel() {
    var uiState by mutableStateOf(MainContentUiState())
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

    fun addCompleteData(title: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.addCompleteData(title, Date().time)
        }
    }

    companion object {
        private const val TAG = "MainContentViewModel"
    }
}