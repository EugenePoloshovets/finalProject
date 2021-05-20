package info.library.dao;

import info.library.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {

    public List<Genre> getAllGenres() throws SQLException;

    public Genre getAuthorById(Long id) throws SQLException;

    public void addGenreToDB(String genreName) throws SQLException;
}
