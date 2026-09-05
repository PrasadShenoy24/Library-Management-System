package patterns;

import models.Book;

public class TitleSearchStrategy implements BookSearchStrategy {
    @Override
    public boolean matches(Book book, String query) {
        return book.getTitle().toLowerCase().contains(query.toLowerCase());
    }
}
