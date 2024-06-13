package books.controller;

import books.domain.Book;

import java.util.ArrayList;
import java.util.Collection;

public class Books {

    private Collection<Book> books = new ArrayList<>();

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

}
