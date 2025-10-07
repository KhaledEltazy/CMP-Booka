package org.cmp.cmp_booka.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cmp.cmp_booka.book.domain.Book
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = getKoin().get(),
    onBookClick : (Book) -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = { action ->
            when(action){
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun BookListScreen(
    state : BookListState,
    onAction : (BookListAction) -> Unit
){

}

