package application.contacts;

import application.user.Register;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactFileWriter {

    private final String PATH_TO_CONTACTS_FILE = "src/application/database/contacts/ContactsList";
    private Register register = new Register();
    private Integer contactId = 1;
    private String idFinderRegex = "(\\[Contact ID: [0-9]+\\])";

    // Setters

    private Integer getContactId() {
        try {
            Reader reader = new FileReader(PATH_TO_CONTACTS_FILE);
            int readSize = reader.read();
            if (readSize == -1) {
                contactId = 1;
                return contactId;
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_CONTACTS_FILE))) {
                    String sCurrentLine;
                    String lastLine = "";

                    while ((sCurrentLine = br.readLine()) != null) {
                        lastLine = sCurrentLine;
                    }

                    Pattern pattern = Pattern.compile(this.idFinderRegex);
                    Matcher matcher = pattern.matcher(lastLine);
                    if (matcher.find()) {
                        String idNumber = matcher.group().replaceAll("\\D","");
                        Integer lastId = Integer.parseInt(idNumber);
                        lastId++;
                        contactId = lastId;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO: 25-Jan-17 finish exception handling
                }
            }
        } catch (IOException e) {
            // TODO: 25-Jan-17 finish exception
            e.printStackTrace();
        }
        return contactId;
    }

    // Methods
    public void appendNewContactInFile(ArrayList<String> contactInformation) {

        Integer id = this.getContactId();
        String content = "[Contact ID: ".concat(Integer.toString(id)).concat("]");

        for (String s : contactInformation) {
            content = content.concat("[").concat(s).concat("]");
        }

        register.appendNewLineWithContentInFile(content, PATH_TO_CONTACTS_FILE, "\n");
    }

    public void writeNewContactList(ArrayList<String> contactList) throws FileNotFoundException {
        // clear all contacts from file
        emptyFile(PATH_TO_CONTACTS_FILE);

        // create line writer instance
        Register writer = new Register();

        // write all contacts in file
        for (String contactContent : contactList) {
            writer.appendNewLineWithContentInFile(contactContent, PATH_TO_CONTACTS_FILE, "\n");
        }
    }

    // TODO: 23-Jan-17 finish description
    /**
     *
     * @param pathToFile
     * @throws FileNotFoundException
     */
    private void emptyFile(String pathToFile) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(pathToFile);
        writer.print("");
        writer.close();
    }
}
