package info.library.dbcommands;

import info.library.model.Book;
import info.library.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

public class CompareWithDB {

    private BookService bookService = new BookService();
    private List<Book> bookList = bookService.fetchAllBooks();

    public boolean compareID(long bookId) {
        return bookList.stream()
                .map(Book::getId)
                .collect(Collectors.toList())
                .contains(bookId);
    }

    public boolean compareTitle(String bookTitle) {
        return bookList.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList())
                .contains(bookTitle);
    }
}

