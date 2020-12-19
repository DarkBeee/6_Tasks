package by.epam.careers.java.server.logic;

import by.epam.careers.java.entity.Student;
import by.epam.careers.java.server.dao.StudentDAO;
import by.epam.careers.java.server.dao.StudentDAOFactory;

import java.util.List;

public class StudentManager {
    private StudentDAO studentDao;

    public StudentManager() {
        studentDao = StudentDAOFactory.getStudentDAO();
    }

    public List<Student> findStudents() {
        return studentDao.findStudents();
    }

    public List<Student> readStudentsFromFile() {
        return studentDao.readStudentsFromFile().getStudents();
    }

    public void writeStudentsOnFile(List<Student> students) {
        studentDao.writeStudentsOnFile(students);
    }
}
