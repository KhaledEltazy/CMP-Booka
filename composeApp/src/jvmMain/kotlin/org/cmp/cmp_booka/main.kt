package org.cmp.cmp_booka

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.cmp.cmp_booka.app.App
import org.cmp.cmp_booka.di.initKoin


fun main() {
    initKoin()
    application {

        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Booka",
        ) { App() }
    }
}