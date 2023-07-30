package com.leoleo.androidapptemplate.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.quiz.CompactMainScreen
import com.leoleo.androidapptemplate.ui.quiz.ExpandedMainScreen
import com.leoleo.androidapptemplate.ui.quiz.MediumMainScreen
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    windowWidthSizeClass: WindowWidthSizeClass,
    navigateToNextScreen: () -> Unit,
) {
    when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> CompactMainScreen(modifier.testTag(stringResource(id = R.string.test_tag_compact_main_screen))) { navigateToNextScreen() }
        WindowWidthSizeClass.Medium -> MediumMainScreen(modifier.testTag(stringResource(id = R.string.test_tag_medium_main_screen))) { navigateToNextScreen() }
        WindowWidthSizeClass.Expanded -> ExpandedMainScreen(modifier.testTag(stringResource(id = R.string.test_tag_expanded_main_screen))) { navigateToNextScreen() }
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Compact() {
    MainScreen(windowWidthSizeClass = WindowWidthSizeClass.Compact) {}
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Medium() {
    MainScreen(windowWidthSizeClass = WindowWidthSizeClass.Medium) {}
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Expanded() {
    MainScreen(windowWidthSizeClass = WindowWidthSizeClass.Expanded) {}
}