package info.library.dbcommands;

import info.library.model.Genre;
import info.library.service.GenreService;

import java.util.List;

public class ShowAllGenres {

    public void start() {
        GenreService genreService = new GenreService();
        List<Genre> genreList = genreService.fetchAllGenres();

        System.out.println("Количество жанров в библиотеке: " + genreList.size());
        genreList.forEach(System.out::println);
    }
}
