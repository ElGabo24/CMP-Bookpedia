package com.gapps.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gapps.bookpedia.app.App
import com.gapps.bookpedia.book.di.initKoin

fun main(){
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ) {
            App()
        }
    }
}