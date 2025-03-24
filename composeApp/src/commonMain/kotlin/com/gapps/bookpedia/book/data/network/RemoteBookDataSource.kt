package com.gapps.bookpedia.book.data.network

import com.gapps.bookpedia.book.data.dto.BookWorkDto
import com.gapps.bookpedia.book.data.dto.SearchResponseDto
import com.gapps.bookpedia.core.domain.DataError
import com.gapps.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}