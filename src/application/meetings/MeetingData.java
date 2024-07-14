package application.meetings;

import javafx.beans.property.SimpleStringProperty;

public class MeetingData {

    //Fields
    private SimpleStringProperty date = new SimpleStringProperty("");
    private SimpleStringProperty place = new SimpleStringProperty("");
    private SimpleStringProperty category = new SimpleStringProperty("");
    private SimpleStringProperty contacts = new SimpleStringProperty("");
    private SimpleStringProperty comments = new SimpleStringProperty("");

    //Constructors
    public MeetingData() {
        this("", "", "", "", "");
    }

    public MeetingData(String date, String place, String category, String contacts, String comments) {
        setDate(date);
        setPlace(place);
        setCategory(category);
        setContacts(contacts);
        setComments(comments);
    }

    //Setters
    public void setDate(String theDate) {
        date.set(theDate);
    }

    public void setPlace(String thePlace) {
        place.set(thePlace);
    }

    public void setCategory(String theCategory) {
        category.set(theCategory);
    }

    public void setContacts(String theContacts) {
        contacts.set(theContacts);
    }

    public void setComments(String theComments) {
        comments.set(theComments);
    }

    //Getters
    public String getDate() {
        return date.get();
    }

    public String getPlace() {
        return place.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getContacts() {
        return contacts.get();
    }

    public String getComments() {
        return comments.get();
    }

}
