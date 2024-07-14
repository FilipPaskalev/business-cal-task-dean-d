package application.display;

import application.user.FirstStart;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class Display {

    //Fields
    private static final String PATH_TO_LOGIN_FILE = "/application/fxmlFiles/LoginPopUp.fxml";
    private static final String PATH_TO_REGISTER_NEW_USER_FILE = "/application/fxmlFiles/RegisterNewUserPopUp.fxml";
    private static final String PATH_TO_INFO_POP_UP_FILE = "/application/fxmlFiles/InfoPopUp.fxml";

    private Stage stage;
    private String stageTitle;
    private String pathToFxmlFile;
    private Parent root;

    //Constructors

    //Setters
    public void setPathToFxmlFile(String pathToFxmlFile) {
        this.pathToFxmlFile = pathToFxmlFile;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    // Getters
    private String getPathToFxmlFile() {
        return pathToFxmlFile;
    }

    private Stage getStage() {
        return stage;
    }

    private String getStageTitle() {
        return stageTitle;
    }

    // Methods
    public void startProgram(){
        stage = getStage();
        try {
            root = FXMLLoader.load(getClass().getResource(getPathToFxmlFile()));
            stage.setTitle(getStageTitle());
            stage.setScene(new Scene(root));
            stage.show();

            // if program start fo first time start register user pop-up
            FirstStart firstStart = new FirstStart();
            if (firstStart.checkUser()) {
                startUserRegistration();
            } else {// else start login pop-up
                startLogin();
            }
        } catch (IOException ioe) {
            // TODO: 20-Jan-17 finish exception
        }
    }

    private void startLogin() {
        setPathToFxmlFile(PATH_TO_LOGIN_FILE);
        setStageTitle("Вход в програмата.");
        displayUndecoratedPopUp();
    }

    private void startUserRegistration() {
        setStageTitle("Регистарция");
        setPathToFxmlFile(PATH_TO_REGISTER_NEW_USER_FILE);
        displayUndecoratedPopUp();
    }

    public void exitFromProgram(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void closePopUpScene(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void infoPopUpWithHeader(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void displayUndecoratedPopUp() {
        Stage popUp = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(getPathToFxmlFile()));
            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.initStyle(StageStyle.UNDECORATED);
            popUp.setTitle(getStageTitle());
            popUp.setScene(new Scene(root));
            popUp.show();
        } catch (IOException ioe) {
            // TODO: 20-Jan-17 finish exception
        }
    }

    public void displayDecoratedPopUp() {
        Stage popUp = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(getPathToFxmlFile()));
            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.initStyle(StageStyle.UTILITY);
            popUp.setTitle(getStageTitle());
            popUp.setScene(new Scene(root));
            popUp.showAndWait();
        } catch (IOException ioe) {
            ioe.printStackTrace(); // TODO: 20-Jan-17 finish exception
        }
    }

    public void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation exit");
        alert.setHeaderText("Излизане от програмата!");
        alert.setContentText("Ще излезете от програмата. Наистина ли желаете това? Ако желаете да продължите, и излезете от програмата, моля потвърдете.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

}
