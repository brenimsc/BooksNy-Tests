package com.android.booksny.data.di

import com.android.booksny.data.BooksService
import com.android.booksny.data.repository.BooksRepository
import com.android.booksny.data.repository.BooksRepositoryInterface
import com.android.booksny.presentation.books.BooksViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"

val moduleNetwork = module {
    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    single {
        createService<BooksService>(get())
    }

}

val moduleRepo = module {
    single<BooksRepositoryInterface> { BooksRepository(get()) }
}

val moduleViewModel = module {
    viewModel { BooksViewModel(get()) }
}

private inline fun <reified T> createService(
    factory: Moshi
): T {
    return Retrofit.Builder()
        //.client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create(T::class.java)
}