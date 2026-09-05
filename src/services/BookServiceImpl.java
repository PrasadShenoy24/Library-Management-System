package services;

import exception.ItemNotFoundException;

import models.Book;
import patterns.BookSearchStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private final Map<String, Book> bookInventory = new ConcurrentHashMap<>();

    @Override
    public void addBook(Book book) {
        bookInventory.put(book.getIsbn(), book);
    }

    @Override
    public void removeBook(String isbn) {
        bookInventory.remove(isbn);
    }

    @Override
    public void updateBook(Book book) {
        if (!bookInventory.containsKey(book.getIsbn())) {
            throw new ItemNotFoundException("models.Book not found to update");
        }
        bookInventory.put(book.getIsbn(), book);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = bookInventory.get(isbn);
        if (book == null) throw new ItemNotFoundException("models.Book not found with ISBN: " + isbn);
        return book;
    }

    @Override
    public List<Book> searchBooks(String query, BookSearchStrategy strategy) {
        return bookInventory.values().stream()
                .filter(book -> strategy.matches(book, query))
                .collect(Collectors.toList());
    }
}
