package com.gapps.bookpedia.book.presentation.book_list

import com.gapps.bookpedia.book.domain.Book
import com.gapps.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    val selectedTabIndex: Int = 0
)