package application.controllers;

import application.category.LoadCategories;
import application.display.Display;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    // Files
    private static final String PATH_TO_ADD_NEW_CATEGORY_FXML_FILE = "/application/fxmlFiles/AddNewCategoryPopUp.fxml";
    private static final String PATH_TO_EDIT_CATEGORY_FXML_FILE = "/application/fxmlFiles/EditCategoryPopUp.fxml";
    private static final String PATH_TO_DELETE_CATEGORY_FXML_FILE = "/application/fxmlFiles/DeleteCategoryPopUp.fxml";

    private static final String PATH_TO_ADD_NEW_CONTACT_FXML_FILE = "/application/fxmlFiles/AddNewContactPopUp.fxml";
    private static final String PATH_TO_EDIT_CONTACT_FXML_FILE = "/application/fxmlFiles/EditContactPopUp.fxml";
    private static final String PATH_TO_DELETE_CONTACT_FXML_FILE = "/application/fxmlFiles/DeleteContactPopUp.fxml";

    private static final String PATH_TO_ADD_NEW_MEETING_FXML_FILE = "/application/fxmlFiles/AddNewMeetingPopUp.fxml";
    private static final String PATH_TO_EDIT_MEETING_FXML_FILE = "/application/fxmlFiles/EditMeetingPopUp.fxml";
    private static final String PATH_DELETE_MEETING_FXML_FILE = "/application/fxmlFiles/DeleteMeetingPopUp.fxml";

    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private static final String regexNewLine = "\\r?\\n";

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="agenda"
    private Agenda agenda; // Value injected by FXMLLoader

    @FXML // fx:id="calendarBox"
    private CalendarPicker calendarBox; // Value injected by FXMLLoader

    @FXML // fx:id="startDate"
    private DatePicker startDate; // Value injected by FXMLLoader

    @FXML // fx:id="endDate"
    private DatePicker endDate; // Value injected by FXMLLoader

    @FXML // fx:id="categoryList"
    private ComboBox<String> categoryList; // Value injected by FXMLLoader

    @FXML // fx:id="firstName"
    private TextField firstName; // Value injected by FXMLLoader

    @FXML // fx:id="lastName"
    private TextField lastName; // Value injected by FXMLLoader

    @FXML
    void clearAllFieldsFromSearchBox(ActionEvent event) {
        this.startDate.getEditor().clear();
        this.endDate.getEditor().clear();
        this.categoryList.getItems().clear();
        this.categoryList.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));
        this.firstName.clear();
        this.lastName.clear();
    }

    @FXML
    void exitFromProgram(ActionEvent event) {
        new Display().confirmExit();
    }

    @FXML
    void openAddCategoryPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Добави нова категория");
        display.setPathToFxmlFile(PATH_TO_ADD_NEW_CATEGORY_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openAddContactPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Премахване на категория");
        display.setPathToFxmlFile(PATH_TO_ADD_NEW_CONTACT_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openAddMeetingPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Добавяне на среща");
        display.setPathToFxmlFile(PATH_TO_ADD_NEW_MEETING_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openDeleteCategoryPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Премахване на категория");
        display.setPathToFxmlFile(PATH_TO_DELETE_CATEGORY_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openDeleteContactPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Премахване на контакт");
        display.setPathToFxmlFile(PATH_TO_DELETE_CONTACT_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openDeleteMeetingPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Изтриване на среща");
        display.setPathToFxmlFile(PATH_DELETE_MEETING_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openEditCategoryPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Редактиране на категория");
        display.setPathToFxmlFile(PATH_TO_EDIT_CATEGORY_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openEditContactPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Редактиране на контакт");
        display.setPathToFxmlFile(PATH_TO_EDIT_CONTACT_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void openEditMeetingPopUp(ActionEvent event) {
        Display display = new Display();
        // set stage title and path to FXML file
        display.setStageTitle("Редактираме на среща");
        display.setPathToFxmlFile(PATH_TO_EDIT_MEETING_FXML_FILE);
        // display pop-up window
        display.displayDecoratedPopUp();
    }

    @FXML
    void searchForMeetingInDatabase(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert agenda != null : "fx:id=\"agenda\" was not injected: check your FXML file 'Main.fxml'.";
        assert calendarBox != null : "fx:id=\"calendarBox\" was not injected: check your FXML file 'Main.fxml'.";
        assert startDate != null : "fx:id=\"startDate\" was not injected: check your FXML file 'Main.fxml'.";
        assert endDate != null : "fx:id=\"endDate\" was not injected: check your FXML file 'Main.fxml'.";
        assert categoryList != null : "fx:id=\"categoryList\" was not injected: check your FXML file 'Main.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'Main.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'Main.fxml'.";

        // load category list
        this.categoryList.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));
    }
}
