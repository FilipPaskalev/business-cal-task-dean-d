package application.controllers;

import application.category.LoadCategories;
import application.contacts.LoadContacts;
import application.display.Display;
import application.meetings.Meeting;
import application.meetings.MeetingLoader;
import application.validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddNewMeetingPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dateOfMeeting"
    private DatePicker dateOfMeeting; // Value injected by FXMLLoader

    @FXML // fx:id="meetingHour"
    private ComboBox<String> meetingHour; // Value injected by FXMLLoader

    @FXML // fx:id="meetingMinutes"
    private ComboBox<String> meetingMinutes; // Value injected by FXMLLoader

    @FXML // fx:id="placeOfMeeting"
    private TextField placeOfMeeting; // Value injected by FXMLLoader

    @FXML // fx:id="categoryList"
    private ComboBox<String> categoryList; // Value injected by FXMLLoader

    @FXML // fx:id="contactList"
    private ComboBox<String> contactList; // Value injected by FXMLLoader

    @FXML // fx:id="allSelectedContactsTextArea"
    private TextArea allSelectedContactsTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="commentTextArea"
    private TextArea commentTextArea; // Value injected by FXMLLoader

    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private static final String regexNewLine = "\\r?\\n";

    @FXML
    void addContactToAllContactsTextArea(ActionEvent event) {
        // get selected contact from contact list
        String selectedContactToAdd = this.contactList.getValue();

        // get all selected contacts
        String textArea = this.allSelectedContactsTextArea.getText();

        // check if contact that user wont to add is not added in list with selected contacts
        if (textArea.contains(selectedContactToAdd)) {
            callInfoPopUpForUnsupportedField("Вече сте добавили този контакт. Моля опитайте отново.");
        } else { // add selected contact to list with selected contacts
            textArea = textArea.concat(selectedContactToAdd).concat("\n");
            this.allSelectedContactsTextArea.setText(textArea);
        }

    }

    @FXML
    void addMeeting(ActionEvent event) {
        DataValidation check = new DataValidation();

        // check if date is empty
        if (check.isDatePickerEmpty(this.dateOfMeeting)) {
            callInfoPopUpForUnsupportedField("Полето дата на срещата трябва да бъде попълнено.");
        } else {
            boolean isHoursSelected = this.meetingHour.getSelectionModel().isEmpty();
            // check is hours is filled
            if (isHoursSelected) {
                callInfoPopUpForUnsupportedField("Трябва задължително да изберете час на срещата.");
            } else {
                boolean isMinutesSelected = this.meetingMinutes.getSelectionModel().isEmpty();
                // check is minutes are filled
                if (isMinutesSelected) {
                    callInfoPopUpForUnsupportedField("Трябва задължително отбележите час и минути на срещата.");
                } else {
                    // check is at least one contact is selected
                    if (check.isTextFieldEmpty(this.allSelectedContactsTextArea)) {
                        callInfoPopUpForUnsupportedField("Трябва да изберете поне един контакт, за да продължите.");
                    } else {
                        // check if comment is empty
                        if (check.isTextFieldEmpty(this.commentTextArea)) {
                            callInfoPopUpForUnsupportedField("Трябва да попълните полето \"Коментар\", за да продължите.");
                        } else {
                            // check if date and time is smaller from today date and now(HH:mm)
                            try {
                                if (isSelectedDateIsSmallerThanToday(this.dateOfMeeting.getValue().toString(),this.meetingHour.getValue(),this.meetingMinutes.getValue())) {
                                    // TODO: 29-Jan-17 clear date, hours and minutes field
                                } else {

                                    String date = this.dateOfMeeting.getValue().toString(); // 2017-01-20 yyyy-MM-dd
                                    String hour = this.meetingHour.getValue(); // 00-23
                                    String minutes = this.meetingMinutes.getValue(); // 00-59

                                    // place CAN be empty
                                    String place = new DataValidation().isTextFieldEmpty(this.placeOfMeeting) ? " " : this.placeOfMeeting.getText();

                                    // category CAN be empty
                                    String category = this.categoryList.getValue() != null ? this.categoryList.getValue() : "";

                                    // every contact are separate by "\n"
                                    String contacts = this.allSelectedContactsTextArea.getText();

                                    // probably contain "\n"
                                    String comment = new DataValidation().isTextFieldEmpty(this.commentTextArea) ? " " : this.commentTextArea.getText();

                                    Meeting meeting = new Meeting(date, hour, minutes, place, category, contacts, comment);
                                    meeting.createMeeting(meeting);

                                    new Display().closePopUpScene(event);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }

    @FXML
    void reloadCategoryList(ActionEvent event) {
        //clear category combo box
        this.categoryList.getItems().clear();
        // load category list
        this.categoryList.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));
    }

    // TODO: 29-Jan-17 finish description
    /**
     *
     * @param selectedDateFromDatePicker is a string in format "yyyy-MM-dd"
     * @param hours String taken from hours comboBox
     * @param minutes String taken from minutes comboBox
     * @return
     * <p>true if selected date and time is before today and time is before now</p>
     * <p>false if selected date is tomorrow, OR selected date is today but time is after now</p>
     * @throws ParseException
     */
    private boolean isSelectedDateIsSmallerThanToday(String selectedDateFromDatePicker, String hours, String minutes)
            throws ParseException { // date format is yyyy-MM-dd
        boolean answer = false;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // selected date
        Date date1 = sdf.parse(selectedDateFromDatePicker);

        // date now
        Date date2 = sdf.parse(dtf.format(now));

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        if (cal1.before(cal2)) {
            // selected date is before today
            callInfoPopUpForUnsupportedField("Въвели сте грешна дата. Датата, която трябва да въведете, трябва да е не по-малка от днешната. Моля опитайте отново.");
            return true;
        }

        if (cal1.after(cal2)) {
            // selected date is after today
            answer = false;
        }

        if (cal1.equals(cal2)) {
            // check if hours and minutes are min NOW
            if (compareTime(hours, minutes)) {
                answer = false;
            } else {
                callInfoPopUpForUnsupportedField("Въвели сте грешен час. Моля променете го и опитайте отново.");
                answer = true;
            }
        }

        return answer;
    }

    /**
     *
     * @param hours
     * @param minutes
     * @return <p>true if time is after now, false if before now</p>
     */
    private boolean compareTime(String hours, String minutes) {
        boolean selectedTimeIfBeforeNow = false;

        Calendar cal = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String timeNow = sdf.format(cal.getTime());

        Integer hourNow = Integer.parseInt(timeNow.substring(0, 2));
        Integer minutesNow = Integer.parseInt(timeNow.substring(3, 5));

        Integer hourSelected = Integer.parseInt(hours);
        Integer minutesSelected = Integer.parseInt(minutes);

        if (hourSelected > hourNow) {
            // if hour is bigger than time now return true and break
            return true;
        } else {
            if (hourSelected < hourNow) {
                selectedTimeIfBeforeNow = false;
            } else {
                if (minutesSelected <= minutesNow) {
                    selectedTimeIfBeforeNow = false;
                } else {
                    selectedTimeIfBeforeNow = true;
                }
            }
        }

        return selectedTimeIfBeforeNow;
    }

    // TODO: 29-Jan-17 finish description of method
    /**
     *
     * @param message
     */
    private void callInfoPopUpForUnsupportedField(String message) {
        new Display().infoPopUpWithHeader("Грешка", "Информация за грешката", message);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dateOfMeeting != null : "fx:id=\"dateOfMeeting\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert meetingHour != null : "fx:id=\"meetingHour\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert meetingMinutes != null : "fx:id=\"meetingMinutes\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert placeOfMeeting != null : "fx:id=\"placeOfMeeting\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert categoryList != null : "fx:id=\"categoryList\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert contactList != null : "fx:id=\"contactList\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert allSelectedContactsTextArea != null : "fx:id=\"allSelectedContactsTextArea\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";
        assert commentTextArea != null : "fx:id=\"commentTextArea\" was not injected: check your FXML file 'AddNewMeetingPopUp.fxml'.";

        // load contact list
        this.contactList.getItems().addAll(new LoadContacts().loadAllNamesAndIDsFromFile());

        // load category list
        this.categoryList.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));

        // load hours comboBox
        this.meetingHour.getItems().addAll(new MeetingLoader().loadMinutesOrHoursValues(0, 23));

        // load minutes comboBox
        this.meetingMinutes.getItems().addAll(new MeetingLoader().loadMinutesOrHoursValues(0, 59));

    }
}
