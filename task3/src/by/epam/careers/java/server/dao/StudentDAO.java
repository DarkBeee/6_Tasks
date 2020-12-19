package by.epam.careers.java.server.dao;

import by.epam.careers.java.entity.Archive;
import by.epam.careers.java.entity.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> findStudents();

    public Archive readStudentsFromFile();

    public void writeStudentsOnFile(List<Student> students);
}
