package org.cmp.cmp_booka.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.cmp.cmp_booka.book.presentation.SelectedBookViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.cmp.cmp_booka.book.presentation.book_list.BookListScreenRoot
import org.cmp.cmp_booka.book.presentation.book_list.BookListViewModel
import org.koin.mp.KoinPlatform.getKoin

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
                    val viewModel: BookListViewModel = getKoin().get()
                    val selectedBookViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>()

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
                    val selectedBookViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>()
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()


                }
            }
        }

    }
}

@Composable
inline fun <reified T : Any> NavBackStackEntry.sharedKoinViewModel(): T {
    return getKoin().get<T>() as T
}