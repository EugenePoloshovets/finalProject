package info.library.dbcommands;

import info.library.model.Book;
import info.library.service.BookService;

import java.util.List;

public class ShowAllBooks {

    public void start() {
        BookService bookService = new BookService();
        List<Book> booksList = bookService.fetchAllBooks();

        System.out.println("Количество книг в библиотеке: " + booksList.size());
        booksList.forEach(System.out::println);
    }
}
