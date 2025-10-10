package org.cmp.cmp_booka.book.domain

import org.cmp.cmp_booka.core.domain.DataError
import org.cmp.cmp_booka.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query : String) : Result<List<Book>, DataError.Remote>
}