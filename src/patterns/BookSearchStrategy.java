package patterns;

import models.Book;

public interface BookSearchStrategy {
    boolean matches(Book book, String query);
}