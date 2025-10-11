package org.cmp.cmp_booka.book.presentation.book_detail

import org.cmp.cmp_booka.book.domain.Book

sealed interface BookDetailAction {
    data object onBackClick : BookDetailAction
    data object onFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book : Book) : BookDetailAction
}