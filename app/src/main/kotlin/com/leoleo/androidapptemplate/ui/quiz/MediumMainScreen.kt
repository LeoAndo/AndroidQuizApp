package com.leoleo.androidapptemplate.ui.quiz

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.component.ErrorContent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MediumMainScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val pagerState = rememberPagerState()
    val pagerScrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModel.changeUiState(throwable.message ?: "error.")
    }
    val context = LocalContext.current

    AppSurface(modifier) {
        Row {
            NavigationRail {
                Quiz.values().forEachIndexed { index, item ->
                    NavigationRailItem(
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = stringResource(id = item.titleResId)
                            )
                        },
                        label = { Text(stringResource(id = item.titleResId)) },
                        selected = viewModel.uiState.selectedQuestion == item,
                        onClick = {
                            viewModel.changeUiState(index)
                            scope.launch { pagerState.scrollToPage(page = 0) }
                        },
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            if (viewModel.uiState.errorMessage.isNullOrBlank()) {
                MainContent(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(pagerScrollState),
                    pagerState = pagerState,
                    selectedQuestion = viewModel.uiState.selectedQuestion,
                    isFinishedQuiz = viewModel.uiState.isFinishedQuiz,
                    collectAnswerCount = viewModel.uiState.collectAnswerCount,
                    onClickResetButton = {
                        scope.launch(coroutineExceptionHandler) {
                            viewModel.resetUiState()
                            pagerState.scrollToPage(page = 0)
                        }
                    },
                    onClickAnswerButton = { index ->
                        scope.launch(coroutineExceptionHandler) {
                            viewModel.changeUiState(pagerState.currentPage, index)
                            val msg =
                                if (viewModel.uiState.selectedQuestion.data[pagerState.currentPage].answerIndex == index) {
                                    "Is the correct answer!"
                                } else {
                                    "Incorrect!"
                                }
                            pagerState.scrollToPage(page = pagerState.currentPage + 1)
                            // TODO: できれば Android ViewのToastを辞めたい
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                ErrorContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                        .padding(8.dp),
                    errorMessage = viewModel.uiState.errorMessage ?: "error.",
                )
            }
        }
    }
}