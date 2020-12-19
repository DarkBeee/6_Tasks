package by.epam.careers.java.view;

import by.epam.careers.java.entity.Note;

import java.util.List;

public class NoteView {
    private static final NoteView instance = new NoteView();

    private NoteView() {
    }

    public static NoteView getInstance() {
        return instance;
    }

    public void print(List<Note> notes) {
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    public void print(String message, List<Note> notes) {
        System.out.println(message);
        for (Note note : notes) {
            System.out.println(note);
        }
    }
}
