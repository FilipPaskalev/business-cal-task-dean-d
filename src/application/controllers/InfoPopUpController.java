package application.controllers;

import application.display.Display;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoPopUpController
{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="header"
    private Label header; // Value injected by FXMLLoader

    @FXML // fx:id="message"
    private Label message; // Value injected by FXMLLoader

    public Label getHeader() {
        return header;
    }

    public Label getMessage() {
        return message;
    }

    public void setHeader(Label header) {
        this.header = header;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    @FXML
    void closeInfoPopUp(ActionEvent event) {
        new Display().closePopUpScene(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert header != null : "fx:id=\"header\" was not injected: check your FXML file 'InfoPopUp.fxml'.";
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'InfoPopUp.fxml'.";
        setHeader(this.header);
        setMessage(this.message);
    }
}

