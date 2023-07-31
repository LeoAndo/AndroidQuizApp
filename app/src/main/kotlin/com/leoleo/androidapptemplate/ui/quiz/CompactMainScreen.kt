package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.component.ErrorContent
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CompactMainScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModel.changeUiState(throwable.message ?: "error.")
    }

    val title = stringResource(id = viewModel.uiState.selectedQuestion.titleResId)

    CompactMainScreenStateless(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        pagerState = pagerState,
        uiState = viewModel.uiState,
        onClickBottomItem = { index ->
            scope.launch(coroutineExceptionHandler) {
                viewModel.changeUiState(index)
                pagerState.scrollToPage(page = 0)
            }
        },
        onClickResetButton = {
            scope.launch(coroutineExceptionHandler) {
                viewModel.resetUiState()
                pagerState.scrollToPage(page = 0)
            }
        },
        onClickAnswerButton = { index ->
            scope.launch(coroutineExceptionHandler) {
                viewModel.changeUiState(pagerState.currentPage, index, title)
                val msg =
                    if (viewModel.uiState.selectedQuestion.data[pagerState.currentPage].answerIndex == index) {
                        "Is the correct answer!"
                    } else {
                        "Incorrect!"
                    }
                pagerState.scrollToPage(page = pagerState.currentPage + 1)
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar(msg)
            }
        },
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun CompactMainScreenStateless(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    pagerState: PagerState,
    pagerScrollState: ScrollState = rememberScrollState(),
    bottomBarScrollState: ScrollState = rememberScrollState(),
    uiState: QuizUiState,
    onClickBottomItem: (Int) -> Unit,
    onClickResetButton: () -> Unit,
    onClickAnswerButton: (Int) -> Unit,
) {
    AppSurface(modifier) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            stringResource(id = uiState.selectedQuestion.titleResId),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.horizontalScroll(bottomBarScrollState)
                        ) {
                            Quiz.values().forEachIndexed { index, question ->
                                Button(onClick = {
                                    onClickBottomItem(index)
                                }) {
                                    Text(text = stringResource(id = question.titleResId))
                                }
                            }
                        }
                    }
                )
            }, content = {
                val innerPadding =
                    PaddingValues(
                        start = it.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
                        end = it.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
                        top = it.calculateTopPadding() + 8.dp,
                        bottom = it.calculateBottomPadding() + 8.dp,
                    )

                if (uiState.errorMessage.isNullOrBlank()) {
                    MainContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(pagerScrollState),
                        pagerState = pagerState,
                        selectedQuestion = uiState.selectedQuestion,
                        isFinishedQuiz = uiState.isFinishedQuiz,
                        collectAnswerCount = uiState.collectAnswerCount,
                        onClickResetButton = { onClickResetButton() },
                        onClickAnswerButton = { index -> onClickAnswerButton(index) }
                    )
                } else {
                    ErrorContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                            .padding(innerPadding),
                        errorMessage = uiState.errorMessage,
                    )
                }
            })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_CompactMainScreen_Initial() {
    AppSurface {
        CompactMainScreenStateless(
            snackbarHostState = remember { SnackbarHostState() },
            pagerState = rememberPagerState(),
            uiState = QuizUiState(),
            onClickBottomItem = {},
            onClickResetButton = { },
            onClickAnswerButton = {},
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_CompactMainScreen_Finished() {
    CompactMainScreenStateless(
        snackbarHostState = remember { SnackbarHostState() },
        pagerState = rememberPagerState(initialPage = Quiz.Q01.data.size - 1),
        uiState = QuizUiState(
            isFinishedQuiz = true,
            collectAnswerCount = Quiz.Q01.data.size,
            selectedQuestion = Quiz.Q01,
        ),
        onClickBottomItem = {},
        onClickResetButton = { },
        onClickAnswerButton = {},
    )
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_CompactMainScreen_Error() {
    AppSurface {
        CompactMainScreenStateless(
            snackbarHostState = remember { SnackbarHostState() },
            pagerState = rememberPagerState(),
            uiState = QuizUiState(errorMessage = "error!!!!!!"),
            onClickBottomItem = {},
            onClickResetButton = { },
            onClickAnswerButton = {},
        )
    }
}