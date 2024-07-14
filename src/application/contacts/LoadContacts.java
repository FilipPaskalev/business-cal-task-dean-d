package application.contacts;

import java.io.*;
import java.util.ArrayList;

public class LoadContacts {

    private final String PATH_TO_CONTACTS_FILE = "src/application/database/contacts/ContactsList";

    private ArrayList<String> loadContactInfoFromFile(String pathToFile) {
        // Create empty array
        ArrayList<String> allContacts = new ArrayList<>();

        // Open the file
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
        //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // add row content to array
                allContacts.add(strLine);
            }

        //Close the input stream
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 25-Jan-17 finish exception
        }

        return allContacts;
    }

    public ArrayList<String> loadAllNamesAndIDsFromFile() {
        ArrayList<String> allInformation = loadContactInfoFromFile(PATH_TO_CONTACTS_FILE);
        ArrayList<String> idAndNames = new ArrayList<>();
        for (String info : allInformation) {
            String[] allInfo = info.split("\\]");
            String id = allInfo[0].replaceAll("\\D+", "");
            String firstName = allInfo[1].replaceFirst("\\[", "");
            String fullString = id.concat(" ").concat(firstName);
            if (!allInfo[2].equals("\\[")) {
                String lastName = allInfo[2].replaceFirst("\\[", "");
                fullString = fullString.concat(" ").concat(lastName);
            }
            idAndNames.add(fullString);
        }
        return idAndNames;
    }

    public ArrayList<String> loadInfoForContact(String contactIdAndNames) {
        ArrayList<String> allInfo = loadContactInfoFromFile(PATH_TO_CONTACTS_FILE);
        ArrayList<String> fullContactInformation = new ArrayList<>();
        contactIdAndNames = contactIdAndNames.replaceAll("\\D+", "");

        for (String information : allInfo) {
            String[] info = information.split("\\]");
            if (info[0].replaceAll("\\D+", "").equals(contactIdAndNames)) {
                for (int i = 0; i < info.length; i++) {
                    String s = info[i].replaceFirst("\\[", "");
                    if (s.length() <= 0) {
                        s = "";
                    }
                    fullContactInformation.add(s);
                }
                break;
            }
        }

        return fullContactInformation;
    }

}
