package com.israelalagbe.timetable;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 *
 * @author Israel Alagbe
 */
public class LoginController extends BaseController {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @Override
    public void loaded() throws Exception {
      
    }
    @FXML
    private void login(ActionEvent event) {
        if(username.getText().equals("admin")&&password.getText().equals("password")){
            showCourses(event);
            UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Login Success" );
        }
        else{
             UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Username or password incorrect" );
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UIManager.addRequiredValidator(username, "Required");
        UIManager.addRequiredValidator(password, "Required");
    }
    
}
