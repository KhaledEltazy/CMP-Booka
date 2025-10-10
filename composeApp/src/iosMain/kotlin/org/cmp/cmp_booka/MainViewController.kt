package org.cmp.cmp_booka

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(
    engine = remember {
        Darwin.create()
    }
) }