package org.cmp.cmp_booka

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.cmp.cmp_booka.book.domain.Book
import org.cmp.cmp_booka.book.presentation.book_list.BookListScreen
import org.cmp.cmp_booka.book.presentation.book_list.BookListState


//@Preview(showBackground = true)
//@Composable
//private fun BookSearchBarPreview() {
//    MaterialTheme {
//        BookSearchBar(
//            searchQuery = "",
//            onImeSearch = {},
//            onSearchQueryChange = {},
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//}

private val books = (1..10).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imgUrl = "test.com",
        authors = listOf("Khaled Mustafa"),
        description = "Description of $it",
        languages = listOf("En", "Ar"),
        firstPublishYear = "2021",
        averageRating = 4.57,
        ratingCount = 4,
        numPages = 250,
        numEditions = 2
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    MaterialTheme {
        BookListScreen(
            onAction = {},
            state = BookListState(
                searchResults = books
            )
        )
    }
}