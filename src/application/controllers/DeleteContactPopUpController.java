package application.controllers;

import application.contacts.LoadContacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteContactPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="contactList"
    private ComboBox<String> contactList; // Value injected by FXMLLoader

    @FXML
    void deleteContact(ActionEvent event) {

    }

    @FXML
    void loadAllContactsInComboBox(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert contactList != null : "fx:id=\"contactList\" was not injected: check your FXML file 'DeleteContactPopUp.fxml'.";

        // load contacts in combo box
        this.contactList.getItems().addAll(new LoadContacts().loadAllNamesAndIDsFromFile());
    }
}
