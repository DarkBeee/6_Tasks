package by.epam.careers.java.client.giu;

import by.epam.careers.java.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStudentDialog extends JDialog implements ActionListener {
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;

    private final JTextPane txtName = new JTextPane();
    private final JTextPane txtFaculty = new JTextPane();
    private final JTextPane txtGroup = new JTextPane();
    private final JTextPane txtPhone = new JTextPane();
    private final JTextPane txtAddress = new JTextPane();

    private Long studentId = null;
    private boolean save = false;

    public EditStudentDialog() {
        this(null);
    }

    public EditStudentDialog(Student student) {
        setLayout(null);

        buildFields();

        initFields(student);

        buildButtons();


        setModal(true);

        setResizable(false);

        setBounds(300, 300, 450, 200);

        setVisible(true);
    }

    private void buildFields() {
        JLabel labelName = new JLabel("Имя:");
        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        add(labelName);
        txtName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        txtName.setBorder(BorderFactory.createEtchedBorder());
        add(txtName);

        JLabel labelFaculty = new JLabel("Факультет:");
        labelFaculty.setHorizontalAlignment(SwingConstants.RIGHT);
        labelFaculty.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(labelFaculty);
        txtFaculty.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtFaculty.setBorder(BorderFactory.createEtchedBorder());
        add(txtFaculty);

        JLabel labelGroup = new JLabel("Группа:");
        labelGroup.setHorizontalAlignment(SwingConstants.RIGHT);
        labelGroup.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(labelGroup);
        txtGroup.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtGroup.setBorder(BorderFactory.createEtchedBorder());
        add(txtGroup);

        JLabel labelPhone = new JLabel("Телефон:");
        labelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPhone.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(labelPhone);
        txtPhone.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtPhone.setBorder(BorderFactory.createEtchedBorder());
        add(txtPhone);

        JLabel labelAddress = new JLabel("Адресс:");
        labelAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        labelAddress.setBounds(new Rectangle(PAD, 4 * H_B + PAD, W_L, H_B));
        add(labelAddress);
        txtAddress.setBounds(new Rectangle(W_L + 2 * PAD, 4 * H_B + PAD, W_T, H_B));
        txtAddress.setBorder(BorderFactory.createEtchedBorder());
        add(txtAddress);
    }

    private void initFields(Student student) {
        if (student != null) {
            studentId = student.getId();
            txtName.setText(student.getName());
            txtFaculty.setText(student.getFaculty());
            txtGroup.setText(student.getGroup());
            txtPhone.setText(student.getPhone());
            txtAddress.setText(student.getAddress());
        }
    }

    private void buildButtons() {
        JButton buttonSave = new JButton("SAVE");
        buttonSave.setActionCommand(SAVE);
        buttonSave.addActionListener(this);
        buttonSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(buttonSave);

        JButton buttonCancel = new JButton("CANCEL");
        buttonCancel.setActionCommand(CANCEL);
        buttonCancel.addActionListener(this);
        buttonCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(buttonCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }

    public boolean isSave() {
        return save;
    }

    public Student getStudent() {
        Student student = new Student(studentId, txtName.getText(), txtFaculty.getText(),
                txtGroup.getText(), txtPhone.getText(), txtAddress.getText());
        return student;
    }
}
