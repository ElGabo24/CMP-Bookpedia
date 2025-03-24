package com.gapps.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.gapps.bookpedia.book.data.database.FavoriteBookDao
import com.gapps.bookpedia.book.data.mappers.toBook
import com.gapps.bookpedia.book.data.mappers.toBookEntity
import com.gapps.bookpedia.book.data.network.RemoteBookDataSource
import com.gapps.bookpedia.book.domain.Book
import com.gapps.bookpedia.book.domain.BookRepository
import com.gapps.bookpedia.core.domain.DataError
import com.gapps.bookpedia.core.domain.EmptyResult
import com.gapps.bookpedia.core.domain.Result
import com.gapps.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBook(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(bookId)
        return if (localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao.getFavoriteBooks()
            .map { entities ->
                entities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao.getFavoriteBooks()
            .map { entities ->
                entities.any { it.id == id }
            }

    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (_: SQLiteException){
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }

}