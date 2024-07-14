package application.controllers;

import application.display.Display;
import application.user.Register;
import application.validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterNewUserPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="registerBtn"
    private Button registerBtn; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML // fx:id="userPassword"
    private PasswordField userPassword; // Value injected by FXMLLoader

    private static final String PATH_TO_USER_NAME_FILE = "application/database/userInformation/UserName.txt";
    private static final String PATH_TO_USER_PASSWORD_FILE = "application/database/userInformation/UserPassword.txt";

    @FXML
    void exit(ActionEvent event) {
        new Display().exitFromProgram(event);
    }

    @FXML
    void registerNewUser(ActionEvent event) {
        DataValidation validation = new DataValidation();

        if (new DataValidation().isUserNameOrPassFieldEmpty(this.userName, this.userPassword)) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация загрешката.",
                    "Полетата не може да са празни. Моля опитайте отново."
            );
        } else {

            char[] userChars = this.userName.getText().toCharArray();
            for (Character ch : userChars) {
                if (validation.isNotLetter(ch)) {
                    new Display().infoPopUpWithHeader(
                            "Грешка",
                            "Информация за грешката.",
                            "Потребителското име може да съдържа само малки и големи букви. Моля опитайте отново."
                    );
                    userName.clear();
                    break;
                }
            }

            char[] passChar = this.userPassword.getText().toCharArray();
            for (Character ch : passChar) {
                if (validation.isNotDigit(ch)) {
                    new Display().infoPopUpWithHeader(
                            "Грешка",
                            "Информация за грешката.",
                            "Паролата може да съдържа само цифри. Моля опитайте отново."
                    );
                    userPassword.clear();
                    break;
                }
            }

            if (new DataValidation().isUserNameOrPassFieldEmpty(this.userName, this.userPassword)) {
                new Display().infoPopUpWithHeader(
                        "Грешка",
                        "Информация загрешката.",
                        "Полетата не може да са празни. Моля опитайте отново."
                );
            } else {
                // TODO: 20-Jan-17 create user
                Register register = new Register();
                register.createUser(this.userName, this.userPassword, event);
            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert registerBtn != null : "fx:id=\"registerBtn\" was not injected: check your FXML file 'RegisterNewUserPopUp.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'RegisterNewUserPopUp.fxml'.";
        assert userPassword != null : "fx:id=\"userPassword\" was not injected: check your FXML file 'RegisterNewUserPopUp.fxml'.";

        new Display().infoPopUpWithHeader(
                "Информация",
                "Моля регистрирайте се, за да продължите.",
                "Потребителското име трябва да съдържа само латински букви." +
                        "Паролата трябва да съдържа само числа." +
                        "След регистрация програмата ще се затвори автоматично." +
                        "При повторно стартиране ще е нужно да въведете потребителско име" +
                        "и парола, за да имате достъп до календара."
        );
    }
}
