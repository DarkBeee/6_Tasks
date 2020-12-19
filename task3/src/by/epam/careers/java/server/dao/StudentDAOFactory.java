package by.epam.careers.java.server.dao;

public class StudentDAOFactory {

    public static StudentDAO getStudentDAO() {
        return StudentXmlDAO.getInstance();
    }
}
