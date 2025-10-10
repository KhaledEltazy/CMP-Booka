package org.cmp.cmp_booka.book.data.repository

import org.cmp.cmp_booka.book.data.mapper.toBook
import org.cmp.cmp_booka.book.data.network.RemoteBookDataSource
import org.cmp.cmp_booka.book.domain.Book
import org.cmp.cmp_booka.book.domain.BookRepository
import org.cmp.cmp_booka.core.domain.DataError
import org.cmp.cmp_booka.core.domain.Result
import org.cmp.cmp_booka.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource : RemoteBookDataSource
) : BookRepository{
    override suspend fun searchBooks(query : String) : Result<List<Book>, DataError.Remote>{
        return remoteBookDataSource
            .searchBook(query = query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }
}