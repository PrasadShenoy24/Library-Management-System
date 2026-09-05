package patterns;

import models.Book;

public class AuthorSearchStrategy implements BookSearchStrategy {
    @Override
    public boolean matches(Book book, String query) {
        return book.getAuthor().toLowerCase().contains(query.toLowerCase());
    }
}