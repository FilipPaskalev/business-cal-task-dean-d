package application.category;

import application.user.Register;

public class AddNewCategory {

    // Fields
    private static final String PATH_TO_CATEGORY_FILE = "src/application/database/category/CategoryList";
    private String newCategoryName;

    // Constructors
    public AddNewCategory(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }

    // Methods
    public void addCategoryToFileList() {
        new Register().appendNewLineWithContentInFile(newCategoryName, PATH_TO_CATEGORY_FILE, "\n");
    }
}
