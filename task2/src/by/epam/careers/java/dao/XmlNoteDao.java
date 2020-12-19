package by.epam.careers.java.dao;

import by.epam.careers.java.entity.Note;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class XmlNoteDao implements NoteDao {
    private static final XmlNoteDao instance = new XmlNoteDao();

    private static String fileLocation = "D:\\IdeaProjects\\6_Tasks\\task2\\src\\by\\epam\\careers\\resources\\Notebook.xml";

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("D:\\IdeaProjects\\6_Tasks\\task2\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(XmlNoteDao.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XmlNoteDao() {
    }

    public static XmlNoteDao getInstance() {
        return instance;
    }

    public List<Note> readNotes() {
        List<Note> notes = new ArrayList<Note>();
        Document document = null;
        try {
            logger.log(Level.INFO, "Чтение XML файла");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new File(fileLocation));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.log(Level.WARNING, "Ошибка при чтении из XML файла", e);
        }
        Node node = document.getDocumentElement();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node note = nodeList.item(i);
            List<String> text = new ArrayList<>();
            if (note.getNodeType() != Node.TEXT_NODE) {
                NodeList noteProps = note.getChildNodes();
                Node t = noteProps.item(0);
                for (int j = 0; j < noteProps.getLength(); j++) {
                    Node noteProp = noteProps.item(j);
                    if (noteProp.getNodeType() != Node.TEXT_NODE) {
                        text.add(noteProp.getTextContent());
                    }
                }
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                notes.add(new Note(text.get(0), LocalDateTime.parse(text.get(1), parser), text.get(2), text.get(3)));
            }
        }
        return notes;
    }

    public void writeNote(Note note) {
        Document document = null;
        try {
            logger.log(Level.INFO, "Построение XML документа");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new File(fileLocation));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.log(Level.WARNING, "Ошибка при постоении XML документа", e);
        }
        Node node = document.getDocumentElement();
        Element n = document.createElement("note");
        Element theme = document.createElement("theme");
        theme.setTextContent(note.getTheme());
        Element date = document.createElement("date");
        date.setTextContent(note.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        Element email = document.createElement("email");
        email.setTextContent(note.getEmail());
        Element message = document.createElement("message");
        message.setTextContent(note.getMessage());
        n.appendChild(theme);
        n.appendChild(date);
        n.appendChild(email);
        n.appendChild(message);
        node.appendChild(n);
        try {
            logger.log(Level.INFO, "Запись в XML файл");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileLocation);
            StreamResult streamResult = new StreamResult(fos);
            transformer.transform(source, streamResult);
            fos.close();
        } catch (IOException | TransformerException e) {
            logger.log(Level.WARNING, "Ошибка при записи в XML файл", e);
        }
    }

    public void writeNotes(List<Note> notes) {
        Document document = null;
        try {
            logger.log(Level.INFO, "Построение XML документа");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new File(fileLocation));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.log(Level.WARNING, "Ошибка при постоении XML документа", e);
        }
        Node node = document.getDocumentElement();
        for (Note note : notes) {
            Element n = document.createElement("note");
            Element theme = document.createElement("theme");
            theme.setTextContent(note.getTheme());
            Element date = document.createElement("date");
            date.setTextContent(note.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            Element email = document.createElement("email");
            email.setTextContent(note.getEmail());
            Element message = document.createElement("message");
            message.setTextContent(note.getMessage());
            n.appendChild(theme);
            n.appendChild(date);
            n.appendChild(email);
            n.appendChild(message);
            node.appendChild(n);
        }
        try {
            logger.log(Level.INFO, "Запись в XML файл");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileLocation, false);
            StreamResult streamResult = new StreamResult(fos);
            transformer.transform(source, streamResult);
            fos.close();
        } catch (IOException | TransformerException e) {
            logger.log(Level.WARNING, "Ошибка при записи в XML файл", e);
        }

    }
}
