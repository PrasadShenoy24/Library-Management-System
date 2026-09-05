package services;

public interface LendingService {
    void checkoutBook(String isbn, String patronId);
    void returnBook(String isbn, String patronId);
}