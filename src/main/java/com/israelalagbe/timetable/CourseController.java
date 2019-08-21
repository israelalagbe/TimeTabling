/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import com.israelalagbe.timetable.models.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseController extends BaseController {

    

    @FXML
    private Label label;

    @FXML
    private JFXTextField courseName;

    @FXML
    private JFXTextField courseCode;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<Course> coursesTable;
    private ObservableList<Course> coursesObservable = null;
    
    
    @FXML
    public void saveCourse(ActionEvent event) {
        //courseCode.getText(), courseName.getText();
        Course course=new Course();
        course.setName(courseName.getText());
        course.setCode(courseCode.getText());
        if(course.getCode().isEmpty()||course.getName().isEmpty()){
            UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Don't leave any input unfilled" );
            return;
        }
        mainApp.dataService.addCourse(course);
        UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Course Saved" );
        coursesObservable.setAll(mainApp.dataService.getCourses());
        // System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UIManager.addRequiredValidator(courseName, "Course name is required");
        UIManager.addRequiredValidator(courseCode, "Course code is required");
       
    }
    @Override
    public void loaded() throws Exception{
        coursesObservable=FXCollections.observableArrayList(mainApp.dataService.getCourses());
        coursesTable.setItems(coursesObservable);
        TableColumn<Course, String> nameCol = new TableColumn<>("Course Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Course, String>("name"));
        TableColumn<Course, String> codeCol = new TableColumn<>("Course Code");
        codeCol.setCellValueFactory(
                new PropertyValueFactory<Course, String>("code"));
        coursesTable.getColumns().addAll(nameCol,codeCol);
    }
}