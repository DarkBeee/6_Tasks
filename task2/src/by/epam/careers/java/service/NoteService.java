package by.epam.careers.java.service;

import by.epam.careers.java.entity.Note;
import by.epam.careers.java.entity.Notebook;
import by.epam.careers.java.logic.NoteLoading;
import by.epam.careers.java.logic.NoteSelection;
import by.epam.careers.java.logic.NoteSorting;
import by.epam.careers.java.logic.NoteValidation;
import by.epam.careers.java.view.NoteView;

import java.util.List;

public class NoteService {
    private static final NoteService instance = new NoteService();

    private NoteSorting noteSorting = NoteSorting.getInstance();
    private NoteLoading noteLoading = NoteLoading.getInstance();
    private NoteView noteView = NoteView.getInstance();
    private NoteValidation noteValidation = NoteValidation.getInstance();
    private NoteSelection noteSelection = NoteSelection.getInstance();

    private NoteService() {
    }

    public static NoteService getInstance() {
        return instance;
    }

    public List<Note> getNoteFromFile() {
        return noteLoading.getNotesFromDocument();
    }

    public void uploadNotesToFile(List<Note> notes) {
        noteLoading.writeNotesToFile(notes);
    }

    public void uploadNoteToFile(Note note) {
        noteLoading.writeNoteToFile(note);
    }

    public void foundNotesByTheme(List<Note> notes, String theme) {
        noteView.print("Найденные записи по теме '" + theme + "'",
                noteSelection.findByTheme(notes, theme));
    }

    public void foundNotesByCreationDate(List<Note> notes, String date) {
        noteView.print("Найденные записи по дате '" + date + "'",
                noteSelection.findByCreationDate(notes, date));
    }

    public void foundNotesByEmail(List<Note> notes, String email) {
        noteView.print("Найденные записи по email '" + email + "'",
                noteSelection.findByEmail(notes, email));
    }

    public void foundNotesByMessage(List<Note> notes, String message) {
        noteView.print("Найденные записи по сообщению '" + message + "'",
                noteSelection.findByMessage(notes, message));
    }

    public void recordsContainingASpecificWord(List<Note> notes, String word) {
        noteView.print("Записи содержащие слово '" + word + "'",
                noteSelection.findRecordsContainingASpecificWord(notes, word));
    }

    public Note createNote(String theme, String email, String message) {
        return noteValidation.creatingNote(theme, email, message);
    }

    public void sortedByTheme(List<Note> notes) {
        noteView.print("Сортировка по теме", noteSorting.sortByTheme(notes));
    }

    public void sortedByCreationDate(List<Note> notes) {
        noteView.print("Сортировка по дате создания", noteSorting.sortByCreationDate(notes));
    }

    public void sortedByEmail(List<Note> notes) {
        noteView.print("Сортировка по email", noteSorting.sortByEmail(notes));
    }

    public void sortedByMessage(List<Note> notes) {
        noteView.print("Сортировка по сообщению", noteSorting.sortByMessage(notes));
    }

    public void showNotebook(Notebook notebook) {
        noteView.print(notebook.getNotes());
    }
}
