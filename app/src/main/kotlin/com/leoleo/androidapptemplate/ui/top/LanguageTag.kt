package com.leoleo.androidapptemplate.ui.top

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.core.os.LocaleListCompat
import com.leoleo.androidapptemplate.R
import java.util.Locale

@Immutable
enum class LanguageTag(
    @StringRes val displayNameResId: Int,
    val localeList: LocaleListCompat,
) {
    SYSTEM(R.string.locale_system_display_name, LocaleListCompat.getEmptyLocaleList()),
    JA(R.string.locale_ja_display_name, LocaleListCompat.forLanguageTags(Locale.forLanguageTag("ja").language)), // 日本
    KO(R.string.locale_ko_display_name, LocaleListCompat.forLanguageTags(Locale.forLanguageTag("ko").language)), // 韓国
    ZH(R.string.locale_zh_display_name, LocaleListCompat.forLanguageTags(Locale.forLanguageTag("zh").language)), // 中国
}