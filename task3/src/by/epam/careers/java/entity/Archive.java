package by.epam.careers.java.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "StudentsArchive")
public class Archive implements Serializable {
    private static final long serialVersionUID = 248793973538046703L;

    private List<Student> students;

    {
        students = new ArrayList<>();
    }

    public Archive() {
    }

    public Archive(List<Student> students) {
        this.students = students;
    }

    @XmlElement(name = "Student")
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archive archive = (Archive) o;
        return Objects.equals(students, archive.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }

    @Override
    public String toString() {
        return "Archive{" +
                "students=" + students +
                '}';
    }
}
