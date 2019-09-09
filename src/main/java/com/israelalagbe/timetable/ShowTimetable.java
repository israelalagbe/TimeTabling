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
import java.util.ArrayList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

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
      String[] days={
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
        };

    @Override
    public void loaded() throws Exception {
        departments.setItems(obs(mainApp.dataService.getDepartments()));
        levels.setItems(obs(mainApp.dataService.getModels(new Level())));
        grid.setPadding(new Insets(50,50,50,50));
        grid.setAlignment(Pos.CENTER);
       
       
            for (int col = 0; col <= 4; col++) {
               
                Label label=new Label(days[col]);
                addItem(label,0, col);
            }
    }
    void addItem(Label label, int row,int col){
        RowConstraints rc=new RowConstraints(70);
                 ColumnConstraints column1 = new ColumnConstraints(150);
                 column1.setHalignment(HPos.CENTER);
                 rc.setValignment(VPos.CENTER);
                grid.getColumnConstraints().add(column1);
                grid.getRowConstraints().add(rc);
                
                VBox pane=new VBox(label);
                pane.setAlignment(Pos.CENTER);
                pane.setStyle("-fx-border-color:black;");
               
                grid.add(pane, col, row);
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
          List children=grid.getChildren();
        grid.getChildren().clear();
        grid.setPadding(new Insets(50,50,50,50));
        grid.setAlignment(Pos.CENTER);
        for (int col = 0; col <= 4; col++) {
               
                Label label=new Label(days[col]);
                
                addItem(label,0, col);
            }
        for (int i = 0; i < days.length; i++) {
            displayTimetableDay(list, days[i], i);
        }
        grid.setGridLinesVisible(true);
        
//        System.out.println("Timetable lenght : "+list.size());
    }
    void displayTimetableDay(List <TimeTable>allTimetables, String day,int col){
        List <TimeTable>dayList=new ArrayList<>();
        for (TimeTable timeTable : allTimetables) {
            if(timeTable.getDay().equals(day)){
                dayList.add(timeTable);
            }
        }
        int row = 0;
        for (; row < dayList.size(); row++) {
            TimeTable timeTable =dayList.get(row) ;
            Label label=new Label(timeTable.getCourse().getName()+
                    "\n Time: "+ timeTable.getTime() +
                    "\n Duration: " + timeTable.getDuration() +" Hour(s)");
            addItem(label,row+1, col);
        }
        for(;row<10;row++){
            Label label=new Label("");
             addItem(label,row+1, col);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    private ObservableList obs(List  list){
        return FXCollections.observableArrayList(list);  
    }
    
}
