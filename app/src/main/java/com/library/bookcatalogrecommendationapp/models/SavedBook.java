package com.library.bookcatalogrecommendationapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

// SavedBook.java
@Entity(tableName = "saved_books")
public class SavedBook {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String authors;
    public String publishedDate;
    public String genre;
    public String coverImageUrl;
    public boolean isRead;

    // Constructor, getters, setters

    public SavedBook(int id, String title, String authors, String publishedDate, String genre, String coverImageUrl, boolean isRead) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.genre = genre;
        this.coverImageUrl = coverImageUrl;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public boolean isIs_read() {
        return isRead;
    }

    public void setIs_read(boolean is_read) {
        this.isRead = is_read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavedBook)) return false;
        SavedBook savedBook = (SavedBook) o;
        return getId() == savedBook.getId() && isIs_read() == savedBook.isIs_read() && Objects.equals(getTitle(), savedBook.getTitle()) && Objects.equals(getAuthors(), savedBook.getAuthors()) && Objects.equals(getPublishedDate(), savedBook.getPublishedDate()) && Objects.equals(getGenre(), savedBook.getGenre()) && Objects.equals(getCoverImageUrl(), savedBook.getCoverImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthors(), getPublishedDate(), getGenre(), getCoverImageUrl(), isIs_read());
    }
}