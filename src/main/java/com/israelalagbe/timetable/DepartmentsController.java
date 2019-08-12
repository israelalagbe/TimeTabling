package com.israelalagbe.timetable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DepartmentsController extends BaseController{
    @FXML
    private Label label;

    @FXML
    private JFXTextField departmentName;

    @FXML
    private TableView<Department> departmentsTable;
    
     private ObservableList<Department> departmentsObservable = null;
     
    @FXML
    private void saveDepartment(ActionEvent event) {
        //courseCode.getText(), courseName.getText();
        Department department=new Department();
        department.setName(departmentName.getText());
        if(department.getName().isEmpty()){
            UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Department name cannot be empty" );
            return;
        }
        mainApp.dataService.addDepartment(department);
        UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Department Saved" );
        try{
            departmentsObservable.setAll(mainApp.dataService.getDepartments());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        // System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       UIManager.addRequiredValidator(departmentName, "Department name is required");
        //UIManager.addRequiredValidator(courseCode, "Course code is required");
       
    }
    public void loaded(){
        departmentsObservable=FXCollections.observableArrayList(new ArrayList());
         departmentsObservable.setAll(mainApp.dataService.getDepartments());
         System.out.println("djdjd"+mainApp.dataService.getDepartments().size());
        departmentsTable.setItems(departmentsObservable);
        TableColumn<Department, String> nameCol = new TableColumn<>("Department Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Department, String>("name"));
        departmentsTable.getColumns().addAll(nameCol);
    }
}

