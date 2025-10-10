package org.cmp.cmp_booka.book.data.network

import org.cmp.cmp_booka.book.data.dto.SearchResponseDto
import org.cmp.cmp_booka.core.domain.DataError
import org.cmp.cmp_booka.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBook(
        query: String,
        resultLimit : Int? = null
    ) : Result<SearchResponseDto, DataError.Remote>
}