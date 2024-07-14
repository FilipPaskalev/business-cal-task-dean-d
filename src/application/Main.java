package application;

import application.display.Display;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String PATH_TO_MAIN_FXML_FILE = "/application/fxmlFiles/Main.fxml";
    private Display display = new Display();

    @Override
    public void start(Stage primaryStage) throws Exception{
        display.setPathToFxmlFile(PATH_TO_MAIN_FXML_FILE);
        display.setStage(primaryStage);
        display.setStageTitle("Business calendar version 0.04");
        display.startProgram();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
