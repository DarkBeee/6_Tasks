package by.epam.careers.java.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Notebook implements Serializable {
    private static final long serialVersionUID = 653887419757285891L;

    private List<Note> notes;

    {
        notes = new ArrayList<>();
    }

    public Notebook() {
    }

    public Notebook(List<Note> notes) {
        this.notes = notes;
    }

    public Note getOneNote(int poz) {
        return notes.get(poz);
    }

    public void setOneNote(Note note, int poz) {
        notes.add(poz, note);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return Objects.equals(notes, notebook.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes);
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "notes=" + notes +
                '}';
    }
}
