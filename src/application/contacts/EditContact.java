package application.contacts;

import application.category.LoadCategories;
import application.display.Display;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EditContact {

    // Fields
    private final String PATH_TO_CONTACTS_FILE = "src/application/database/contacts/ContactsList";

    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String note;

    // Constructor
    public EditContact(String id, String firstName, String lastName, String dateOfBirth, String phoneNumber, String
            email, String address, String note) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.note = note;
    }

    // Methods
    public void editSelectedContact() throws FileNotFoundException {

        new Display().infoPopUpWithHeader(
                "Информация",
                "Контакта беше редактиран.",
                "За да редактирате друг контакт моля отворете отново Контакти -> Редактирай от главното меню."
        );

        // load list with all contacts
        ArrayList<String> contactList = new LoadCategories().loadArrayListFromString(PATH_TO_CONTACTS_FILE, "\n");

        // Create new value
        String newContactValue = "[".concat(id).concat("]")
                .concat("[").concat(this.firstName).concat("]")
                .concat("[").concat(this.lastName).concat("]")
                .concat("[").concat(this.dateOfBirth).concat("]")
                .concat("[").concat(this.phoneNumber).concat("]")
                .concat("[").concat(this.email).concat("]")
                .concat("[").concat(this.address).concat("]")
                .concat("[").concat(this.note).concat("]");

        // replace old contact values with new contact values
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).contains(this.id)) {
                // add new values
                contactList.add(i, newContactValue);
                // remove next element in array which is old values of contact
                contactList.remove(i+1);
                break;
            }
        }

        // write new value in file
        new ContactFileWriter().writeNewContactList(contactList);
    }
}
