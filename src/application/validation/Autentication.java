package application.validation;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class Autentication {
    //Fields
    private TextField userNameTextField;
    private PasswordField passwordField;
    private String userName;
    private String userPassword;
    private static final String PATH_TO_USER_NAME_FILE = "src\\application\\database\\userInformation\\UserName.txt";
    private static final String PATH_TO_USER_PASSWORD_FILE = "src\\application\\database\\userInformation\\UserPassword.txt";

    //Constructors

    public Autentication(TextField userNameTextField, PasswordField passwordField) {
        this.userNameTextField = userNameTextField;
        this.passwordField = passwordField;
    }


    //Setters

    //Getters

    //Methods
    private String readFromFile(String pathToFile) {
        String result = "";

        File f = new File(pathToFile);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return result = new String(bytes,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String getUserName() {
        return this.userName = readFromFile(PATH_TO_USER_NAME_FILE);
    }

    private String getUserPassword() {
        return this.userPassword = readFromFile(PATH_TO_USER_PASSWORD_FILE);
    }

    public boolean autenticationOfUser() {
        return this.userNameTextField.getText().equals(getUserName()) &&
                this.passwordField.getText().equals(getUserPassword());
    }

}
