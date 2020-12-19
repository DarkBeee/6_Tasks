package by.epam.careers.java.main;

import by.epam.careers.java.entity.Note;
import by.epam.careers.java.entity.Notebook;
import by.epam.careers.java.service.NoteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private Notebook notebook;
    private NoteService noteService;

    public void startProgram() throws IOException {
        notebook = new Notebook(NoteService.getInstance().getNoteFromFile());
        noteService = NoteService.getInstance();
        mainMenu();
    }

    public void mainMenu() throws IOException {
        System.out.println("Notebook");
        System.out.println("1 - добавить/удалить заметку");
        System.out.println("2 - поиск заметок");
        System.out.println("3 - сортировка заметок");
        System.out.println("4 - показать все заметки");
        System.out.println("0 - выход из программы");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                noteManagementMenu();
                break;
            case "2" :
                noteSearchMenu();
                break;
            case "3" :
                noteSortMenu();
                break;
            case "4" :
                noteService.showNotebook(notebook);
                break;
            case "0" :
                return;
        }
        mainMenu();
    }

    public void noteManagementMenu() throws IOException {
        System.out.println("1 - добавить заметку");
        System.out.println("0 - назад");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                System.out.println("Введите тему заметки");
                String theme = reader.readLine();
                System.out.println("Введите ваш email");
                String email = reader.readLine();
                System.out.println("Введите сообщение");
                String message = reader.readLine();
                Note newNote = noteService.createNote(theme, email, message);
                if (newNote != null) {
                    notebook.getNotes().add(newNote);
                }
                else {
                    System.out.println("Не верный формат ввода email");
                }
                break;
            case "0" :
                return;
        }
        noteManagementMenu();
    }

    public void noteSearchMenu() throws IOException {
        System.out.println("1 - найти записи по теме");
        System.out.println("2 - найти записи по дате создания");
        System.out.println("3 - найти записи по email");
        System.out.println("4 - найти записи по сообщению");
        System.out.println("5 - найти записи, текстовое поле которой содержит определённое слово");
        System.out.println("0 - назад");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                System.out.println("Введите искомую тему");
                String theme = reader.readLine();
                noteService.foundNotesByTheme(notebook.getNotes(), theme);
                break;
            case "2" :
                System.out.println("Введите искомую дату создания в формате дд.мм.гггг");
                String date = reader.readLine();
                noteService.foundNotesByCreationDate(notebook.getNotes(), date);
                break;
            case "3" :
                System.out.println("Введите искомый email");
                String email = reader.readLine();
                noteService.foundNotesByEmail(notebook.getNotes(), email);
                break;
            case "4" :
                System.out.println("Введите искомое сообщение");
                String message = reader.readLine();
                noteService.foundNotesByMessage(notebook.getNotes(), message);
                break;
            case "5" :
                System.out.println("Введите искомое слово");
                String word = reader.readLine();
                noteService.recordsContainingASpecificWord(notebook.getNotes(), word);
                break;
            case "0" :
                return;
        }
        noteSearchMenu();
    }

    public void noteSortMenu() throws IOException {
        System.out.println("1 - сортировка по теме");
        System.out.println("2 - сортировка по дате");
        System.out.println("3 - сортировка по email");
        System.out.println("4 - сортировка по сообщению");
        System.out.println("0 - назад");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                noteService.sortedByTheme(notebook.getNotes());
                break;
            case "2" :
                noteService.sortedByCreationDate(notebook.getNotes());
                break;
            case "3" :
                noteService.sortedByEmail(notebook.getNotes());
                break;
            case "4" :
                noteService.sortedByMessage(notebook.getNotes());
                break;
            case "0" :
                return;
        }
        noteSortMenu();
    }
}
