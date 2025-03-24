package com.gapps.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.gapps.bookpedia.app.App
import com.gapps.bookpedia.book.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }