package org.cmp.cmp_booka.book.presentation.book_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(BookDetailsState())
    val state = _state.asStateFlow()

    fun onAction (action : BookDetailAction){
        when(action){
            is BookDetailAction.OnSelectedBookChange ->{
                _state.update { it.copy(
                    book = action.book
                ) }
            }
            is BookDetailAction.onFavoriteClick -> {

            }
            else -> Unit
        }
    }
}