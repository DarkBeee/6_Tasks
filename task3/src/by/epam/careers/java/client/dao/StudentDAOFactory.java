package by.epam.careers.java.client.dao;

public class StudentDAOFactory {

    public static StudentDAO getStudentDAO() {
        return SimpleStudentDAO.getInstance();
    }
}
