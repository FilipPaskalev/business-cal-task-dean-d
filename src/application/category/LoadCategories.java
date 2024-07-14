package application.category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class LoadCategories {

    // Fields

    //Methods

    // TODO: 23-Jan-17 finish description
    /**
     *
     * @param pathToFile
     * @return
     * @throws IOException
     */
    private String loadAllFromTextFileInString(String pathToFile) throws IOException {
        String content;
        content = new String(Files.readAllBytes(Paths.get(pathToFile)));
        return content;
    }

    // TODO: 23-Jan-17 finish description of method
    /**
     *
     * @param pathToFile
     * @param splitRegex
     * @return
     */
    public ArrayList<String> loadArrayListFromString(String pathToFile, String splitRegex) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        try {
            // split string by new line
            String[] content = loadAllFromTextFileInString(pathToFile).split(splitRegex);
            Collections.addAll(stringArrayList, content);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // TODO: 23-Jan-17 finish exception
        }

        return stringArrayList;
    }


}
