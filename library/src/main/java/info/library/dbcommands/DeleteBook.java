package info.library.dbcommands;

import info.library.service.BookService;

import java.util.Scanner;

public class DeleteBook {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID книги,которую хотите удалить: ");
        long bookId = scanner.nextLong();
        BookService bookService = new BookService();

        if (new CompareWithDB().compareID(bookId)) {
            bookService.deleteBookById(bookId);
            System.out.println("Книга успешно удалена");
        } else {
            System.out.println("Книги с таким ID не существует!!!");
            System.out.println("Посмотрите список доступных книг");
        }
    }
}
