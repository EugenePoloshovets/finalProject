package info.library.dbcommands;

import info.library.model.Book;
import info.library.service.BookService;

import java.util.List;

public class AddBook {

    public void start() {
        BookService bookService = new BookService();
        List<Book> bookList = bookService.fetchAllBooks();
        Book inputBook = new CreateBook().createInputBook();

        if (new CompareWithDB().compareTitle(inputBook.getTitle())) {
            Book bookFromDatabase = bookList.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(inputBook.getTitle()))
                    .findFirst()
                    .get();
            System.out.println("В базе уже есть такая книга: " + bookFromDatabase);
        } else {
            bookService.addBookToDatabase(inputBook);
            System.out.println("Книга успешно добавлена!");
        }
    }
}
