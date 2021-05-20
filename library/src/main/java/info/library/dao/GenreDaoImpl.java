package info.library.dao;

import info.library.model.Genre;
import info.library.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {

    DBUtils dbUtils = new DBUtils();

    @Override
    public List<Genre> getAllGenres() throws SQLException {
        Connection connection = dbUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name FROM genres");

        return createdGenre(resultSet, connection);
    }

    @Override
    public Genre getAuthorById(Long id) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("SELECT id, name FROM genres WHERE id = ?");
        prepareStatement.setLong(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();

        return createdGenre(resultSet, connection).get(0);
    }

    private List<Genre> createdGenre(ResultSet resultSet, Connection connection) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        while (resultSet.next()) {
            Genre genre = new Genre();
            genre.setId(resultSet.getLong("id"));
            genre.setName(resultSet.getString("name"));
            genres.add(genre);
        }
        dbUtils.closeConnection(connection);
        return genres;
    }

    @Override
    public void addGenreToDB(String genreName) throws SQLException {
        Connection connection = dbUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO genres (name) VALUES (?);");
        statement.setString(1, genreName);
        statement.executeUpdate();

        dbUtils.closeConnection(connection);
    }

}
