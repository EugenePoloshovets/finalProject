package info.library.util;

import info.library.dbcommands.*;

import java.util.Scanner;

public class Application {

    public void startProgram() {
        boolean chooose = true;
        while (chooose) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*****MENU*****");
            System.out.println("1. Просмотреть список книг");
            System.out.println("2. Добавить книгу");
            System.out.println("3. Удалить книгу");
            System.out.println("4. Редактировать книгу");
            System.out.println("5. Посмотреть книгу");
            System.out.println("6. Посмотреть список авторов");
            System.out.println("7. Посмотреть список жанров");
            System.out.println("0. Выйти");

            switch (scanner.nextInt()) {
                case 1: {
                    new ShowAllBooks().start();
                }
                break;
                case 2: {
                    new AddBook().start();
                }
                break;
                case 3: {
                    new DeleteBook().start();
                }
                break;
                case 4: {
                    new UpdateBook().start();
                }
                break;
                case 5: {
                    new ShowOneBook().start();
                }
                break;
                case 6: {
                    new ShowAllAuthors().start();
                }
                break;
                case 7: {
                    new ShowAllGenres().start();
                }
                break;
                case 0: {
                    chooose = false;
                    System.out.println("Пока!");
                }
                break;
                default: {
                    System.out.println("Неверный номер");
                }
                break;
            }
            System.out.println();
        }
    }
}

