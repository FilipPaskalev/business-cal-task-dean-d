package application.meetings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class AddMeeting {

    // Fields
    private static final String PATH_TO_MEETING_LIST_FILE = "src/application/database/meetings/Events";

    // Methods
    void appendMeetingInFile(String contentToAppend) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TO_MEETING_LIST_FILE, true))) {
            bw.write(contentToAppend.concat("\n"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // TODO: 23-Jan-17 finish exception handling
        }
    }

}
