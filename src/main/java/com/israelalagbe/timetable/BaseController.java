/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author User
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
    public abstract void loaded() throws Exception;
}
