package models;

import java.time.LocalDate;
import java.util.UUID;

public class LendingRecord {

    private String recordId;
    private String isbn;
    private String patronId;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    public LendingRecord(String isbn, String patronId) {
        this.recordId = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.patronId = patronId;
        this.checkoutDate = LocalDate.now();
    }

    public void markReturned() { this.returnDate = LocalDate.now(); }
    public String getIsbn() { return isbn; }

    public LocalDate getReturnDate() {return returnDate;}

}