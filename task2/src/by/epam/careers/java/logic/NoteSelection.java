package by.epam.careers.java.logic;

import by.epam.careers.java.entity.Note;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteSelection {
    private static final NoteSelection instance = new NoteSelection();

    private NoteSelection() {
    }

    public static NoteSelection getInstance() {
        return instance;
    }

    public List<Note> findRecordsContainingASpecificWord(List<Note> notes, String word) {
        List<Note> found = new ArrayList<Note>();
        Pattern pattern = Pattern.compile("\\b" + word.toLowerCase() + "\\b");
        for (Note note : notes) {
            Matcher matcher = pattern.matcher(note.getMessage().toLowerCase());
            if (matcher.find()) {
                found.add(note);
            }
        }
        return found;
    }

    public List<Note> findByTheme(List<Note> notes, String theme) {
        List<Note> found = new ArrayList<Note>();
        Pattern pattern = Pattern.compile(theme.toLowerCase());
        for (Note note : notes) {
            Matcher matcher = pattern.matcher(note.getTheme().toLowerCase());
            if (matcher.find()) {
                found.add(note);
            }
        }
        return found;
    }

    public List<Note> findByCreationDate(List<Note> notes, String date) {
        List<Note> found = new ArrayList<Note>();
        Pattern pattern = Pattern.compile(date);
        for (Note note : notes) {
            Matcher matcher = pattern.matcher(note.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            if (matcher.matches()) {
                found.add(note);
            }
        }
        return found;
    }

    public List<Note> findByEmail(List<Note> notes, String email) {
        List<Note> found = new ArrayList<Note>();
        Pattern pattern = Pattern.compile(email.toLowerCase());
        for (Note note : notes) {
            Matcher matcher = pattern.matcher(note.getEmail().toLowerCase());
            if (matcher.find()) {
                found.add(note);
            }
        }
        return found;
    }

    public List<Note> findByMessage(List<Note> notes, String message) {
        List<Note> found = new ArrayList<Note>();
        Pattern pattern = Pattern.compile(message.toLowerCase());
        for (Note note : notes) {
            Matcher matcher = pattern.matcher(note.getMessage().toLowerCase());
            if (matcher.find()) {
                found.add(note);
            }
        }
        return found;
    }
}
