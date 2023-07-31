package com.leoleo.androidapptemplate.ui.quiz

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.component.ErrorContent
import com.leoleo.androidapptemplate.ui.preview.PreviewTabletDevice
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ExpandedMainScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModel.changeUiState(throwable.message ?: "error.")
    }
    val context = LocalContext.current
    val title = stringResource(id = viewModel.uiState.selectedQuestion.titleResId)
    BackHandler(enabled = drawerState.isOpen) { scope.launch { drawerState.close() } }

    ExpandedMainScreenStateless(
        modifier = modifier,
        drawerState = drawerState,
        uiState = viewModel.uiState,
        pagerState = pagerState,
        onClickDrawerItem = { index ->
            viewModel.changeUiState(index)
            scope.launch {
                drawerState.close()
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
                // TODO: できれば Android ViewのToastを辞めたい
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExpandedMainScreenStateless(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    uiState: QuizUiState,
    pagerScrollState: ScrollState = rememberScrollState(),
    pagerState: PagerState,
    onClickDrawerItem: (Int) -> Unit,
    onClickResetButton: () -> Unit,
    onClickAnswerButton: (Int) -> Unit,
) {
    AppSurface(modifier) {
        DismissibleNavigationDrawer(drawerState = drawerState, drawerContent = {
            DismissibleDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Quiz.values().forEachIndexed { index, quiz ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(id = quiz.titleResId)) },
                        selected = quiz == uiState.selectedQuestion,
                        onClick = { onClickDrawerItem(index) },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }, content = {
            if (uiState.errorMessage.isNullOrBlank()) {
                MainContent(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(pagerScrollState),
                    pagerState = pagerState,
                    selectedQuestion = uiState.selectedQuestion,
                    isFinishedQuiz = uiState.isFinishedQuiz,
                    collectAnswerCount = uiState.collectAnswerCount,
                    onClickResetButton = { onClickResetButton() },
                    onClickAnswerButton = { index -> onClickAnswerButton(index) })
            } else {
                ErrorContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                        .padding(8.dp),
                    errorMessage = uiState.errorMessage,
                )
            }
        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewTabletDevice
@Composable
fun Prev_ExpandedMainScreen_Initial() {
    ExpandedMainScreenStateless(
        drawerState = rememberDrawerState(DrawerValue.Closed),
        uiState = QuizUiState(),
        pagerState = rememberPagerState(),
        onClickDrawerItem = {},
        onClickResetButton = { },
        onClickAnswerButton = {},
    )
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewTabletDevice
@Composable
fun Prev_ExpandedMainScreen_Error() {
    ExpandedMainScreenStateless(
        drawerState = rememberDrawerState(DrawerValue.Closed),
        uiState = QuizUiState(errorMessage = "error..."),
        pagerState = rememberPagerState(),
        onClickDrawerItem = {},
        onClickResetButton = { },
        onClickAnswerButton = {},
    )
}