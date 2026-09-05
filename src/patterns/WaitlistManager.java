package patterns;

import patterns.BookObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WaitlistManager {
    private final Map<String, List<BookObserver>> waitlist = new ConcurrentHashMap<>();

    public void subscribe(String isbn, BookObserver patron) {
        waitlist.computeIfAbsent(isbn, k -> new ArrayList<>()).add(patron);
    }

    public void unsubscribe(String isbn, BookObserver patron) {
        waitlist.getOrDefault(isbn, new ArrayList<>()).remove(patron);
    }

    public void notifyObservers(Book book) {
        List<BookObserver> observers = waitlist.get(book.getIsbn());
        if (observers != null && !observers.isEmpty()) {
            for (BookObserver observer : observers) {
                observer.onBookAvailable(book);
            }
            // Clear list after notifying (optional business logic)
            waitlist.remove(book.getIsbn());
        }
    }
}