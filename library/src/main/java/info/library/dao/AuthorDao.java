package info.library.dao;

import info.library.model.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {

    List<Author> getAllAuthors() throws SQLException;

    public Author getAuthorById(Long id) throws SQLException;

    public void addAuthorToDB(String authorName) throws SQLException;

}
