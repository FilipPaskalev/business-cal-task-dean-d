package application.meetings;

public class Meeting {

    // Fields
    private String date;
    private String hours;
    private String minutes;
    private String place;
    private String category;
    private String contacts;
    private String comment;

    //Constructors
    public Meeting() {

    }

    public Meeting(String date, String hours, String minutes, String place, String category, String contacts, String
            comment) {
        this.date = date;
        this.hours = hours;
        this.minutes = minutes;
        this.place = place;
        this.category = category;
        this.contacts = contacts;
        this.comment = comment;
    }

    // Methods
    public void createMeeting(Meeting meeting) {
        String content = "[";
        String date = this.date.concat(" ").concat(this.hours).concat(":").concat(minutes);
        content = content.concat(date).concat("] [").concat(place).concat("] [").concat(category).concat("] [");
        content = content.concat(this.contacts.replaceAll("\n", ",")).concat("] [");
        content = content.concat(this.comment.replaceAll("\n", " ")).concat("]");

        new AddMeeting().appendMeetingInFile(content);
    }
}
