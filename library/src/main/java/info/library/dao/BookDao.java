package info.library.dao;

import info.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    List<Book> getAllBooks() throws SQLException;

    void addBookToDatabase(Book bookToAdd) throws SQLException;

    Book getBookById(Long id) throws SQLException;

    void deleteBookByID(Long id) throws SQLException;

    void updateBook(Book bookToUpdate) throws SQLException;
}
