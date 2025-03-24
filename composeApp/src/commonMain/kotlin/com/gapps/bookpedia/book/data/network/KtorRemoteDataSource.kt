package com.gapps.bookpedia.book.data.network

import com.gapps.bookpedia.book.data.dto.BookWorkDto
import com.gapps.bookpedia.book.data.dto.SearchResponseDto
import com.gapps.bookpedia.core.data.safeCall
import com.gapps.bookpedia.core.domain.DataError
import com.gapps.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteDataSource(
    private val client: HttpClient,
): RemoteBookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?,
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            client.get(
                urlString = "$BASE_URL/search.json?"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key," + "title," + "language," + "cover_i," + "author_key," +
                            "author_name," + "cover_edition_key," + "first_publish_year," +
                            "ratings_average," + "ratings_count," + "numbers_of_pages_median," +
                            "edition_count"
                )
            }
        }
    }

    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {
        return safeCall<BookWorkDto> {
            client.get(
                urlString = "$BASE_URL/works/$bookWorkId.json"
            )
        }
    }

}