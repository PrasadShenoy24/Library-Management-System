package models;

import patterns.BookObserver;

import java.util.ArrayList;
import java.util.List;

public class Patron implements BookObserver {
    private String patronId;
    private String name;
    private String email;
    private List<LendingRecord> borrowingHistory;

    public Patron(String patronId, String name, String email) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getPatronId() { return patronId; }
    public void updateInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public List<LendingRecord> getBorrowingHistory() { return borrowingHistory; }
    public void addLendingRecord(LendingRecord record) { this.borrowingHistory.add(record); }

    @Override
    public void onBookAvailable(Book book) {
        System.out.println("Notification for " + this.name + ": The book '"
                + book.getTitle() + "' is now AVAILABLE!");
    }
}