package com.leoleo.androidapptemplate.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Phone Landscape",
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
internal annotation class PreviewPhoneDevice

@Preview(
    showBackground = true,
    device = Devices.TABLET,
    name = "Tablet"
)
internal annotation class PreviewTabletDevice

@Preview(
    showBackground = true,
    device = Devices.FOLDABLE,
    name = "Foldable"
)
internal annotation class PreviewFoldableDevice

@Preview(
    showBackground = true,
    device = Devices.DESKTOP,
    name = "Desktop"
)
internal annotation class PreviewDesktopDevice

@PreviewPhoneDevice
@PreviewTabletDevice
@PreviewFoldableDevice
@PreviewDesktopDevice
internal annotation class PreviewDevices