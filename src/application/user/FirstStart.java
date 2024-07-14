package application.user;

import java.io.File;

public class FirstStart {
    //Fields
    private static final String PATH_TO_USER_NAME_FILE = "src/application/database/userInformation/UserName.txt";

    //Methods
    public boolean checkUser() {
        File file = new File(PATH_TO_USER_NAME_FILE);
        if (file.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
