/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 *
 * @author User
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
    public static void addRequiredValidator(final JFXTextField validationField,String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required");
//validator.setAwsomeIcon(new Icon(AwesomeIcon.WARNING,"2em",";","error"));
        validationField.getValidators().add(validator);
//        (o, oldVal, newVal) -> {
//            if (!newVal) {
//                validationField.validate();
//            }
//        }
        validationField.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                validationField.validate();
            }
           
        });
    }
}
