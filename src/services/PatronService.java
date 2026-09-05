package services;

import models.LendingRecord;
import models.Patron;

import java.util.List;

public interface PatronService {
    void addPatron(Patron patron);
    void updatePatron(Patron patron);
    Patron getPatron(String patronId);
    List<LendingRecord> getPatronHistory(String patronId);
}