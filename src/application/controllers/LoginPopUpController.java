package application.controllers;

import application.display.Display;
import application.validation.Autentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML // fx:id="userPassword"
    private PasswordField userPassword; // Value injected by FXMLLoader

    @FXML
    void exit(ActionEvent event) {
        new Display().exitFromProgram(event);
    }

    @FXML
    void login(ActionEvent event) {
        Autentication autentication = new Autentication(this.userName, this.userPassword);
        if (autentication.autenticationOfUser()) {
            new Display().closePopUpScene(event);
        } else {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката",
                    "Въвели сте грешно потребителко име или парола, моля опитайте отново."
            );
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'LoginPopUp.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'LoginPopUp.fxml'.";
        assert userPassword != null : "fx:id=\"userPassword\" was not injected: check your FXML file 'LoginPopUp.fxml'.";

    }
}
