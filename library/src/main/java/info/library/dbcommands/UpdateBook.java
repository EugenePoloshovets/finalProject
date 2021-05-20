package info.library.dbcommands;

import info.library.model.Book;
import info.library.service.BookService;

import java.util.List;
import java.util.Scanner;

public class UpdateBook {

    private long bookId;

    public void start() {

        BookService bookService = new BookService();
        List<Book> bookList = bookService.fetchAllBooks();
        Book bookForUpdate;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для редактирования введите ID книги: ");
        try {
            bookId = Long.parseLong(scanner.nextLine());
            if (new CompareWithDB().compareID(bookId)) {
                bookForUpdate = new CreateBook().createInputBook();
                bookForUpdate.setId(bookId);
                bookService.updateBook(bookForUpdate);
                System.out.println("Книга обновлена");
            } else {
                System.out.println("В базе данных нет такого ID");
            }
        } catch (NumberFormatException e) {
            System.out.println("Это не число");
        }
    }
}

