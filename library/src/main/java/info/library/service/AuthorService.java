package info.library.service;

import info.library.dao.AuthorDao;
import info.library.dao.AuthorDaoImpl;
import info.library.model.Author;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {

    private AuthorDao authorDao = new AuthorDaoImpl();

    public List<Author> fetchAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        try {
            List<Author> allAuthors = authorDao.getAllAuthors();
            authorList.addAll(authorDao.getAllAuthors());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorList;
    }

    public Author fetchAuthorById(Long id) {
        try {
            return authorDao.getAuthorById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addAuthorToDB(String addAuthor) {
        try {
            authorDao.addAuthorToDB(addAuthor);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
