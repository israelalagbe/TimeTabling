/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import com.israelalagbe.timetable.models.Course;
import com.israelalagbe.timetable.models.Department;
import com.israelalagbe.timetable.models.LectureRoom;
import com.israelalagbe.timetable.models.Lecturer;
import com.israelalagbe.timetable.models.Level;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author User
 */
public class TimetableController extends BaseController {
    @FXML
    private JFXTimePicker time;
    @FXML
    private JFXTextField duration;
      @FXML
    private JFXComboBox<Department>  departments;
      @FXML
    private JFXComboBox<Course>  courses;
      @FXML
    private JFXComboBox<Lecturer>  lecturers;
      @FXML
    private JFXComboBox<LectureRoom>  lectureRooms;
      @FXML
    private JFXComboBox<Level>  levels;
      @FXML
    private JFXComboBox<String>  days;
    @Override
    public void loaded() throws Exception {
       
       timeListeners();
    }
    private void timeListeners(){
      time.focusedProperty().addListener(new ChangeListener<Boolean>(){
           @Override
           public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if(newValue){
                   time.show();
               }
               else{
                   int hour=time.getValue().getHour();
                   if(hour<7 || hour>19){
                       LocalTime localTime = LocalTime.now().withHour(7).withMinute(0);
                        time.setValue(localTime);
                   }
               }
           }
       });  
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalTime localTime = LocalTime.now().withHour(7).withMinute(0);
        time.setValue(localTime);
        UIManager.addRequiredValidator(departments, "This field is required");
        UIManager.addRequiredValidator(courses, "This field is required");
        UIManager.addRequiredValidator(levels, "This field is required");
        UIManager.addRequiredValidator(lecturers, "This field is required");
        UIManager.addRequiredValidator(lectureRooms, "This field is required");
        UIManager.addRequiredValidator(days, "This field is required");
        UIManager.addRequiredValidator(time, "This field is required");
        UIManager.addRequiredValidator(duration, "This field is required");
        UIManager.addNumberValidator(duration, "This field is required");
    }
    
    @FXML
    private void save(ActionEvent event) {
        
    }
    
}
