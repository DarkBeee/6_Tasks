package by.epam.careers.java.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlType(propOrder = {"name", "faculty", "group", "phone", "address"})
public class Student implements Serializable {
    private static final long serialVersionUID = 612795008318270984L;

    private Long id;
    private String name;
    private String faculty;
    private String group;
    private String phone;
    private String address;

    public Student() {
    }

    public Student(String name, String faculty, String group, String phone, String address) {
        this.name = name;
        this.faculty = faculty;
        this.group = group;
        this.phone = phone;
        this.address = address;
    }

    public Student(Long id, String name, String faculty, String group, String phone, String address) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.group = group;
        this.phone = phone;
        this.address = address;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(faculty, student.faculty) &&
                Objects.equals(group, student.group) &&
                Objects.equals(phone, student.phone) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, faculty, group, phone, address);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", group='" + group + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
