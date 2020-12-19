package by.epam.careers.java.client.dao;

import by.epam.careers.java.entity.Archive;
import by.epam.careers.java.entity.Student;

import java.util.List;

public interface StudentDAO {

    public Long addStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(Long studentId);

    public Student getStudent(Long studentId);

    public List<Student> findStudents();

}
