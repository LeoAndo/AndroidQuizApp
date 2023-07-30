package com.leoleo.androidapptemplate.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@Composable
internal fun ErrorContent(modifier: Modifier = Modifier, errorMessage: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = errorMessage)
        Text(text = "問題が発生しました。", color = MaterialTheme.colorScheme.error)
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_ErrorContent() {
    AppSurface {
        ErrorContent(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp),
            errorMessage = "error!!!!",
        )
    }
}