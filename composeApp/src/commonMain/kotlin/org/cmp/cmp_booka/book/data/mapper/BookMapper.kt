package org.cmp.cmp_booka.book.data.mapper

import org.cmp.cmp_booka.book.data.dto.SearchedBookDto
import org.cmp.cmp_booka.book.domain.Book

fun SearchedBookDto.toBook() : Book{
    return Book(
        id = id,
        title = title,
        imgUrl =  if(coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions= numEditions ?: 0
    )
}