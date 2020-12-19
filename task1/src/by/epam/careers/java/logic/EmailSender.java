package by.epam.careers.java.logic;

import by.epam.careers.java.entity.UserAccount;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EmailSender {
    private static final EmailSender instance = new EmailSender();

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Владислав\\IdeaProjects\\6_Tasks\\task1\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(EmailSender.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EmailSender() {
    }

    public static EmailSender getInstance() {
        return instance;
    }

    public void sendEmailToUsers(String from, String password, String theme, String message, List<UserAccount> users) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        Message mimeMessage = new MimeMessage(session);
        try {
            logger.log(Level.INFO, "Отправка писем пользователям");
            Transport transport = session.getTransport("smtp");
            InternetAddress[] address = new InternetAddress[users.size()];
            for (int i = 0; i < address.length; i++) {
                address[i] = new InternetAddress(users.get(i).getEmail());
            }
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipients(Message.RecipientType.TO, address);
            mimeMessage.setSubject(theme);
            mimeMessage.setText(message);
            transport.connect("smtp.gmail.com", from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "Ошибка при отправке писем пользователям", e);
        }
    }

    public void sendBookToLibrary(String from, String password, String theme, String message, String to) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        Message mimeMessage = new MimeMessage(session);
        try {
            logger.log(Level.INFO, "Отправка письма в каталог");
            Transport transport = session.getTransport("smtp");
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
            mimeMessage.setSubject(theme);
            mimeMessage.setText(message);
            transport.connect("smtp.gmail.com", from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "Ошибка при отправке письма в каталог", e);
        }
    }
}
