package services;

import models.Book;
import patterns.BookSearchStrategy;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void removeBook(String isbn);
    void updateBook(Book book);
    Book getBookByIsbn(String isbn);
    List<Book> searchBooks(String query, BookSearchStrategy strategy);
}
