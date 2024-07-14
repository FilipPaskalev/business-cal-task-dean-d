package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteMeetingPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="meetingDate"
    private DatePicker meetingDate; // Value injected by FXMLLoader

    @FXML // fx:id="meetingHour"
    private ComboBox<?> meetingHour; // Value injected by FXMLLoader

    @FXML // fx:id="meetingMinutes"
    private ComboBox<?> meetingMinutes; // Value injected by FXMLLoader

    @FXML // fx:id="meetingPlace"
    private TextField meetingPlace; // Value injected by FXMLLoader

    @FXML // fx:id="meetingCategory"
    private ComboBox<?> meetingCategory; // Value injected by FXMLLoader

    @FXML // fx:id="meetingContactList"
    private ComboBox<?> meetingContactList; // Value injected by FXMLLoader

    @FXML // fx:id="allSelectedContactsTextArea"
    private TextArea allSelectedContactsTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="meetingComment"
    private TextArea meetingComment; // Value injected by FXMLLoader

    @FXML // fx:id="dateAntTime"
    private TableColumn<?, ?> dateAntTime; // Value injected by FXMLLoader

    @FXML // fx:id="place"
    private TableColumn<?, ?> place; // Value injected by FXMLLoader

    @FXML // fx:id="category"
    private TableColumn<?, ?> category; // Value injected by FXMLLoader

    @FXML // fx:id="contacts"
    private TableColumn<?, ?> contacts; // Value injected by FXMLLoader

    @FXML // fx:id="comment"
    private TableColumn<?, ?> comment; // Value injected by FXMLLoader

    @FXML
    void addContactToAllContactsTextArea(ActionEvent event) {

    }

    @FXML
    void loadInformationForMeeting(ActionEvent event) {

    }

    @FXML
    void removeMeeting(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert meetingDate != null : "fx:id=\"meetingDate\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert meetingHour != null : "fx:id=\"meetingHour\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert meetingMinutes != null : "fx:id=\"meetingMinutes\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert meetingPlace != null : "fx:id=\"meetingPlace\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert meetingCategory != null : "fx:id=\"meetingCategory\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert meetingContactList != null : "fx:id=\"meetingContactList\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert allSelectedContactsTextArea != null : "fx:id=\"allSelectedContactsTextArea\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert meetingComment != null : "fx:id=\"meetingComment\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert dateAntTime != null : "fx:id=\"dateAntTime\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert place != null : "fx:id=\"place\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert contacts != null : "fx:id=\"contacts\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";
        assert comment != null : "fx:id=\"comment\" was not injected: check your FXML file 'DeleteMeetingPopUp.fxml'.";

    }
}
