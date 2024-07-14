package application.validation;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DataValidation {

    public boolean isNotLetter(Character ch) {
        if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= 'а' && ch <= 'я') || (ch >= 'А' && ch <= 'Я'))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNotDigit(Character ch) {
        if (!(ch >= '0' && ch <= '9')) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserNameOrPassFieldEmpty(TextField textField, PasswordField passwordField) {
        if ((textField.getText() == null || textField.getText().trim().isEmpty()) ||
                (passwordField.getText() == null || passwordField.getText().trim().isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: 23-Jan-17 finish description
    /**
     *
     * @param textField
     * @return
     */
    public Boolean isTextFieldEmpty(TextField textField) {
        Boolean valid = false;

        if (textField.getText() == null || textField.getText().trim().isEmpty()) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

    // TODO: 23-Jan-17 finish description
    public Boolean isTextFieldEmpty(TextArea textArea) {
        Boolean valid = false;

        if (textArea.getText() == null || textArea.getText().trim().isEmpty()) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

    public Boolean isDatePickerEmpty(DatePicker datePicker) {
        Boolean valid = false;

        if (datePicker.getValue() == null) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

}