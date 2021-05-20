package info.library;

import info.library.dbcommands.CompareWithDB;;
import info.library.model.Book;
import info.library.service.BookService;
import info.library.util.Application;
import info.library.xmlUtil.DomParser;
import info.library.xmlUtil.XmlValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Runner {

    public static final String XSD_PATH = "C:/dev/finalProject/library/src/main/java/info/library/xml/bookSchema.xsd";
    public static final String XML_PATH = "C:/dev/finalProject/library/src/main/java/info/library/xml/books.xml";
    private static volatile List<Book> booksListFromXML;
    private static final File XML_FILE = new File(XML_PATH);

    public static void main(String[] args) {
        BookService bookService = new BookService();

        System.out.println("*****XML*****READ*****");
        if (XmlValidator.validateXMLSchema(XSD_PATH, XML_PATH)) {
            System.out.println("XML валидация прошла успешно!!");
            try {
                booksListFromXML = DomParser.getObjectsFromXML(XML_FILE);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < booksListFromXML.size(); i++) {
                Book book = booksListFromXML.get(i);
                if (new CompareWithDB().compareTitle(book.getTitle())) {
                } else {
                    bookService.addBookToDatabase(book);
                }
            }
            new Application().startProgram();
        } else {
            System.out.println("XML валидация не прошла");
        }
    }
}

