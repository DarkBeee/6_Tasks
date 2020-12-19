package by.epam.careers.java.dao;

import by.epam.careers.java.entity.Note;

import java.util.List;

public interface NoteDao {

    public List<Note> readNotes();

    public void writeNote(Note note);

    public void writeNotes(List<Note> notes);
}
