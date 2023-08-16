package com.library.bookcatalogrecommendationapp.utils;


import com.library.bookcatalogrecommendationapp.models.Book;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {
    @SerializedName("error")
    private String error;

    @SerializedName("total")
    private String total;

    @SerializedName("books")
    private List<Book> books;

    // Getters and setters for the fields

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
