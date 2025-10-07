package org.cmp.cmp_booka.book.presentation.book_list

import org.cmp.cmp_booka.book.domain.Book
import org.cmp.cmp_booka.core.presentation.UiText

data class BookListState(
    val searchQuery : String = "Kotlin",
    val searchResults : List<Book> = emptyList(),
    val favoriteBooks : List<Book> = emptyList(),
    val isLoading : Boolean = false,
    val selectedTabIndex : Int = 0,
    val errorMessage : UiText? = null
)