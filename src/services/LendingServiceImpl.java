package services;

import exception.BookNotAvailableException;
import exception.ItemNotFoundException;
import models.BookStatus;
import models.LendingRecord;
import models.Patron;

import models.Book;

public class LendingServiceImpl implements LendingService {
    private final BookService bookService;
    private final PatronService patronService;

    public LendingServiceImpl(BookService bookService, PatronService patronService) {
        this.bookService = bookService;
        this.patronService = patronService;
    }

    @Override
    public void checkoutBook(String isbn, String patronId) {
        Book book = bookService.getBookByIsbn(isbn);
        Patron patron = patronService.getPatron(patronId);

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new BookNotAvailableException("models.Book is currently " + book.getStatus());
        }

        book.setStatus(BookStatus.BORROWED);
        bookService.updateBook(book);

        LendingRecord record = new LendingRecord(isbn, patronId);
        patron.addLendingRecord(record);
    }

    @Override
    public  void returnBook(String isbn, String patronId) {
        Book book = bookService.getBookByIsbn(isbn);
        Patron patron = patronService.getPatron(patronId);

        LendingRecord activeRecord = patron.getBorrowingHistory().stream()
                .filter(r -> r.getIsbn().equals(isbn) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("No active checkout found for this book and patron."));

        book.setStatus(BookStatus.AVAILABLE);
        bookService.updateBook(book);

        activeRecord.markReturned();
    }
}