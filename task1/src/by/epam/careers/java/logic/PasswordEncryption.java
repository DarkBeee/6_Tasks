package by.epam.careers.java.logic;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PasswordEncryption {
    private static final PasswordEncryption instance = new PasswordEncryption();

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Владислав\\IdeaProjects\\6_Tasks\\task1\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(PasswordEncryption.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PasswordEncryption() {
    }

    public static PasswordEncryption getInstance() {
        return instance;
    }

    public String encryptPassword(String password) {
        byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        try {
            logger.log(Level.INFO, "Шифрование пароля");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] data = cipher.doFinal(password.getBytes());
            return DatatypeConverter.printHexBinary(data);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException e) {
            logger.log(Level.WARNING, "Ошибка при шифровании пароля", e);
        }
        return null;
    }

    public String decryptPassword(String password) {
        byte[] keyBytes = new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        try {
            logger.log(Level.INFO, "Расшифровка пароля");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(password)));
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException e) {
            logger.log(Level.WARNING, "Ошибка при расшифровке пароля", e);
        }
        return null;
    }
}
