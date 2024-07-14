package application.controllers;

import application.contacts.EditContact;
import application.contacts.LoadContacts;
import application.display.Display;
import application.validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditContactPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="contactList"
    private ComboBox<String> contactList; // Value injected by FXMLLoader

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

    private void clearAllFields() {
        this.firstName.clear();
        this.lastName.clear();
        this.dateOfBirth.getEditor().clear();
        this.phoneNumber.clear();
        this.eMail.clear();
        this.address.clear();
        this.note.clear();
    }

    private void callInfoPopUp(String message) {
        new Display().infoPopUpWithHeader("Грешка", "Информация за грешката", message);
    }

    @FXML
    void editContact(ActionEvent event) {
        // Check if selected value from combo box list is null
        if (this.contactList.getValue() == null) {
            callInfoPopUp("Не сте избрали контакт за редактиране. Моля опитайте отново");
            clearAllFields();
        } else {
            // Create data validation instance
            DataValidation validation = new DataValidation();

            // load contacts list
            ArrayList<String> allInfoForContact = new LoadContacts().loadInfoForContact(this.contactList.getValue());

            // Take contact id
            String id = allInfoForContact.get(0);

            // check if first name field is empty
            if (this.firstName.getText().isEmpty()) {
                callInfoPopUp("Полето \"Име\" не може да е празно. Моля опитайте отново.");
                this.firstName.setText(allInfoForContact.get(1));
            } else { // first name is not empty

                // Check if first name contains unsupported characters
                char[] firstNameCharArray = this.firstName.getText().toCharArray();
                for (char c : firstNameCharArray) {
                    if (validation.isNotLetter(c)) {
                        callInfoPopUp("Полето \"Име\" може да съдържа само малки и големи букви. Моля опитайте отново.");
                        this.firstName.setText(allInfoForContact.get(1));
                        break;
                    }
                }

            }

            // Check if last name field is empty and if is NOT check for unsupported chars
            if (!validation.isTextFieldEmpty(this.lastName)) {
                char[] lastNameCharArray = this.lastName.getText().toCharArray();
                for (char ch : lastNameCharArray) {
                    if (validation.isNotLetter(ch)) {
                        callInfoPopUp("Полето \"Фамилия\" може да съдържа само малки и големи букви. Моля опитайте отново.");
                        this.lastName.setText(allInfoForContact.get(2));
                        break;
                    }
                }
            }

//            // Take value from date picker
//            String birthday = new DataValidation().isDatePickerEmpty(this.dateOfBirth) ? "" : this.dateOfBirth.getValue().toString();

            // Take all values like String and pass it to EditContact class
            EditContact editContact = new EditContact(
                    id,
                    this.firstName.getText(),
                    validation.isTextFieldEmpty(this.lastName) ? "" : this.lastName.getText(),
                    validation.isDatePickerEmpty(this.dateOfBirth) ? "" : this.dateOfBirth.getValue().toString(),
                    validation.isTextFieldEmpty(this.phoneNumber) ? "" : this.phoneNumber.getText(),
                    validation.isTextFieldEmpty(this.eMail) ? "" : this.eMail.getText(),
                    validation.isTextFieldEmpty(this.address) ? "" : this.address.getText(),
                    validation.isTextFieldEmpty(this.note) ? "" : this.note.getText()
            );

            // edit contact
            try {
                editContact.editSelectedContact();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                // TODO: 29-Jan-17 finish exception handling
            }

            // close edit pop-up
            new Display().closePopUpScene(event);
        }
    }

    @FXML
    void loadContactInformation(ActionEvent event) {

        // Clear all text box values before load new
        clearAllFields();

        ArrayList<String> allInfoForContact = new LoadContacts().loadInfoForContact(this.contactList.getValue());

        // Index 0 in array is ID
        Integer index = 0;
        index++;

        // Set first name
        this.firstName.setText(allInfoForContact.get(index));
        index++;

        // Set last name
        if (!(allInfoForContact.get(index).length() <= 0)) {
            this.lastName.setText(allInfoForContact.get(index));
        }
        index++;

        // Set birthday
        if (!(allInfoForContact.get(index).length() <= 0)) {
            this.dateOfBirth.setValue(LocalDate.parse(allInfoForContact.get(index)));
        }
        index++;

        // Set phone number
        if (!(allInfoForContact.get(index).length() <= 0)) {
            this.phoneNumber.setText(allInfoForContact.get(index));
        }
        index++;

        // Set e-mail address
        if (!(allInfoForContact.get(index).length() <= 0)) {
            this.eMail.setText(allInfoForContact.get(index));
        }
        index++;

        // Set Address
        if (!(allInfoForContact.get(index).length() <= 0)) {
            this.address.setText(allInfoForContact.get(index));
        }
        index++;

        // Set note
        if (!(allInfoForContact.get(index).length() <= 0)) {
            this.note.setText(allInfoForContact.get(index));
        }
        index = 0;

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert contactList != null : "fx:id=\"contactList\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert dateOfBirth != null : "fx:id=\"dateOfBirth\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert eMail != null : "fx:id=\"eMail\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert phoneNumber != null : "fx:id=\"phoneNumber\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";
        assert note != null : "fx:id=\"note\" was not injected: check your FXML file 'EditContactPopUp.fxml'.";

        // load contacts in combo box
        this.contactList.getItems().addAll(new LoadContacts().loadAllNamesAndIDsFromFile());
    }
}
