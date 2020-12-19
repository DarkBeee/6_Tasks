package by.epam.careers.java.client.giu;

import by.epam.careers.java.entity.Archive;
import by.epam.careers.java.entity.Student;
import by.epam.careers.java.client.logic.StudentManager;
import by.epam.careers.java.client.main.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class StudentFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static final String EXIT = "EXIT";

    private final StudentManager studentManager = new StudentManager();
    private final JTable studentTable = new JTable();
    private final Client client = new Client();

    public StudentFrame() {
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);

        JPanel panel = new JPanel();
        panel.setLayout(gridBagLayout);
        panel.add(createButton(gridBagLayout, gridBagConstraints, "Обновить", LOAD));
        panel.add(createButton(gridBagLayout, gridBagConstraints, "Добавить", ADD));
        panel.add(createButton(gridBagLayout, gridBagConstraints, "Исправить", EDIT));
        panel.add(createButton(gridBagLayout, gridBagConstraints, "Удалить", DELETE));


        JPanel exit = new JPanel();
        exit.setLayout(gridBagLayout);
        exit.add(createButton(gridBagLayout, gridBagConstraints, "Выход", EXIT));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(panel, BorderLayout.NORTH);
        leftPanel.add(exit, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.sendStudentToServer(new Archive(studentManager.findStudents()));
            }
        });
        updateStudent();

    }

    private JButton createButton(GridBagLayout gridBagLayout, GridBagConstraints
            gridBagConstraints, String tittle, String action) {
        JButton button = new JButton(tittle);
        button.setActionCommand(action);
        button.addActionListener(this);
        gridBagLayout.setConstraints(button, gridBagConstraints);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case LOAD:
                updateStudent();
                break;
            case ADD:
                addStudent();
                break;
            case EDIT:
                editStudent();
                break;
            case DELETE:
                deleteStudent();
                break;
            case EXIT:
                client.sendStudentToServer(new Archive(studentManager.findStudents()));
                System.exit(0);
        }
    }

    private void loadStudent() {
        List<Student> students = client.getStudentFromServer().getStudents();
        StudentModel studentModel = new StudentModel(students);
        studentTable.setModel(studentModel);
    }

    private void updateStudent() {
        List<Student> students = studentManager.findStudents();
        StudentModel studentModel = new StudentModel(students);
        studentTable.setModel(studentModel);
    }

    private void addStudent() {
        EditStudentDialog studentDialog = new EditStudentDialog();
        saveStudent(studentDialog);
    }

    private void editStudent() {
        int sr = studentTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(studentTable.getModel().getValueAt(sr, 0).toString());
            Student student = studentManager.getStudent(id);
            EditStudentDialog studentDialog = new EditStudentDialog(student);
            saveStudent(studentDialog);
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Необходимо выделить требуемого студента в строке для редактирования");
        }
    }

    private void deleteStudent() {
        int sr = studentTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(studentTable.getModel().getValueAt(sr, 0).toString());
            studentManager.deleteStudent(id);
            updateStudent();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Необходимо выделить требуемого студента в строке для удаления");
        }
    }

    private void saveStudent(EditStudentDialog studentDialog) {
        if (studentDialog.isSave()) {
            Student student = studentDialog.getStudent();
            if (student.getId() != null) {
                studentManager.updateStudent(student);
            }
            else {
                studentManager.addStudent(student);
            }
            updateStudent();
        }
    }
}
