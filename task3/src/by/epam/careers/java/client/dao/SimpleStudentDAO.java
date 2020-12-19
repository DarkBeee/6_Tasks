package by.epam.careers.java.client.dao;

import by.epam.careers.java.client.main.Client;
import by.epam.careers.java.entity.Student;

import java.util.List;

public class SimpleStudentDAO implements StudentDAO {
    private static final SimpleStudentDAO instance = new SimpleStudentDAO();

    private final List<Student> students;

    {
        students = new Client().getStudentFromServer().getStudents();
    }

    private SimpleStudentDAO() {
    }

    public static SimpleStudentDAO getInstance(){
        return instance;
    }

    @Override
    public Long addStudent(Student student) {
        Long id = generateStudentId();
        student.setId(id);
        students.add(student);
        return id;
    }

    @Override
    public void updateStudent(Student student) {
        Student oldStudent = getStudent(student.getId());
        if (oldStudent != null) {
            oldStudent.setName(student.getName());
            oldStudent.setFaculty(student.getFaculty());
            oldStudent.setGroup(student.getGroup());
            oldStudent.setAddress(student.getAddress());
            oldStudent.setPhone(student.getPhone());
        }
    }

    @Override
    public void deleteStudent(Long studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                students.remove(i);
            }
        }
    }

    @Override
    public Student getStudent(Long studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findStudents() {
        return students;
    }

    private Long generateStudentId() {
        Long studentId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while (getStudent(studentId) != null) {
            studentId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return studentId;
    }
}
