package info.library.dbcommands;

import info.library.model.Author;
import info.library.model.Book;
import info.library.model.Genre;
import info.library.model.GenreEnum;

import java.util.Scanner;

public class CreateBook extends Book {

    private Scanner scanner = new Scanner(System.in);
    public static final String GENRES = "1:NOVEL, 2:STORY, 3:ROMANCE, 4:FANTASY, 5:CLASSIC, 6:HISTORICAL, 7:HORROR";

    public Book createInputBook() {
        Book book = new Book();
        System.out.println("Добавляем книгу");
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        book.setTitle(title);
        System.out.print("Введите автора книги: ");
        String authorname = scanner.nextLine();
        Author author = new Author();
        author.setName(authorname);
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setName(getGenreName());
        book.setGenre(genre);
        System.out.print("ISBN книги: ");
        String ISBN = scanner.nextLine();
        book.setISBN(ISBN);
        return book;
    }

    private String getGenreName() {
        int genreNumber;
        System.out.println("Введите номер жанра (" + GENRES + "):");
        genreNumber = Integer.parseInt(scanner.nextLine());
        GenreEnum genreEnum;
        switch (genreNumber) {
            case 1:
                genreEnum = GenreEnum.NOVEL;
                break;
            case 2:
                genreEnum = GenreEnum.STORY;
                break;
            case 3:
                genreEnum = GenreEnum.ROMANCE;
                break;
            case 4:
                genreEnum = GenreEnum.FANTASY;
                break;
            case 5:
                genreEnum = GenreEnum.CLASSIC;
                break;
            case 6:
                genreEnum = GenreEnum.HISTORICAL;
                break;
            case 7:
                genreEnum = GenreEnum.HORROR;
                break;
            default:
                System.out.println("Нет такого жанра");
                genreEnum = GenreEnum.NO_GENRE;
                break;
        }
        return genreEnum.toString();
    }
}
