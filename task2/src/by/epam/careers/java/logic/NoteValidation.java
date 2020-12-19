package by.epam.careers.java.logic;

import by.epam.careers.java.dao.XmlNoteDao;
import by.epam.careers.java.entity.Note;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteValidation {
    private static final NoteValidation instance = new NoteValidation();

    private NoteValidation() {
    }

    public static NoteValidation getInstance() {
        return instance;
    }

    public Note creatingNote(String theme, String email, String message) {
        Note note = null;
        Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            note = new Note(theme.trim(), LocalDateTime.now(), email.trim(), message.trim());
            XmlNoteDao.getInstance().writeNote(note);
            return note;
        }
        return null;
    }
}
