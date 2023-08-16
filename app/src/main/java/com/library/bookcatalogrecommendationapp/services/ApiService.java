package com.library.bookcatalogrecommendationapp.services;

import com.library.bookcatalogrecommendationapp.models.Book;
import com.library.bookcatalogrecommendationapp.utils.BookResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("OL45804W.json")
    Call<Book> getBookDetails();

    @GET("new")
    Call<BookResponse> getBookResponse();

//    Call<List<Book>> getBooks();

    @GET("/")
    Call<List<Book>> searchBooks(String term);

//    @GET("books")
//    Call<List<Book>> getBook();
}
