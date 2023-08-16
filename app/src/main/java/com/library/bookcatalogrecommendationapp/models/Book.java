package com.library.bookcatalogrecommendationapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class Book implements Parcelable {
    public String title;
    public String authors;
    public String publishedDate;
    public String genre;
    public String coverImageUrl;

    public Book(String title, String authors, String publishedDate, String genre, String coverImageUrl) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.genre = genre;
        this.coverImageUrl = coverImageUrl;
    }
    protected Book(Parcel in) {
        title = in.readString();
        authors = in.readString();
        publishedDate = in.readString();
        genre = in.readString();
        coverImageUrl = in.readString();
    }

    public Book() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthors(), book.getAuthors()) && Objects.equals(getPublishedDate(), book.getPublishedDate()) && Objects.equals(getGenre(), book.getGenre()) && Objects.equals(getCoverImageUrl(), book.getCoverImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthors(), getPublishedDate(), getGenre(), getCoverImageUrl());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", genre='" + genre + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                '}';
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(authors);
        dest.writeString(publishedDate);
        dest.writeString(genre);
        dest.writeString(coverImageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}