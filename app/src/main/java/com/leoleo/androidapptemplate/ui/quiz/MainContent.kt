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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.ui.compact.Question
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    selectedQuestion: Question,
    isFinishedQuiz: Boolean,
    collectAnswerCount: Int,
    onClickResetButton: () -> Unit,
    onClickAnswerButton: (Int) -> Unit,
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
                val message = selectedQuestion.data[pageIndex].message
                Text(text = message, overflow = TextOverflow.Ellipsis)
            }
        }

        AnimatedVisibility(isFinishedQuiz) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (collectAnswerCount == selectedQuestion.data.size) {
                    Text("全問正解です！", style = MaterialTheme.typography.titleLarge)
                }
                Text("${collectAnswerCount}問正解しました。")
                Button(onClick = {
                    onClickResetButton()
                }) {
                    Text("やり直す")
                }
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
fun Prev_MainContent() {
    AppSurface {
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            pagerState = rememberPagerState(),
            selectedQuestion = Question.KADAI01,
            isFinishedQuiz = false,
            collectAnswerCount = 0,
            onClickResetButton = { },
            onClickAnswerButton = { index -> }
        )
    }
}