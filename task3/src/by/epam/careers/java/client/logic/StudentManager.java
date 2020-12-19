package by.epam.careers.java.client.logic;

import by.epam.careers.java.client.dao.StudentDAO;
import by.epam.careers.java.client.dao.StudentDAOFactory;
import by.epam.careers.java.entity.Student;

import java.util.List;

public class StudentManager {
    private StudentDAO studentDao;

    public StudentManager() {
        studentDao = StudentDAOFactory.getStudentDAO();
    }

    public Long addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    public void deleteStudent(Long studentId) {
        studentDao.deleteStudent(studentId);
    }

    public Student getStudent(Long studentId) {
        return studentDao.getStudent(studentId);
    }

    public List<Student> findStudents() {
        return studentDao.findStudents();
    }

}
