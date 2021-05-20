package info.library.service;

import info.library.dao.GenreDao;
import info.library.dao.GenreDaoImpl;
import info.library.model.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreService {

    private GenreDao genreDao = new GenreDaoImpl();

    public List<Genre> fetchAllGenres() {
        List<Genre> genresList = new ArrayList<>();
        try {
            List<Genre> allgenres = genreDao.getAllGenres();
            genresList.addAll(genreDao.getAllGenres());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return genresList;
    }

    public Genre fetchGenreById(Long id) {
        try {
            return genreDao.getAuthorById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addGenreToDB(String addGenre){
        try {
            genreDao.addGenreToDB(addGenre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
