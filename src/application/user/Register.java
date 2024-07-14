package application.user;

import application.display.Display;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Register {

    //Fields
    private static final String PATH_TO_USER_NAME_FILE = "src\\application\\database\\userInformation\\UserName.txt";
    private static final String PATH_TO_USER_PASSWORD_FILE = "src\\application\\database\\userInformation\\UserPassword.txt";

    public void createUser(TextField userName, PasswordField password, ActionEvent event) {
        String contentToWrite = userName.getText();

        writeInFile(contentToWrite, PATH_TO_USER_NAME_FILE);

        contentToWrite = password.getText();
        writeInFile(contentToWrite, PATH_TO_USER_PASSWORD_FILE);

        new Display().exitFromProgram(event);
    }

    private void writeInFile(String content, String pathToFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace(); // TODO: 21-Jan-17 finish exception
        }
    }

    // TODO: 23-Jan-17 finish description
    /**
     *
     * @param content
     * @param pathToFile
     * @param separatorRegex
     */
    public void appendNewLineWithContentInFile(String content, String pathToFile, String separatorRegex) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile, true))) {
            bw.write(content.concat(separatorRegex));
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 23-Jan-17 finish exception handling
        }
    }

}
