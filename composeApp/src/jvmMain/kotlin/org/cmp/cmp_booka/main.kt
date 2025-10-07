package org.cmp.cmp_booka

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP-Booka",
    ) {
        App()
    }
}