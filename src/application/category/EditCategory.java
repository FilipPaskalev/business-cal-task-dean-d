package application.category;

import application.display.Display;
import application.user.Register;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EditCategory {

    // Fields
    private ArrayList<String> categoryList;
    private String oldCategoryNameThatWillBeEdit;
    private String newCategoryNameThatWillBeEdit;
    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private static final String regexNewLine = "\\r?\\n";

    public EditCategory(String oldCategoryNameThatWillBeEdit, String newCategoryNameThatWillBeEdit) {
        this.oldCategoryNameThatWillBeEdit = oldCategoryNameThatWillBeEdit;
        this.newCategoryNameThatWillBeEdit = newCategoryNameThatWillBeEdit;
    }

    public void edit() {
        // load all categories in this.categoryList
        this.categoryList = new LoadCategories().loadArrayListFromString(PATH_TO_CATEGORY_FILE, regexNewLine);

        // check if value from comboBox is selected and TextField is NOT empty
        if (this.oldCategoryNameThatWillBeEdit == null) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката.",
                    "Не сте маркирали категория , която искате да редактирате, моля изберете категория и опитайте отново."
            );
        }

        // check if value from TextField is NOT empty
        if (this.newCategoryNameThatWillBeEdit.isEmpty()) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката.",
                    "Полето с редактирано име на категорията е празно, моля опитайте отново."
            );
        }

        // check if ArrayList contains newCategoryName
        if (this.categoryList.contains(this.newCategoryNameThatWillBeEdit)) {
            new Display().infoPopUpWithHeader(
                    "Грешка",
                    "Информация за грешката.",
                    "Въвели сте име, което съществува! Моля опитайте отново."
            );
        }

        // remove oldCategoryName from ArrayList
        this.categoryList.remove(this.oldCategoryNameThatWillBeEdit);

        // add newCategoryName to ArrayList
        this.categoryList.add(this.newCategoryNameThatWillBeEdit);

        // empty category file
        try (PrintWriter writer = new PrintWriter(PATH_TO_CATEGORY_FILE)) {
            writer.print("");
            writer.close();
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
            // TODO: 23-Jan-17 finish exception
        }

        // write all information from ArrayList in file
        Register writer = new Register();
        for (String s : categoryList) {
            writer.appendNewLineWithContentInFile(s, PATH_TO_CATEGORY_FILE, "\n");
        }

    }
}
