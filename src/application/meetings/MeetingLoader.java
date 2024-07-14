package application.meetings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MeetingLoader {

    private static final String PATH_TO_MEETING_LIST_FILE = "src/application/database/meetings/Events";

    private List<MeetingData> loadMeetingsFromFile(){
        List<MeetingData> data = new ArrayList<>();
        String[] info;

        // Open the file
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_MEETING_LIST_FILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String currentLine;
            //Read File Line By Line
            while ((currentLine = br.readLine()) != null) {
                info = currentLine.split("\\] \\[");
                info[0] = info[0].replaceFirst("\\[", "");
                String[] contacts = info[3].split(",");
                if (contacts.length <= 1) {
                    info[3] = info[3].replaceFirst(",", "");
                }
                info[4] = info[4].substring(0, info[4].length() - 1);
                data.add(new MeetingData(info[0], info[1], info[2], info[3], info[4]));
            }
            //Close the input stream
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 25-Jan-17 finish exception
        }

        return data;
    }

    // get all meetings
    public ObservableList<MeetingData> loadObservableListWithMeetings() {
        ObservableList<MeetingData> data = FXCollections.observableArrayList(loadMeetingsFromFile());
        return data;
    }

    // TODO: 29-Jan-17 finish description of method
    /**
     *
     * @param minValue
     * @param maxValue
     * @return <p>ArrayList<String> with values between minValue & maxValue.</p>
     * <p>If value is smaller than 10 return value with 0 before it</p>
     * <p>Example if value is 9 return 09</p>
     * <p>Example if value is 4 return 04</p>
     */
    public ArrayList<String> loadMinutesOrHoursValues(Integer minValue, Integer maxValue) {
        ArrayList<String> arrWithValues = new ArrayList<>();

        for (int i = minValue; i <= maxValue; i++) {
            String time;
            if (i < 10) {
                time = "0".concat(Integer.toString(i));
            } else {
                time = Integer.toString(i);
            }
            arrWithValues.add(i, time);
        }

        return arrWithValues;
    }

}
