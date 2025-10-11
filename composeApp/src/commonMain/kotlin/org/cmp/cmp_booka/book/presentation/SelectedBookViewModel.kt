package org.cmp.cmp_booka.book.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.cmp.cmp_booka.book.domain.Book

class SelectedBookViewModel : ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectedBook(book : Book?){
        _selectedBook.value = book
    }
}