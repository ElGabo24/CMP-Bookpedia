package com.gapps.bookpedia.book.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gapps.bookpedia.book.data.database.DatabaseFactory
import com.gapps.bookpedia.book.data.database.FavoriteBookDatabase
import com.gapps.bookpedia.book.data.network.KtorRemoteDataSource
import com.gapps.bookpedia.book.data.network.RemoteBookDataSource
import com.gapps.bookpedia.book.data.repository.DefaultBookRepository
import com.gapps.bookpedia.book.domain.BookRepository
import com.gapps.bookpedia.book.presentation.SelectedBookViewModel
import com.gapps.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.gapps.bookpedia.book.presentation.book_list.BookListViewModel
import com.gapps.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}