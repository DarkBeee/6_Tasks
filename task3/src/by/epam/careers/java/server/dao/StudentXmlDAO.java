package by.epam.careers.java.server.dao;

import by.epam.careers.java.entity.Archive;
import by.epam.careers.java.entity.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class StudentXmlDAO implements StudentDAO {
    private static final StudentXmlDAO instance = new StudentXmlDAO();

    private final List<Student> students;

    {
        students = readStudentsFromFile().getStudents();
    }

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("D:\\IdeaProjects\\6_Tasks\\task3\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(StudentXmlDAO.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StudentXmlDAO() {
    }

    public static StudentXmlDAO getInstance(){
        return instance;
    }

    @Override
    public List<Student> findStudents() {
        return students;
    }

    @Override
    public void writeStudentsOnFile(List<Student> students) {
        try {
            logger.log(Level.INFO, "Запись данных в файла");
            JAXBContext context = JAXBContext.newInstance(Archive.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Archive(students), new File("D:\\IdeaProjects\\6_Tasks\\task3\\src\\by\\epam\\careers\\resources\\students.xml"));
        } catch (JAXBException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных в файл", e);
        }
    }

    @Override
    public Archive readStudentsFromFile() {
        Archive archive = null;
        try {
            logger.log(Level.INFO, "Чтение данных из файла");
            JAXBContext context = JAXBContext.newInstance(Archive.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            archive = (Archive) unmarshaller.unmarshal(new File("D:\\IdeaProjects\\6_Tasks\\task3\\src\\by\\epam\\careers\\resources\\students.xml"));
        } catch (JAXBException e) {
            logger.log(Level.WARNING, "Ошибка при чтении данных из файла", e);
        }
        return archive;
    }
}
