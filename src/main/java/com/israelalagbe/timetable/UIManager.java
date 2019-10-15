package com.israelalagbe.timetable;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 *
 * @author Israel Alagbe
 */
public class UIManager {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static void addRequiredValidator(final JFXComboBox validationField,String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required");
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                validationField.validate();
            }
           
        });
    }
    public static void addRequiredValidator(final JFXTextField validationField,String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required");
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                validationField.validate();
            }
           
        });
    }
    public static void addRequiredValidator(final JFXTimePicker validationField,String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required");
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                validationField.validate();
            }
           
        });
    }
    public static void addRequiredValidator(final JFXPasswordField validationField,String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required");
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                validationField.validate();
            }
           
        });
    }
    public static void addNumberValidator(final JFXTextField validationField,String message){
        NumberValidator validator = new NumberValidator();
        validator.setMessage("Should be a number");
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                validationField.validate();
            }
           
        });
    }
    
}
