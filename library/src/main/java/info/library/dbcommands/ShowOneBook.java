package info.library.dbcommands;

import info.library.model.Book;
import info.library.service.BookService;

import java.util.Scanner;

public class ShowOneBook {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID книги, которую хотите просмотреть: ");
        long a = scanner.nextInt();
        BookService bookService = new BookService();
        try {
            Book bookById = bookService.fetchBookById(a);
            System.out.println(bookById);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Книги с таким ID не существует!!!");
            System.out.println("Посмотрите список доступных книг");
        }
    }
}
