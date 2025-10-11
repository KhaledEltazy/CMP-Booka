package org.cmp.cmp_booka.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.cmp.cmp_booka.book.presentation.SelectedBookViewModel
import org.cmp.cmp_booka.book.presentation.book_detail.BookDetailAction
import org.cmp.cmp_booka.book.presentation.book_detail.BookDetailScreenRoot
import org.cmp.cmp_booka.book.presentation.book_detail.BookDetailViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.cmp.cmp_booka.book.presentation.book_list.BookListScreenRoot
import org.cmp.cmp_booka.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination =Route.BookGraph
        ){
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ){
                composable<Route.BookList> {entry ->
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    LaunchedEffect(true){
                        selectedBookViewModel.onSelectedBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectedBook(book)
                            navController.navigate(
                                Route.BookDetail(book.id)
                            )
                        }
                    )
                }

                composable<Route.BookDetail> { entry ->
                    val selectedBookViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val viewModel = koinViewModel<BookDetailViewModel>()
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let {
                            viewModel.onAction(BookDetailAction.OnSelectedBookChange(selectedBook!!))
                        }
                    }
                    BookDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )

                }
            }
        }

    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}