package com.gapps.bookpedia

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gapps.bookpedia.book.domain.Book
import com.gapps.bookpedia.book.presentation.book_list.BookListScreen
import com.gapps.bookpedia.book.presentation.book_list.BookListState
import com.gapps.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview(showBackground = true)
@Composable
private fun BookSearchBarPreview() {
    MaterialTheme {
        BookSearchBar(
            modifier = Modifier
                .padding(8.dp),
            searchQuery = "Kotlin",
            onSearchQueryChanged = {},
            onImeSearch = {},
        )
    }
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        author = listOf("Author $it"),
        imageUrl = "https://picsum.photos/200/300?random=$it",
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.54322,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    MaterialTheme {
        BookListScreen(
            state = BookListState(
                searchResults = books
            ),
            onAction = {}
        )
    }
}