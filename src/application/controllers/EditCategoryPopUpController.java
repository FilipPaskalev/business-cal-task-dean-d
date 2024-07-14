package application.controllers;

import application.category.EditCategory;
import application.category.LoadCategories;
import application.validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCategoryPopUpController {

    // Fields
    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private static final String regexNewLine = "\\r?\\n";

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="categoryList"
    private ComboBox<String> categoryList; // Value injected by FXMLLoader

    @FXML // fx:id="category"
    private TextField category; // Value injected by FXMLLoader

    @FXML
    void setValueInTextField(ActionEvent event) {
        this.category.setText(categoryList.getValue());
    }

    @FXML
    void editCategoryAction(ActionEvent event) {
        // take validated value from TextField
        String newCategoryName = new DataValidation().isTextFieldEmpty(this.category) ? "" : this.category.getText();

        // take validated value from ComboBox
        String oldCategoryName = this.categoryList.getValue() == null ? null : this.categoryList.getValue();

        // edit category
        EditCategory editCategory = new EditCategory(oldCategoryName, newCategoryName);
        editCategory.edit();

        // clear combo box
        this.categoryList.getItems().clear();

        // load new combo box values
        this.categoryList.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert categoryList != null : "fx:id=\"categoryList\" was not injected: check your FXML file 'EditCategoryPopUp.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'EditCategoryPopUp.fxml'.";

        // load category list
        this.categoryList.getItems().addAll(new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine));
    }
}
