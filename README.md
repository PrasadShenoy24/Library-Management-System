# Library Management System - Low-Level Design (LLD)

A robust, in-memory Library Management System implemented in Java. This project demonstrates core Object-Oriented Design (OOD) concepts, strictly adheres to **SOLID principles**, and utilizes common **Design Patterns** to build a scalable and maintainable architecture.

## 🚀 Core Features

*   **Book Management:** Add, remove, update, and search for books.
*   **Patron Management:** Register library members and track their borrowing history.
*   **Lending Process:** Securely check out and return books, with automated state management.
*   **Smart Search:** Extensible search functionality by title, author, etc.
*   **Waitlist Notifications:** Automatically notify patrons when a requested book becomes available.

## 📐 Architecture & Design Patterns

*   **SOLID Principles:** High cohesion and low coupling through Dependency Injection, interface segregation, and single-responsibility classes.
*   **Strategy Pattern:** Extracts search algorithms (`TitleSearchStrategy`, `AuthorSearchStrategy`) from the `BookService`, allowing new search methods to be added without modifying existing code (Open/Closed Principle).
*   **Observer Pattern:** The `WaitlistManager` acts as the Subject, and `Patron` acts as the `BookObserver`. Patrons are automatically notified when a book's status changes to `AVAILABLE`.

## 🗺️ Class Diagram

The following diagram illustrates the relationships, dependencies, and implementations of the core entities and services.

```mermaid
classDiagram
    %% Core Entities
    class Book {
        -String isbn
        -String title
        -String author
        -BookStatus status
    }
    class Patron {
        -String patronId
        -String name
        -List~LendingRecord~ borrowingHistory
        +onBookAvailable(Book)
    }
    class LendingRecord {
        -String recordId
        -String isbn
        -String patronId
        -LocalDate checkoutDate
        -LocalDate returnDate
    }
    class BookStatus {
        <<enumeration>>
        AVAILABLE
        BORROWED
        LOST
    }

    %% Interfaces
    class BookService { <<interface>> }
    class PatronService { <<interface>> }
    class LendingService { <<interface>> }
    class BookSearchStrategy { <<interface>> }
    class BookObserver { <<interface>> }

    %% Relationships - Entities
    Book --> BookStatus : has a
    Patron "1" *-- "*" LendingRecord : contains
    Patron ..|> BookObserver : implements

    %% Relationships - Services
    BookServiceImpl ..|> BookService : implements
    PatronServiceImpl ..|> PatronService : implements
    LendingServiceImpl ..|> LendingService : implements

    %% Relationships - Dependencies
    LendingServiceImpl --> BookService : injects
    LendingServiceImpl --> PatronService : injects
    
    %% Relationships - Strategy Pattern
    TitleSearchStrategy ..|> BookSearchStrategy : implements
    AuthorSearchStrategy ..|> BookSearchStrategy : implements
    BookService --> BookSearchStrategy : uses

    %% Relationships - Observer Pattern
    class WaitlistManager {
        -Map~String, List~BookObserver~~ waitlist
        +subscribe(String, BookObserver)
        +notifyObservers(Book)
    }
    WaitlistManager --> BookObserver : notifies