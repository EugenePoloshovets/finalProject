package info.library.dbcommands;

import info.library.model.Author;
import info.library.service.AuthorService;

import java.util.List;

public class ShowAllAuthors {

    public void start() {
        AuthorService authorService = new AuthorService();
        List<Author> authorList = authorService.fetchAllAuthors();

        System.out.println("Количество авторов в библиотеке: " + authorList.size());
        authorList.forEach(System.out::println);
    }
}
