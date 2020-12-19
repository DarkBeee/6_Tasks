package by.epam.careers.java.client.giu;

import by.epam.careers.java.entity.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentModel extends AbstractTableModel {
    private static final String[] headers = {"ID", "Имя", "Факультет", "Группа", "Номер телефона", "Адресс"};

    private final List<Student> students;

    public StudentModel(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return headers[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return student.getId();
            case 1:
                return student.getName();
            case 2:
                return student.getFaculty();
            case 3:
                return student.getGroup();
            case 4:
                return student.getPhone();
            default:
                return student.getAddress();
        }
    }
}
