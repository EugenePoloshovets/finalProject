package info.library.dao;

import info.library.model.Author;
import info.library.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    private DBUtils dbUtils = new DBUtils();

    @Override
    public List<Author> getAllAuthors() throws SQLException {
        Connection connection = dbUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name FROM authors");

        return createdAuthor(resultSet, connection);
    }

    @Override
    public Author getAuthorById(Long id) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("SELECT id, name FROM authors WHERE id = ?");
        prepareStatement.setLong(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();

        return createdAuthor(resultSet, connection).get(0);
    }

    @Override
    public void addAuthorToDB(String authorName) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO authors (name) VALUES (?);");
        statement.setString(1, authorName);
        statement.executeUpdate();

        dbUtils.closeConnection(connection);
    }

    private List<Author> createdAuthor(ResultSet resultSet, Connection connection) throws SQLException {
        List<Author> authors = new ArrayList<>();
        while (resultSet.next()) {
            Author author = new Author();
            author.setId(resultSet.getLong("id"));
            author.setName(resultSet.getString("name"));
            authors.add(author);
        }
        dbUtils.closeConnection(connection);
        return authors;
    }

}