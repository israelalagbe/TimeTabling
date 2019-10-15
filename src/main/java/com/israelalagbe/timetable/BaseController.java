package com.israelalagbe.timetable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Israel Alagbe
 */
public abstract class BaseController implements Initializable{
    protected MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public MainApp getMainApp() {
        return mainApp;
    }
    @FXML
    public  void showDepartments(ActionEvent event) {
        mainApp.navigate("departments");
    }
    @FXML
    public  void showCourses(ActionEvent event) {
        mainApp.navigate("courses");
    }
    @FXML
    public  void showLecturers(ActionEvent event) {
        mainApp.navigate("lecturers");
    }
    @FXML
    public  void showDepartmentalCourses(ActionEvent event) {
        mainApp.navigate("departmental_courses");
    }
    @FXML
    public  void showTimetables(ActionEvent event) {
        mainApp.navigate("timetables");
    }
    @FXML
    public  void showLogin(ActionEvent event) {
        mainApp.navigate("login");
    }
    @FXML
    public  void displayTimtable(ActionEvent event) {
        mainApp.navigate("show_timetable");
    }
    public abstract void loaded() throws Exception;
}
