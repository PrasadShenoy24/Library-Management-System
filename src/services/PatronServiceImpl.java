package services;

import exception.ItemNotFoundException;
import models.LendingRecord;
import models.Patron;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PatronServiceImpl implements PatronService {
    private final Map<String, Patron> patrons = new ConcurrentHashMap<>();

    @Override
    public void addPatron(Patron patron) {
        patrons.put(patron.getPatronId(), patron);
    }

    @Override
    public void updatePatron(Patron patron) {
        if (!patrons.containsKey(patron.getPatronId())) {
            throw new ItemNotFoundException("models.Patron not found");
        }
        patrons.put(patron.getPatronId(), patron);
    }

    @Override
    public Patron getPatron(String patronId) {
        Patron patron = patrons.get(patronId);
        if (patron == null) throw new ItemNotFoundException("models.Patron not found with ID: " + patronId);
        return patron;
    }

    @Override
    public List<LendingRecord> getPatronHistory(String patronId) {
        return getPatron(patronId).getBorrowingHistory();
    }
}