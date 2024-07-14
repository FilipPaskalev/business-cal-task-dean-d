package application.controllers;

import application.category.AddNewCategory;
import application.category.LoadCategories;
import application.display.Display;
import application.validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCategoryPopUpController {

    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private static final String regexNewLine = "\\r?\\n";

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="category"
    private TextField category; // Value injected by FXMLLoader

    @FXML
    void addNewCategory(ActionEvent event) {
        // load all categories in array
        ArrayList<String> listWithCategories = new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine);

        // create string with value from TextField
        String nameOfCategory = new DataValidation().isTextFieldEmpty(this.category) ? "" : this.category.getText();

        // check if category field text box is empty
        if (new DataValidation().isTextFieldEmpty(this.category)) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката.",
                    "Полети име на категорията е празно! Моля попълнете го и опитайте отново."
            );
        } else {
            // check if category list contain name of new category
            if (listWithCategories.contains(nameOfCategory)) {
                new Display().infoPopUpWithHeader(
                        "Грешка",
                        "Информация за грешката.",
                        "Името на категорията, вече съществува, моля опитайте с ново име."
                );
                this.category.clear();
            } else {
                // addCategoryToFileList ne category
                AddNewCategory addNewCategory = new AddNewCategory(nameOfCategory);
                addNewCategory.addCategoryToFileList();
                this.category.clear();
            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'AddNewCategoryPopUp.fxml'.";

    }
}
