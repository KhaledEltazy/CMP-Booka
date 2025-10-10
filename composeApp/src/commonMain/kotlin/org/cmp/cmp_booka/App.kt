package org.cmp.cmp_booka

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import io.ktor.client.engine.HttpClientEngine
import org.cmp.cmp_booka.book.data.network.KtorRemoteBookDataSource
import org.cmp.cmp_booka.book.data.repository.DefaultBookRepository
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.cmp.cmp_booka.book.presentation.book_list.BookListScreenRoot
import org.cmp.cmp_booka.book.presentation.book_list.BookListViewModel
import org.cmp.cmp_booka.core.data.HttpClientFactory

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory.create(
                            engine = engine
                        )
                    )
                )
            )},
            onBookClick = {

            }
        )
    }
}