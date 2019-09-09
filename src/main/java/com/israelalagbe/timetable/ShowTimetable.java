/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import com.israelalagbe.timetable.models.Department;
import com.israelalagbe.timetable.models.DepartmentalCourses;
import com.israelalagbe.timetable.models.LectureRoom;
import com.israelalagbe.timetable.models.Lecturer;
import com.israelalagbe.timetable.models.Level;
import com.israelalagbe.timetable.models.TimeTable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author User
 */
public class ShowTimetable extends BaseController{
    
    private JFXTextField duration;
      @FXML
    private JFXComboBox<Department>  departments;
    @FXML
    private JFXComboBox<Level>  levels;
     @FXML
    private GridPane  grid;

    @Override
    public void loaded() throws Exception {
        departments.setItems(obs(mainApp.dataService.getDepartments()));
        levels.setItems(obs(mainApp.dataService.getModels(new Level())));
        grid.setPadding(new Insets(50,50,50,50));
        grid.setAlignment(Pos.CENTER);
        String[] days={
            "Moday", "Tuesday", "Wednesday", "Thursday", "Friday"
        };
       
            for (int col = 0; col <= 4; col++) {
               
                Label label=new Label(days[col]);
                addItem(label,0, col);
            }
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//               
//                Label label=new Label("Monday");
//                addItem(label, col, row);
//                
//            }
//        }
    }
    void addItem(Label label, int row,int col){
        RowConstraints rc=new RowConstraints(50);
                 ColumnConstraints column1 = new ColumnConstraints(100);
                 column1.setHalignment(HPos.CENTER);
                 rc.setValignment(VPos.CENTER);
                grid.getColumnConstraints().add(column1);
                grid.getRowConstraints().add(rc);
                grid.add(label, col, row);
    }
    @FXML
    private void filterTimetable(ActionEvent event) {
        Department department=departments.getSelectionModel().getSelectedItem();
        Level level=levels.getSelectionModel().getSelectedItem();
        if(department==null || level==null){
            UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Info cannot be empty!" );
                return;
        }
        List <TimeTable>list=mainApp.dataService.getTimetables(department, level);
        System.out.println("Timetable lenght : "+list.size());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    private ObservableList obs(List  list){
        return FXCollections.observableArrayList(list);  
    }
    
}
