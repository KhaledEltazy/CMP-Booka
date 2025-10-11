package org.cmp.cmp_booka.di

import org.cmp.cmp_booka.book.data.network.KtorRemoteBookDataSource
import org.cmp.cmp_booka.book.data.network.RemoteBookDataSource
import org.cmp.cmp_booka.book.data.repository.DefaultBookRepository
import org.cmp.cmp_booka.book.domain.BookRepository
import org.cmp.cmp_booka.book.presentation.SelectedBookViewModel
import org.cmp.cmp_booka.book.presentation.book_list.BookListViewModel
import org.cmp.cmp_booka.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module{
    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single { BookListViewModel(get()) }
    single { SelectedBookViewModel() }
}