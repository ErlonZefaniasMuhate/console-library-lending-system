# Console Library Lending System (CLiLS)

A text-based Java application that simulates a small public library's lending operations. Built as a comprehensive practice project covering core Java concepts from JDK setup to exception handling and object-oriented design.

## Features

- **Catalog Management:** View all books with status (Available, Borrowed, Under Repair).
- **Member Registration:** Add new members with auto-generated IDs.
- **Borrowing System:** Members can borrow up to 3 books; business rules enforced.
- **Return Processing:** Return books and update availability.
- **Transaction Logging:** Every borrow/return attempt logged to a file using try-with-resources.
- **Console Interface:** Interactive menu with switch-case navigation.

## Technologies & Concepts Practiced

| Category               | Topics                                                       |
| ---------------------- | ------------------------------------------------------------ |
| **Environment**        | JDK 15, IntelliJ IDEA, manual compilation (`javac`/`java`), PATH configuration |
| **Core Java**          | Primitives, casting, operators, strings, arrays, multidimensional arrays |
| **Control Flow**       | `if`/`else`, `switch`, loops (`while`, `for`, `foreach`), `break`/`continue` |
| **OOP**                | Classes, objects, encapsulation, inheritance, abstract classes, interfaces, polymorphism |
| **Advanced OOP**       | Enums, static members, final modifier, method overloading/overriding, varargs |
| **Exception Handling** | Checked vs unchecked exceptions, custom exceptions, try-catch-finally, multi-catch, try-with-resources |
| **I/O**                | Scanner console input, FileWriter logging                    |
| **Design Patterns**    | Facade pattern (service layer), programming to interface     |

## Project Structure

```
src/
├── com.library.model/
│   ├── Person.java (abstract)
│   ├── Member.java
│   ├── Book.java
│   ├── Borrowable.java (interface)
│   └── BookStatus.java (enum)
├── com.library.exception/
│   ├── BookNotAvailableException.java (checked)
│   └── MaxBooksExceededException.java (unchecked)
├── com.library.service/
│   └── LibraryService.java (facade, logging, catalog management)
└── com.library.main/
    └── Main.java (console UI)
```

## Setup & Compilation

1. Ensure JDK 15+ is installed and `JAVA_HOME` is set.
2. Clone or create the project in IntelliJ IDEA (Community Edition).
3. Compile manually (optional):
   ```
   javac -d bin src/com/library/model/*.java src/com/library/exception/*.java src/com/library/service/*.java src/com/library/main/*.java
   ```
4. Run the application:
   ```
   java -cp bin com.library.main.Main
   ```

## Usage

Upon running, a menu appears:

```
===== Library Menu =====
1. Display Catalog
2. Register Member
3. Display Members
4. Borrow Book
5. Return Book
6. Exit
```

Follow the prompts to interact. All transaction attempts are recorded in `library_log.txt` with timestamps.

## Class Diagram

```mermaid
classDiagram
    direction TB
%% Packages as namespaces (visual grouping via naming convention)
namespace com.library.model {
    class Person {
        <<abstract>>
        # String name
        # String email
        + Person(name, email)
        + getDescription()* String
    }

    class Member {
        - String memberId
        - Book[3] borrowedBooks
        + borrowBook(book: Book) void
        + returnBook(book: Book) void
        + getDescription() String
        + getBorrowedBooks() Book[]
    }

    class Borrowable {
        <<interface>>
        + checkOut(member: Member) void
        + returnItem() void
    }

    class Book {
        - String isbn
        - String title
        - String author
        - BookStatus status
        - Member currentBorrower
        + Book(isbn, title, author)
        + Book(title)
        + checkOut(member: Member) void
        + returnItem() void
        + toString() String
        + getStatus() BookStatus
    }

    class BookStatus {
        <<enumeration>>
        AVAILABLE
        BORROWED
        UNDER_REPAIR
    }
}

namespace com.library.exception {
    class BookNotAvailableException {
        + BookNotAvailableException(message: String)
    }

    class MaxBooksExceededException {
        + MaxBooksExceededException(message: String)
    }
}

namespace com.library.service {
    class LibraryService {
        - List~Book~ catalog$
        - List~Member~ members$
        + initCatalog()$ void
        + attemptBorrow(memberId: int, isbn: String)$ void
        + processCheckout(item: Borrowable, member: Member)$ void
        + logTransaction(message: String)$ void
        + printFormatted(header: String, ... items: String)$ void
        + findBookByIsbn(isbn: String)$ Book
        + findMemberById(id: int)$ Member
    }
}

namespace com.library.main {
    class Main {
        + main(args: String[])$ void
    }
}

%% Inheritance & Implementation
Person <|-- Member
Borrowable <|.. Book
Exception <|-- BookNotAvailableException
RuntimeException <|-- MaxBooksExceededException

%% Associations
Member "1" --> "0..3" Book : borrows
Book "1" --> "0..1" Member : currentBorrower
Book --> BookStatus : status

%% Dependencies
LibraryService ..> Member : uses
LibraryService ..> Book : uses
LibraryService ..> BookNotAvailableException : throws/handles
LibraryService ..> MaxBooksExceededException : throws/handles
LibraryService ..> Borrowable : uses
Main ..> LibraryService : calls

%% Notes
note for Member "MAX_BOOKS_ALLOWED = 3 (static final)\nFixed-size array for practice"
note for LibraryService "Implements try-catch-finally\nand try-with-resources"
```

## Product Backlog Summary

| Epic           | Key Features                                    | Status      |
| -------------- | ----------------------------------------------- | ----------- |
| Foundation     | Project setup, packages, core classes           | Completed   |
| Business Logic | Borrow/return implementation, array management  | Completed   |
| Service Layer  | Transaction facade, logging, exception handling | Completed   |
| UI & Polish    | Console menu, display features, documentation   | In Progress |

Full backlog mapping to course videos (02-105) is available in the project documentation.

## License

This project is created for educational purposes as part of a Java refresher curriculum.

---

*Built to practice all topics from the "Java Funciona" video series (Videos 02–105).*