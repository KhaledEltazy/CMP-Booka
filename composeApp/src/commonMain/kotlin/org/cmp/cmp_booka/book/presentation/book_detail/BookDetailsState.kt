package org.cmp.cmp_booka.book.presentation.book_detail

import org.cmp.cmp_booka.book.domain.Book

data class BookDetailsState (
    val isLoading : Boolean = true,
    val isFavorite : Boolean = false,
    val book : Book? = null
)