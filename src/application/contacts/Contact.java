package application.contacts;

import java.util.ArrayList;

public class Contact {

    // Fields
    private final String PATH_TO_CONTACTS_FILE = "src/application/database/contacts/ContactsList";

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String eMail;
    private String address;
    private String note;

    private ArrayList<String> contactInformation = new ArrayList<>();

    // Constructors
    public Contact(String firstName, String lastName, String dateOfBirth, String phoneNumber, String eMail, String
            address, String note) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.address = address;
        this.note = note;
    }

    // Getters

    private String getFirstName() {
        return firstName;
    }
    private String getLastName() {
        return lastName;
    }
    private String getDateOfBirth() {
        return dateOfBirth;
    }
    private String getPhoneNumber() {
        return phoneNumber;
    }
    private String getEMail() {
        return eMail;
    }
    private String getAddress() {
        return address;
    }
    private String getNote() {
        return note;
    }

    // Methods

    public void appendNewContactInList(Contact contact) {
        this.contactInformation.add(contact.getFirstName());
        this.contactInformation.add(contact.getLastName());
        this.contactInformation.add(contact.getDateOfBirth());
        this.contactInformation.add(contact.getPhoneNumber());
        this.contactInformation.add(contact.getEMail());
        this.contactInformation.add(contact.getAddress());
        this.contactInformation.add(contact.getNote());

        new ContactFileWriter().appendNewContactInFile(this.contactInformation);
    }



}
