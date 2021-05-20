package info.library.dbcommands;

import info.library.model.Author;
import info.library.model.Genre;
import info.library.service.AuthorService;
import info.library.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

public class AddAuthorAndGenre {

    public long searchAndGetAuthorId(String authorName) {
        long authorId;
        AuthorService authorService = new AuthorService();
        List<Author> authorsList = authorService.fetchAllAuthors();
        List<String> authorDBNames = authorsList.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        if (authorDBNames.contains(authorName)) {
            authorId = authorsList.stream()
                    .filter(author -> author.getName().equalsIgnoreCase(authorName))
                    .findFirst()
                    .get().getId();
            System.out.println("Автор есть в базе данных");
        } else {
            authorService.addAuthorToDB(authorName);
            List<Author> authorList = authorService.fetchAllAuthors();
            authorId = authorsList.stream()
                    .filter(author -> author.getName().equalsIgnoreCase(authorName))
                    .findFirst()
                    .get().getId();
            System.out.println("Новый автор был добавлен");
        }
        return authorId;
    }

    public long searchAndGetGenreId(String genreName) {
        long genreId;
        GenreService genreService = new GenreService();
        List<Genre> genreList = genreService.fetchAllGenres();
        List<String> genreDBNames = genreList.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        if (genreDBNames.contains(genreName)) {
            genreId = genreList.stream()
                    .filter(genre -> genre.getName().equalsIgnoreCase(genreName))
                    .findFirst()
                    .get()
                    .getId();
            System.out.println("Жанр есть в базе данных");
        } else {
            genreService.addGenreToDB(genreName);
            List<Genre> genreList1 = genreService.fetchAllGenres();
            genreId = genreList1.stream()
                    .filter(genre -> genre.getName().equalsIgnoreCase(genreName))
                    .findFirst()
                    .get()
                    .getId();
            System.out.println("Новый жанр был добавлен");
        }
        return genreId;
    }


}

