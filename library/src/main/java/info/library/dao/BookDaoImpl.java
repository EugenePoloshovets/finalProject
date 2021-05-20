package info.library.dao;

import info.library.dbcommands.AddAuthorAndGenre;
import info.library.model.Author;
import info.library.model.Book;
import info.library.model.Genre;
import info.library.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookDaoImpl implements BookDao {

    DBUtils dbUtils = new DBUtils();

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Connection connection = dbUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT b.id, b.title, b.author_id, a.name AS author, b.genre_id, g.name AS genre, b.ISBN FROM books b\n" +
                "LEFT JOIN authors a ON b.author_id = a.id \n" +
                "LEFT JOIN genres g ON b.genre_id = g.id;");

        return createdBook(resultSet, connection);
    }

    private List<Book> createdBook(ResultSet resultSet, Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            Author author = new Author();
            author.setId(resultSet.getLong("author_id"));
            author.setName(resultSet.getString("author"));
            book.setAuthor(author);
            Genre genre = new Genre();
            genre.setId(resultSet.getLong("genre_id"));
            genre.setName(resultSet.getString("genre").toUpperCase(Locale.ROOT));
            book.setGenre(genre);
            book.setISBN(resultSet.getString("ISBN"));
            books.add(book);
        }
        dbUtils.closeConnection(connection);
        return books;
    }

    @Override
    public void addBookToDatabase(Book inputBook) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title, author_id, genre_id, ISBN) VALUES (?, ?, ?, ?);");
        statement.setString(1, inputBook.getTitle());
        statement.setLong(2, new AddAuthorAndGenre().searchAndGetAuthorId(inputBook.getAuthor().getName()));
        statement.setLong(3, new AddAuthorAndGenre().searchAndGetGenreId(inputBook.getGenre().getName()));
        statement.setString(4, inputBook.getISBN());
        statement.executeUpdate();

        dbUtils.closeConnection(connection);
    }

    @Override
    public Book getBookById(Long id) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("SELECT b.id, b.title, b.author_id, a.name AS author, b.genre_id, g.name AS genre, b.ISBN FROM books b\n" +
                "LEFT JOIN authors a on b.author_id = a.id \n" +
                "LEFT JOIN genres g on b.genre_id = g.id\n" +
                "WHERE b.id = ?;");
        prepareStatement.setLong(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        return createdBook(resultSet, connection).get(0);
    }

    @Override
    public void deleteBookByID(Long id) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM books WHERE id=?;");
        prepareStatement.setLong(1, id);
        prepareStatement.executeUpdate();

        dbUtils.closeConnection(connection);
    }

    @Override
    public void updateBook(Book bookForUpdate) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement statement = connection
                .prepareStatement("UPDATE `books` SET title = ?, author_id = ?, genre_id = ?, ISBN = ? WHERE id = ?;");
        statement.setString(1, bookForUpdate.getTitle());
        statement.setLong(2, new AddAuthorAndGenre().searchAndGetAuthorId(bookForUpdate.getAuthor().getName()));
        statement.setLong(3, new AddAuthorAndGenre().searchAndGetGenreId(bookForUpdate.getGenre().getName()));
        statement.setString(4, bookForUpdate.getISBN());
        statement.executeUpdate();

        dbUtils.closeConnection(connection);
    }
}

