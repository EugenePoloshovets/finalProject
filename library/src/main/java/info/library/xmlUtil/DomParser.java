package info.library.xmlUtil;

import info.library.model.Author;
import info.library.model.Book;
import info.library.model.Genre;
import info.library.service.BookService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DomParser {
    private static Document document;

    public static ArrayList<Book> getObjectsFromXML(File file) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(file);

        NodeList bookNodeList = document.getElementsByTagName("book");

        ArrayList<Book> bookList = new ArrayList<>();

        for (int i = 0; i < bookNodeList.getLength(); i++) {
            Element bookElement = (Element) bookNodeList.item(i);

            Book book = new Book();

            NodeList childNodes = bookElement.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNodes.item(j);

                    switch (childElement.getNodeName()) {
                        case "title":
                            book.setTitle(childElement.getTextContent());
                            break;
                        case "author":
                            Author author = new Author();
                            author.setName(childElement.getTextContent());
                            book.setAuthor(author);
                            break;
                        case "genre":
                            Genre genre = new Genre();
                            genre.setName(childElement.getTextContent().toUpperCase());
                            book.setGenre(genre);
                            break;
                        case "ISBN":
                            book.setISBN(childElement.getTextContent());
                            break;
                    }
                }
            }
            if(book.getTitle() != null){
                System.out.println(book);
                bookList.add(book);
            }
        }
        return bookList;
    }
}
