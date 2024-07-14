package application.controllers;

import application.contacts.Contact;
import application.display.Display;
import application.validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewContactPopUpController {

    // Fields
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="firstName"
    private TextField firstName; // Value injected by FXMLLoader

    @FXML // fx:id="lastName"
    private TextField lastName; // Value injected by FXMLLoader

    @FXML // fx:id="dateOfBirth"
    private DatePicker dateOfBirth; // Value injected by FXMLLoader

    @FXML // fx:id="eMail"
    private TextField eMail; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumber"
    private TextField phoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="address"
    private TextField address; // Value injected by FXMLLoader

    @FXML // fx:id="note"
    private TextArea note; // Value injected by FXMLLoader

    private Contact contact;

    //Methods

    @FXML
    void addContact(ActionEvent event) {
        // check if first name is empty
        if (new DataValidation().isTextFieldEmpty(this.firstName)) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за вида на грешката.",
                    "Не сте въвели нищо в полето \"Име\".\nПолето трябва да е попълнено.\nМоля опитайте отново. "
            );
        } else {
            // add contact to file list with ID
            DataValidation validation = new DataValidation();
            this.contact = new Contact(
                    this.firstName.getText(),
                    validation.isTextFieldEmpty(this.lastName) ? "" : this.lastName.getText(),
                    validation.isDatePickerEmpty(this.dateOfBirth) ? "" : this.dateOfBirth.getValue().toString(),
                    validation.isTextFieldEmpty(this.phoneNumber) ? "" : this.phoneNumber.getText(),
                    validation.isTextFieldEmpty(this.eMail) ? "" : this.eMail.getText(),
                    validation.isTextFieldEmpty(this.address) ? "" : this.address.getText(),
                    validation.isTextFieldEmpty(this.note) ? "" : this.note.getText()
            );

            this.contact.appendNewContactInList(this.contact);
        }
        this.firstName.clear();
        this.lastName.clear();
        this.dateOfBirth.getEditor().clear();
        this.phoneNumber.clear();
        this.eMail.clear();
        this.address.clear();
        this.note.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";
        assert dateOfBirth != null : "fx:id=\"dateOfBirth\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";
        assert eMail != null : "fx:id=\"eMail\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";
        assert phoneNumber != null : "fx:id=\"phoneNumber\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";
        assert note != null : "fx:id=\"note\" was not injected: check your FXML file 'AddNewContactPopUp.fxml'.";

    }
}

