package info.library.service;

import info.library.dao.BookDao;
import info.library.dao.BookDaoImpl;
import info.library.model.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookDao bookDao = new BookDaoImpl();

    public List<Book> fetchAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try {
            List<Book> allbooks = bookDao.getAllBooks();
            bookList.addAll(bookDao.getAllBooks());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookList;
    }

    public void addBookToDatabase(Book inputBook) {
        try {
            bookDao.addBookToDatabase(inputBook);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Book fetchBookById(Long id) {
        try {
            return bookDao.getBookById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Book deleteBookById(Long id) {
        try {
            bookDao.deleteBookByID(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateBook(Book bookForUpdate) {
        try {
            bookDao.updateBook(bookForUpdate);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
