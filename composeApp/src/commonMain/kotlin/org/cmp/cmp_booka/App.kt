package org.cmp.cmp_booka

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.cmp.cmp_booka.book.presentation.book_list.BookListScreenRoot
import org.cmp.cmp_booka.book.presentation.book_list.BookListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel()},
            onBookClick = {

            }
        )
    }
}