package by.epam.careers.java.logic;

import by.epam.careers.java.dao.XmlNoteDao;
import by.epam.careers.java.entity.Note;

import java.util.List;

public class NoteLoading {
    private static final NoteLoading instance = new NoteLoading();

    private NoteLoading() {
    }

    public static NoteLoading getInstance() {
        return instance;
    }

    public List<Note> getNotesFromDocument() {
        return XmlNoteDao.getInstance().readNotes();
    }

    public void writeNoteToFile(Note note) {
        XmlNoteDao.getInstance().writeNote(note);
    }

    public void writeNotesToFile(List<Note> notes) {
        XmlNoteDao.getInstance().writeNotes(notes);
    }


}
