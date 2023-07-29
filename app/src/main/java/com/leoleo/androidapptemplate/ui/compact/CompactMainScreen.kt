package com.leoleo.androidapptemplate.ui.compact

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice
import com.leoleo.androidapptemplate.ui.quiz.MainContent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompactMainScreen(
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var isFinishedQuiz by rememberSaveable { mutableStateOf(false) }
    var collectAnswerCount by rememberSaveable { mutableStateOf(0) }
    var selectedQuestionIndex by rememberSaveable { mutableStateOf(0) }
    val selectedQuestion =
        Question.values()
            .first { question -> question.ordinal == selectedQuestionIndex }
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("error-> ${throwable.message}")
    }

    CompactMainScreenStateless(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        pagerState = pagerState,
        isFinishedQuiz = isFinishedQuiz,
        collectAnswerCount = collectAnswerCount,
        selectedQuestion = selectedQuestion,
        onClickBottomItem = { index ->
            selectedQuestionIndex = index
            collectAnswerCount = 0
            isFinishedQuiz = false
            scope.launch(coroutineExceptionHandler) {
                pagerState.scrollToPage(page = 0)
            }
        },
        onClickResetButton = {
            collectAnswerCount = 0
            isFinishedQuiz = false
            scope.launch(coroutineExceptionHandler) {
                pagerState.scrollToPage(page = 0)
            }
        },
        onClickAnswerButton = { index ->
            isFinishedQuiz = selectedQuestion.data.size <= pagerState.currentPage + 1
            val msg =
                if (selectedQuestion.data[pagerState.currentPage].answerIndex == index) {
                    collectAnswerCount++
                    "正解です！"
                } else {
                    "不正解です！"
                }
            snackbarHostState.currentSnackbarData?.dismiss()
            scope.launch(coroutineExceptionHandler) {
                pagerState.scrollToPage(page = pagerState.currentPage + 1)
                snackbarHostState.showSnackbar(msg)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CompactMainScreenStateless(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    pagerState: PagerState,
    pagerScrollState: ScrollState = rememberScrollState(),
    bottomBarScrollState: ScrollState = rememberScrollState(),
    isFinishedQuiz: Boolean,
    collectAnswerCount: Int,
    selectedQuestion: Question,
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
                            stringResource(id = selectedQuestion.titleResId),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.horizontalScroll(bottomBarScrollState)
                        ) {
                            Question.values().forEachIndexed { index, question ->
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
                MainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(pagerScrollState),
                    pagerState = pagerState,
                    selectedQuestion = selectedQuestion,
                    isFinishedQuiz = isFinishedQuiz,
                    collectAnswerCount = collectAnswerCount,
                    onClickResetButton = { onClickResetButton() },
                    onClickAnswerButton = { index -> onClickAnswerButton(index) }
                )
            })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_CompactMainScreen_Initial() {
    CompactMainScreenStateless(
        snackbarHostState = remember { SnackbarHostState() },
        pagerState = rememberPagerState(),
        isFinishedQuiz = false,
        collectAnswerCount = 0,
        selectedQuestion = Question.KADAI01,
        onClickBottomItem = {},
        onClickResetButton = { },
        onClickAnswerButton = {},
    )
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_CompactMainScreen_Finished() {
    CompactMainScreenStateless(
        snackbarHostState = remember { SnackbarHostState() },
        pagerState = rememberPagerState(initialPage = Question.KADAI01.data.size - 1),
        isFinishedQuiz = true,
        collectAnswerCount = Question.KADAI01.data.size,
        selectedQuestion = Question.KADAI01,
        onClickBottomItem = {},
        onClickResetButton = { },
        onClickAnswerButton = {},
    )
}