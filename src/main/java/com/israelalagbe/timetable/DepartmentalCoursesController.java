/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;
import com.israelalagbe.timetable.models.Course;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import com.israelalagbe.timetable.models.Department;
import com.israelalagbe.timetable.models.DepartmentalCourses;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.israelalagbe.timetable.models.Lecturer;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author User
 */
public class DepartmentalCoursesController extends BaseController{
    @FXML
    private TableView<DepartmentalCourses> table;
    
    @FXML
    private JFXComboBox<Department>  departments;
    @FXML
    private JFXComboBox<Course>  courses;
    @FXML
    private JFXComboBox<String> courseType;
    
    private ObservableList<Course> coursesList = null;
    private ObservableList<Department> departmentList = null;
    private ObservableList<DepartmentalCourses> lists = null;
     @FXML
    private void save(ActionEvent event) {
        Department selectedDepartment=departments.getSelectionModel().getSelectedItem();
        Course selectedCourse=courses.getSelectionModel().getSelectedItem();
        String selectedCourseType=courseType.getSelectionModel().getSelectedItem();
        DepartmentalCourses departmentalCourses=new DepartmentalCourses();
        departmentalCourses.setCourse(selectedCourse);
        departmentalCourses.setDepartment(selectedDepartment);
        departmentalCourses.setCourseType(selectedCourseType);
        
        if(departmentalCourses.getCourseType().isEmpty()||departmentalCourses.getCourse()==null||departmentalCourses.getDepartment()==null){
            UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Info cannot be empty" );
            return;
        }
        mainApp.dataService.saveModel(departmentalCourses);
        UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Department Course Saved" );
        try{
           lists.setAll(mainApp.dataService.getModels(new DepartmentalCourses()));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        courses.getSelectionModel().clearSelection();
        departments.getSelectionModel().clearSelection();
        courseType.getSelectionModel().clearSelection();
    }
     
    @Override
    public void loaded() throws Exception {
         departmentList=FXCollections.observableArrayList(mainApp.dataService.getDepartments());
         coursesList=FXCollections.observableArrayList(mainApp.dataService.getCourses());
         lists=FXCollections.observableArrayList(mainApp.dataService.getModels(new DepartmentalCourses()));
        departments.setItems(departmentList);
        courses.setItems(coursesList);
        courseType.getItems().addAll("Departmental Course", "Borrowed Course");
        
        table.setItems(lists);
        TableColumn<DepartmentalCourses, String> col1 = new TableColumn<>("Department Name");
        col1.setCellValueFactory(
                new PropertyValueFactory<DepartmentalCourses, String>("department"));
        col1.setMinWidth(200);
        TableColumn<DepartmentalCourses, String> col2 = new TableColumn<>("Course Name");
        col2.setCellValueFactory(
                new PropertyValueFactory<DepartmentalCourses, String>("course"));
             col2.setMinWidth(200);
        TableColumn<DepartmentalCourses, String> col3 = new TableColumn<>("Course Type");
        col3.setCellValueFactory(
                new PropertyValueFactory<DepartmentalCourses, String>("courseType"));
        col3.setMinWidth(200);
        table.getColumns().addAll(col1, col2, col3);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
