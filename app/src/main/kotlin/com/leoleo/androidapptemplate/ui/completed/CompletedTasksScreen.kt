package com.leoleo.androidapptemplate.ui.completed

import androidx.compose.foundation.background
import com.leoleo.androidapptemplate.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.component.ErrorContent
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
internal fun CompletedTasksScreen(
    modifier: Modifier = Modifier,
    viewModel: CompletedTasksViewModel = hiltViewModel(),
) {
    CompletedTasksScreenStateless(modifier = modifier, uiState = viewModel.uiState)
}

@Composable
private fun CompletedTasksScreenStateless(
    modifier: Modifier = Modifier,
    uiState: CompletedQuizUiState
) {
    AppSurface(modifier) {
        when (uiState) {
            Initial -> CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
            )

            Empty -> Text(
                text = stringResource(id = R.string.empty_data),
                modifier = Modifier
                    .wrapContentSize()
            )

            is Error -> {
                ErrorContent(
                    modifier = Modifier
                        .wrapContentSize(),
                    errorMessage = uiState.errorMessage,
                )
            }

            is Data -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(items = uiState.dataList) { data ->
                        ListCell(
                            title = data.title,
                            completedTimeLabel = SimpleDateFormat(
                                "yyyy/MM/dd HH:mm:ss",
                                Locale.ENGLISH
                            ).format(
                                Date(
                                    data.completedTime
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ListCell(
    modifier: Modifier = Modifier,
    title: String,
    completedTimeLabel: String
) {
    Card(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier.padding(4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = completedTimeLabel,
            modifier = Modifier.padding(4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_ListCell() {
    val dataList = buildList {
        repeat(50) {
            if (it % 2 == 0) {
                add(Pair("dasfdfdsafdsfdsfdsaffregersg quiz_$it", "2023/07/30 22:59:23"))
            } else {
                add(Pair("quiz_$it", "2023/07/30 22:59:23"))
            }
        }
    }
    AppSurface {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.background(Color.Cyan),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(items = dataList) { data ->
                ListCell(
                    title = data.first,
                    completedTimeLabel = data.second
                )
            }
        }
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_Initial() {
    CompletedTasksScreenStateless(uiState = Initial)
}

@PreviewPhoneDevice
@Composable
private fun Prev_Empty() {
    CompletedTasksScreenStateless(uiState = Empty)
}

@PreviewPhoneDevice
@Composable
private fun Prev_Error() {
    CompletedTasksScreenStateless(uiState = Error(errorMessage = "error........."))
}