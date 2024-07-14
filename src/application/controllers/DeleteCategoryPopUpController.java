package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCategoryPopUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="categoryList"
    private ComboBox<?> categoryList; // Value injected by FXMLLoader

    @FXML
    void deleteCategory(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert categoryList != null : "fx:id=\"categoryList\" was not injected: check your FXML file 'DeleteCategoryPopUp.fxml'.";

    }
}
