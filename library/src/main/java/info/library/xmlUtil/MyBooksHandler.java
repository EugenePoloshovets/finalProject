package info.library.xmlUtil;

import info.library.model.Author;
import info.library.model.Book;
import info.library.model.Genre;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class MyBooksHandler extends DefaultHandler {

    Book book = new Book();
    boolean titleFlag = false;
    boolean authorFlag = false;
    boolean genreFlag = false;
    boolean ISBNFlag = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("title")) {
            titleFlag = true;
        }
        if (qName.equalsIgnoreCase("author")) {
            authorFlag = true;
        }
        if (qName.equalsIgnoreCase("genre")) {
            genreFlag = true;
        }
        if (qName.equalsIgnoreCase("ISBN")) {
            ISBNFlag = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (titleFlag) {
            book.setTitle(new String(ch, start, length));
            System.out.print("Title: " + new String(ch, start, length) + ", ");
            titleFlag = false;
        }
        if (authorFlag) {
            Author author = new Author();
            author.setName(new String(ch, start, length));
            book.setAuthor(author);
            System.out.print("Author: " + new String(ch, start, length) + ", ");
            authorFlag = false;
        }
        if (genreFlag) {
            Genre genre = new Genre();
            genre.setName(new String(ch, start, length));
            book.setGenre(genre);
            System.out.print("Genre: " + new String(ch, start, length) + ", ");
            genreFlag = false;
        }
        if (ISBNFlag) {
            book.setISBN(new String(ch, start, length));
            System.out.println("ISBN: " + new String(ch, start, length) + ", ");
            ISBNFlag = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("/book")) {

        }
    }
}

