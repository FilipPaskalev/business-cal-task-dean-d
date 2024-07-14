package application.controllers;

import application.display.Display;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class MainControllerCOPY {
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


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    // TODO: 23-Jan-17 finish description
    @FXML //
    void exitFromProgram(ActionEvent event) {
        new Display().confirmExit();
    }

    // TODO: 23-Jan-17 finish description
    @FXML //
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

    // TODO: 23-Jan-17 finish description
    @FXML //
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
