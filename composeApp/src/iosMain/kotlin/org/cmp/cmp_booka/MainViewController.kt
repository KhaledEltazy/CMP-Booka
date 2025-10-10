package org.cmp.cmp_booka

import androidx.compose.ui.window.ComposeUIViewController
import org.cmp.cmp_booka.di.initKoin

fun MainViewController() {
    initKoin()
    ComposeUIViewController {
        App()
    }
}