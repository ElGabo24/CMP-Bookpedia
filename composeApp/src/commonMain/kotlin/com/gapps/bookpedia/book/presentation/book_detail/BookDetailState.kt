package com.gapps.bookpedia.book.presentation.book_detail

import com.gapps.bookpedia.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null,
)
