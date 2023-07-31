package com.leoleo.androidapptemplate.ui.top

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@Composable
internal fun TopScreen(
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit,
    navigateToCompletedTasksScreen: () -> Unit
) {
    var visible by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit, block = {
        visible = true
    })
    TopScreenStateless(
        modifier = modifier,
        navigateToMainScreen = { navigateToMainScreen() },
        navigateToCompletedTasksScreen = { navigateToCompletedTasksScreen() },
        visible = visible,
    )
}

@Composable
private fun TopScreenStateless(
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit,
    navigateToCompletedTasksScreen: () -> Unit,
    visible: Boolean,
) {
    AppSurface(modifier) {
        AnimatedVisibility(visible, enter = fadeIn() + expandIn(expandFrom = Alignment.TopStart)) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { navigateToMainScreen() }) {
                    Text(text = stringResource(id = R.string.go_to_main_screen))
                }
                Button(onClick = { navigateToCompletedTasksScreen() }) {
                    Text(text = stringResource(id = R.string.go_to_completed_tasks_screen))
                }
            }
        }
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_Init() {
    TopScreenStateless(
        navigateToMainScreen = { },
        navigateToCompletedTasksScreen = { },
        visible = true,
    )
}