package application.controllers;

import application.category.LoadCategories;
import application.contacts.LoadContacts;
import application.display.Display;
import application.meetings.MeetingData;
import application.meetings.MeetingLoader;
import application.validation.DataValidation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditMeetingPopUpController {

    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private static final String PATH_TO_MEETING_LIST_FILE = "src/application/database/meetings/Events";
    private static final String regexNewLine = "\\r?\\n";
    // create variable data with all meetings
    ObservableList<MeetingData> data;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dateAndTimeInformation"
    private DatePicker dateAndTimeInformation; // Value injected by FXMLLoader

    @FXML // fx:id="hoursInformation"
    private ComboBox<String> hoursInformation; // Value injected by FXMLLoader

    @FXML // fx:id="minutesInformation"
    private ComboBox<String> minutesInformation; // Value injected by FXMLLoader

    @FXML // fx:id="placeInformation"
    private TextField placeInformation; // Value injected by FXMLLoader

    @FXML // fx:id="categoryInformation"
    private ComboBox<String> categoryInformation; // Value injected by FXMLLoader

    @FXML // fx:id="contactsInformation"
    private ComboBox<String> contactsInformation; // Value injected by FXMLLoader

    @FXML // fx:id="allSelectedContactsTextArea"
    private TextArea allSelectedContactsTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="commentInformation"
    private TextArea commentInformation; // Value injected by FXMLLoader




    //Table variables start from here
    @FXML // fx:id="meetingTable"
    private TableView<MeetingData> meetingTable; // Value injected by FXMLLoader

    @FXML // fx:id="dateAntTimeColumn"
    private TableColumn<MeetingData, String> dateAntTimeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="placeColumn"
    private TableColumn<MeetingData, String> placeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="categoryColumn"
    private TableColumn<MeetingData, String> categoryColumn; // Value injected by FXMLLoader

    @FXML // fx:id="contactsColumn"
    private TableColumn<MeetingData, String> contactsColumn; // Value injected by FXMLLoader

    @FXML // fx:id="commentsColumn"
    private TableColumn<MeetingData, String> commentsColumn; // Value injected by FXMLLoader

    @FXML // Add more contactsColumn to contactsColumn list
    void addContactToAllContactsTextArea(ActionEvent event) {
        // get selected contact from contact list
        String selectedContactToAdd = this.contactsInformation.getValue();

        // get all selected contacts
        String textArea = this.allSelectedContactsTextArea.getText();

        // check if contact that user wont to add is not added in list with selected contacts
        if (textArea.contains(selectedContactToAdd)) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката.",
                    "Вече сте добавили този контакт. Моля опитайте отново.");
        } else { // add selected contact to list with selected contacts
            textArea = textArea.concat(selectedContactToAdd).concat("\n");
            this.allSelectedContactsTextArea.setText(textArea);
        }

    }

    @FXML
    void clearAllSelectedContacts(ActionEvent event) {
        this.allSelectedContactsTextArea.clear();
    }

    @FXML
    void clearSelectedCategory(ActionEvent event) {
        //clear category combo box
        this.categoryInformation.getItems().clear();
        // load category list
        this.categoryInformation.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));
    }

    @FXML //get exit event and replace all meetings from file with new meetings from observable list - data
    void editDataMeetingInFileAndExitFromPopUp(ActionEvent event) {
        // clear meetings file
        try {
            PrintWriter writer = new PrintWriter(PATH_TO_MEETING_LIST_FILE);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            // TODO: 03-Feb-17 finish exception handling
        }

        //write new data in meetings file
        for (MeetingData meetingData : data) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TO_MEETING_LIST_FILE, true))) {
                String content = "[".concat(meetingData.getDate()).concat("] [")
                        .concat(meetingData.getPlace()).concat("] [")
                        .concat(meetingData.getCategory()).concat("] [")
                        .concat(meetingData.getContacts()).concat("] [")
                        .concat(meetingData.getComments()).concat("]");
                bw.write(content.concat("\n"));
//            bw.write(separatorRegex);
            } catch (IOException e) {
                e.printStackTrace();
                // TODO: 23-Jan-17 finish exception handling
            }
        }
        new Display().closePopUpScene(event);
    }

    @FXML
    void editMeeting(ActionEvent event) {
        // get index of selected row
        int selectedRowIndex = this.meetingTable.getSelectionModel().getFocusedIndex();

        try {
            //Create date and time string in format "yyyy-MM-dd HH:mm"
            String date = this.dateAndTimeInformation.getValue().toString(); // 2017-01-20 yyyy-MM-dd
            String hour = this.hoursInformation.getValue(); // 00-23
            String minutes = this.minutesInformation.getValue(); // 00-59
            String fullDate = date.concat(" ").concat(hour).concat(":").concat(minutes);

            // place CAN be empty
            String place =
                    new DataValidation().isTextFieldEmpty(this.placeInformation) ? " " : this.placeInformation.getText();


            // category CAN be empty
            String category = this.categoryInformation.getValue() != null ? this.categoryInformation.getValue() : "";

            // every contact are separate by "\n"
            String contacts = this.allSelectedContactsTextArea.getText().replaceAll("\n",",");

            // probably contain "\n"
            String comment =
                    new DataValidation().isTextFieldEmpty(this.commentInformation) ? " " : this.commentInformation
                            .getText();

            // create new instance with new values of meeting
            MeetingData meetingData = new MeetingData(fullDate, place, category, contacts, comment.replaceAll("\n", " "));

            // remove old meeting data from observable list
            this.data.remove(selectedRowIndex);

            // add new meeting data in observable list
            this.data.add(meetingData);

            // clear table view
            this.meetingTable.getSelectionModel().clearSelection();

            // load new values in table view
            loadTable(this.data);
        } catch (NullPointerException npe) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката.",
                    "Моля селектирайте поне един контакт и го редактирайте.");
        }

    }

    @FXML
    void loadInformationForMeeting(ActionEvent event) {
        // take all data from selected meeting
        MeetingData meetingInfo = this.meetingTable.getSelectionModel().getSelectedItem();

        // Before load check if anything is selected
        if (meetingInfo == null) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката",
                    "Не сте селектирали среща, която искате да редактирате. Моля опитайте отново."
            );
        } else {
            // load date of meeting
            String date = meetingInfo.getDate().substring(0, 10);
            this.dateAndTimeInformation.setValue(LocalDate.parse(date));

            // Load hours in combo box
            this.hoursInformation.setValue(meetingInfo.getDate().substring(11, 13));

            //Load minutes in combo box
            this.minutesInformation.setValue(meetingInfo.getDate().substring(14, 16));

            //Load place
            if (meetingInfo.getPlace().trim().length() > 0) {
                this.placeInformation.setText(meetingInfo.getPlace());
            } else {
                this.placeInformation.clear();
            }

            //Load category
            if (meetingInfo.getCategory().length() > 1) {
                this.categoryInformation.setValue(meetingInfo.getCategory());
            }

            //Load contacts
            String contactsList = meetingInfo.getContacts().replaceAll(",", "\n");
            this.allSelectedContactsTextArea.setText(contactsList);

            //Set contacts combo box value to first contact from list
            Pattern pattern = Pattern.compile("^(.*?)\\n");
            Matcher matcher = pattern.matcher(contactsList);
            if (matcher.find()) {
                this.contactsInformation.setValue(matcher.group(1));
            }

            //Load comment
            this.commentInformation.setText(meetingInfo.getComments());
        }
    }

    void loadTable(ObservableList<MeetingData> data) {
        // set columns
        this.dateAntTimeColumn.setCellValueFactory(new PropertyValueFactory<MeetingData, String>("date"));
        this.placeColumn.setCellValueFactory(new PropertyValueFactory<MeetingData, String>("place"));
        this.categoryColumn.setCellValueFactory(new PropertyValueFactory<MeetingData, String>("category"));
        this.contactsColumn.setCellValueFactory(new PropertyValueFactory<MeetingData, String>("contacts"));
        this.commentsColumn.setCellValueFactory(new PropertyValueFactory<MeetingData, String>("comments"));
        // set observable list in table
        this.meetingTable.setItems(data);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dateAndTimeInformation != null : "fx:id=\"dateAndTimeInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert hoursInformation != null : "fx:id=\"hoursInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert minutesInformation != null : "fx:id=\"minutesInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert placeInformation != null : "fx:id=\"placeInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert categoryInformation != null : "fx:id=\"categoryInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert contactsInformation != null : "fx:id=\"contactsInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert allSelectedContactsTextArea != null : "fx:id=\"allSelectedContactsTextArea\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert commentInformation != null : "fx:id=\"commentInformation\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert meetingTable != null : "fx:id=\"meetingTable\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert dateAntTimeColumn != null : "fx:id=\"dateAntTimeColumn\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert placeColumn != null : "fx:id=\"placeColumn\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert categoryColumn != null : "fx:id=\"categoryColumn\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert contactsColumn != null : "fx:id=\"contactsColumn\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";
        assert commentsColumn != null : "fx:id=\"commentsColumn\" was not injected: check your FXML file 'EditMeetingPopUp.fxml'.";

        // load variable data with all meetings with initialization of pop-up
        this.data = new MeetingLoader().loadObservableListWithMeetings();

        // load all meetings in table
        loadTable(this.data);

        // load contact list
        this.contactsInformation.getItems().addAll(new LoadContacts().loadAllNamesAndIDsFromFile());

        // load category list
        this.categoryInformation.getItems().addAll(
                new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));

        // load hours comboBox
        this.hoursInformation.getItems().addAll(new MeetingLoader().loadMinutesOrHoursValues(0, 23));

        // load minutes comboBox
        this.minutesInformation.getItems().addAll(new MeetingLoader().loadMinutesOrHoursValues(0, 59));

    }

}
