/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.israelalagbe.timetable.models.Lecturer;
import com.jfoenix.controls.JFXComboBox;
/**
 * FXML Controller class
 *
 * @author User
 */
public class LecturerController extends BaseController{
    //@FXML
    //private Label label;

    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXComboBox<String>  gender;
    
    @FXML
    private TableView<Lecturer> table;
    
     private ObservableList<Lecturer> lists = null;
     
    @FXML
    private void save(ActionEvent event) {
        Lecturer lecturer=new Lecturer();
        lecturer.setFirstName(firstName.getText());
        lecturer.setLastName(lastName.getText());
        lecturer.setGender(gender.getSelectionModel().getSelectedItem());
        
        if(lecturer.getFirstName().isEmpty()||lecturer.getLastName().isEmpty()||lecturer.getGender().isEmpty()){
            UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Lecturer's info cannot be empty" );
            return;
        }
        mainApp.dataService.saveModel(lecturer);
        UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Department Saved" );
        try{
            lists.setAll(mainApp.dataService.getLecturers());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        // System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UIManager.addRequiredValidator(firstName, "First name is required");
        UIManager.addRequiredValidator(lastName, "Last Name is required");
       
    }
    public void loaded(){
        gender.getItems().addAll("Male","Female");
        lists=FXCollections.observableArrayList(mainApp.dataService.getLecturers());
        table.setItems(lists);
        TableColumn<Lecturer, String> col1 = new TableColumn<>("Lecturer's First Name");
        col1.setCellValueFactory(
                new PropertyValueFactory<Lecturer, String>("firstName"));
        col1.setMinWidth(200);
        TableColumn<Lecturer, String> col2 = new TableColumn<>("Lecturer's Last Name");
        col2.setCellValueFactory(
                new PropertyValueFactory<Lecturer, String>("lastName"));
             col2.setMinWidth(200);
        TableColumn<Lecturer, String> col3 = new TableColumn<>("Lecturer's Gender");
        col3.setCellValueFactory(
                new PropertyValueFactory<Lecturer, String>("gender"));
        col3.setMinWidth(200);
        table.getColumns().addAll(col1, col2, col3);
        
    }
}

