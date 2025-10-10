package org.cmp.cmp_booka.book.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.cmp.cmp_booka.book.data.dto.SearchResponseDto
import org.cmp.cmp_booka.core.data.safeCall
import org.cmp.cmp_booka.core.domain.DataError
import org.cmp.cmp_booka.core.domain.Result

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) : RemoteBookDataSource {
    override suspend fun searchBook(
        query: String,
        resultLimit: Int?
    ) : Result<SearchResponseDto, DataError.Remote>{
        return safeCall{
            httpClient.get (
                urlString = "$BASE_URL/search.json"
            ){
                parameter("q",query)
                parameter("limit",resultLimit)
                parameter("language","eng")
                parameter("fields","key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }
}