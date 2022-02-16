package com.android.booksny.data

import com.android.booksny.data.response.BooksBodyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {

    @GET("lists.json")
    suspend fun getBooks(
        @Query("api-key") apiKey: String = "LEhvk5lF6u22hAVw18eUPUeS4Q8BXUcF",
        @Query("list") list: String = "hardcover-fiction",
    ) : BooksBodyResponse
}