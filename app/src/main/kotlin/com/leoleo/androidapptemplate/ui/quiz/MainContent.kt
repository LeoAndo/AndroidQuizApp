package com.leoleo.androidapptemplate.ui.quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.component.ErrorContent
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MainContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    selectedQuestion: Quiz,
    isFinishedQuiz: Boolean,
    collectAnswerCount: Int,
    onClickResetButton: () -> Unit,
    onClickAnswerButton: (Int) -> Unit,
    viewModel: MainContentViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            modifier = Modifier.size(width = Dp.Infinity, height = 400.dp),
            state = pagerState,
            pageCount = selectedQuestion.data.size,
            userScrollEnabled = false,
        ) { pageIndex ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .wrapContentSize()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                val message = stringResource(id = selectedQuestion.data[pageIndex].messageResId)
                Text(text = message, overflow = TextOverflow.Ellipsis)
            }
        }

        viewModel.uiState.errorMessage?.let {
            ErrorContent(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp), errorMessage = it
            )
        }

        AnimatedVisibility(isFinishedQuiz) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (collectAnswerCount == selectedQuestion.data.size) {
                    Text(stringResource(id = R.string.completed_msg), style = MaterialTheme.typography.titleLarge)
                    viewModel.addCompleteData(stringResource(id = selectedQuestion.titleResId))
                }
                Text(stringResource(id = R.string.format_finished_msg, formatArgs = arrayOf(collectAnswerCount)))
                Button(onClick = { onClickResetButton() }) { Text(stringResource(id = R.string.retry)) }
            }
        }
        selectedQuestion.data[pagerState.currentPage].answers.forEachIndexed { index, answerText ->
            Button(
                onClick = { onClickAnswerButton(index) },
                modifier = Modifier.fillMaxWidth(), enabled = !isFinishedQuiz,
            ) {
                Text(
                    text = answerText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_MainContent_Initial() {
    AppSurface {
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            pagerState = rememberPagerState(),
            selectedQuestion = Quiz.Q01,
            isFinishedQuiz = false,
            collectAnswerCount = 0,
            onClickResetButton = { },
            onClickAnswerButton = { _ -> }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewPhoneDevice
@Composable
private fun Prev_MainContent_Finished() {
    AppSurface {
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            pagerState = rememberPagerState(initialPage = Quiz.Q01.data.size - 1),
            selectedQuestion = Quiz.Q01,
            isFinishedQuiz = true,
            collectAnswerCount = Quiz.Q01.data.size,
            onClickResetButton = { },
            onClickAnswerButton = { _ -> }
        )
    }
}