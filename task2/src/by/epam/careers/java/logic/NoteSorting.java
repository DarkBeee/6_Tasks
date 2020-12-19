package by.epam.careers.java.logic;

import by.epam.careers.java.entity.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoteSorting {
    private static final NoteSorting instance = new NoteSorting();

    private NoteSorting() {
    }

    public static NoteSorting getInstance() {
        return instance;
    }

    public List<Note> sortByTheme(List<Note> notes) {
        List<Note> sorted = new ArrayList<>(notes);
        sorted.sort(Comparator.comparing(Note::getTheme));
        return sorted;
    }

    public List<Note> sortByCreationDate(List<Note> notes) {
        List<Note> sorted = new ArrayList<>(notes);
        sorted.sort(Comparator.comparing(Note::getCreationDate));
        return sorted;
    }

    public List<Note> sortByEmail(List<Note> notes) {
        List<Note> sorted = new ArrayList<>(notes);
        sorted.sort(Comparator.comparing(Note::getEmail));
        return sorted;
    }

    public List<Note> sortByMessage(List<Note> notes) {
        List<Note> sorted = new ArrayList<>(notes);
        sorted.sort(Comparator.comparing(Note::getMessage));
        return sorted;
    }
}
