/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import com.israelalagbe.timetable.models.Course;
import com.israelalagbe.timetable.models.Department;
import com.israelalagbe.timetable.models.DepartmentalCourses;
import com.israelalagbe.timetable.models.LectureRoom;
import com.israelalagbe.timetable.models.Lecturer;
import com.israelalagbe.timetable.models.Level;
import com.israelalagbe.timetable.models.TimeTable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private JFXComboBox<DepartmentalCourses>  courses;
      @FXML
    private JFXComboBox<Lecturer>  lecturers;
      @FXML
    private JFXComboBox<LectureRoom>  lectureRooms;
      @FXML
    private JFXComboBox<Level>  levels;
      @FXML
    private JFXComboBox<String>  days;
      private ObservableList<TimeTable> lists = null;
      @FXML
    private TableView<TimeTable> table;
    @Override
    public void loaded() throws Exception {
       
       departments.setItems(obs(mainApp.dataService.getDepartments()));
       departments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>(){
           @Override
           public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                     courses.setItems(obs(mainApp.dataService.getDepartmentCoursesByDepartment(newValue)));
           }
            
       
       });
       
       lecturers.setItems(obs(mainApp.dataService.getLecturers()));
       lectureRooms.setItems(obs(mainApp.dataService.getModels(new LectureRoom())));
       levels.setItems(obs(mainApp.dataService.getModels(new Level())));
       days.getItems().addAll("Monday", "Tuesday","Wednesday", "Thursday","Friday");
       timeListeners();
        lists=FXCollections.observableArrayList(mainApp.dataService.getModels(new TimeTable()));
       table.setItems(lists);
       TableColumn<TimeTable, String> col1 = new TableColumn<>("Department Name");
        col1.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("department"));
        col1.setMinWidth(200);
        TableColumn<TimeTable, String> col2 = new TableColumn<>("Course Name");
        col2.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("course"));
             col2.setMinWidth(200);
        TableColumn<TimeTable, String> col3 = new TableColumn<>("Level");
        col3.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("level"));
        col3.setMinWidth(100);
         TableColumn<TimeTable, String> col4 = new TableColumn<>("Lecturer");
        col4.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("lecturer"));
        col4.setMinWidth(100);
        TableColumn<TimeTable, String> col5 = new TableColumn<>("Lecture Room");
        col5.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("lectureRoom"));
        col5.setMinWidth(100);
        TableColumn<TimeTable, String> col6 = new TableColumn<>("Day");
        col6.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("day"));
        col6.setMinWidth(100);
        TableColumn<TimeTable, String> col7 = new TableColumn<>("Time");
        col7.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("time"));
        col7.setMinWidth(100);
        TableColumn<TimeTable, String> col8 = new TableColumn<>("Duration (Hours)");
        col8.setCellValueFactory(
                new PropertyValueFactory<TimeTable, String>("duration"));
        col8.setMinWidth(100);
        
        TableColumn<TimeTable,  Button> deleteCol = new TableColumn<>("Action");
        deleteCol.setCellFactory(ActionButtonTableCell.<TimeTable>forTableColumn("Delete",new Function<TimeTable, TimeTable>() {
            @Override
            public TimeTable apply(TimeTable timetable) {
                mainApp.dataService.deleteModel(timetable);
                lists.setAll(mainApp.dataService.getModels(new TimeTable()));
                UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Timetable  Deleted" );
                return  timetable;
            }
        }));
        
        table.getColumns().addAll(col1, col2, col3,col4, col5,col6, col7, col8, deleteCol);
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
    }
    
    @FXML
    private void save(ActionEvent event) {
        TimeTable timeTable=new TimeTable();
        Department department=departments.getSelectionModel().getSelectedItem();
        DepartmentalCourses departmentalCourse=courses.getSelectionModel().getSelectedItem();
        Level level=levels.getSelectionModel().getSelectedItem();
        Lecturer lecturer=lecturers.getSelectionModel().getSelectedItem();
        LectureRoom  lectureRoom=lectureRooms.getSelectionModel().getSelectedItem();
        String day=days.getValue();
        int hour=time.getValue().getHour();
         if(
                department==null ||
                 departmentalCourse==null ||
                 level==null ||
                 lecturer==null ||
                 lectureRoom == null ||
                 day ==null ||
                 hour<7 || hour>19
            ){
            UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Error", "Info cannot be empty or info is lecture time is invalid!" );
                return;
        }
         timeTable.setDepartment(department);
         timeTable.setCourse(departmentalCourse.getCourse());
         timeTable.setLevel(level);
         timeTable.setLecturer(lecturer);
         timeTable.setLectureRoom(lectureRoom);
         timeTable.setDay(day);
         timeTable.setTime(time.getValue().toString());
         timeTable.setDuration(Integer.valueOf( duration.getText()));
         if(isLectureRoomBooked(timeTable)){
              UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Success", "Lecture room has already been booked!" );
              return;
         }
         if(isLecturerBooked(timeTable)){
              UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Success", "Lecturer  cannot appear more than once at a time!" );
              return;
         }
         if(isCourseBooked(timeTable)){
              UIManager.showAlert(Alert.AlertType.ERROR, mainApp.getWindow(), "Success", "Course cannot appear more than once in a week" );
              return;
         }
         mainApp.dataService.saveModel(timeTable);
        UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Timetable saved successfully!" );
        try{
           lists.setAll(mainApp.dataService.getModels(new TimeTable()));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        departments.getSelectionModel().clearSelection();
        courses.getSelectionModel().clearSelection();
        levels.getSelectionModel().clearSelection();
        lecturers.getSelectionModel().clearSelection();
        lectureRooms.getSelectionModel().clearSelection();
        days.getSelectionModel().clearSelection();
        
        
  }
    public boolean isCourseBooked(TimeTable timeTable){
        
        for (TimeTable t : lists) {
       
                if(   
                        timeTable.getCourse().getId() == t.getCourse().getId()
                        &&
                        timeTable.getDepartment().getId() == t.getDepartment().getId()
                        &&
                        timeTable.getLevel().getId() == t.getLevel().getId()
                       
                        
                  ){
                         return true;
                     
                }
        }
        return false;
    }
    public boolean isLecturerBooked(TimeTable timeTable){
        String day=days.getValue();
         int hour=time.getValue().getHour();
         int min=time.getValue().getMinute();
         int totalTime=(hour * 60)+min;
         int dur=Integer.valueOf( duration.getText()) * 60;
        for (TimeTable t : lists) {
                String time2=t.getTime();
                int hour2= Integer.valueOf(time2.split(":")[0]);
                int min2= Integer.valueOf(time2.split(":")[1]);
                int totalTime2=(hour2*60)+min2;
                int dur2=t.getDuration()*60;
                if(   
                         timeTable.getLecturer().getId()==t.getLecturer().getId()
                        &&
                        timeTable.getDay().equalsIgnoreCase(t.getDay())
                       &&
                        timeOverlaps(totalTime, dur, totalTime2, dur2)
                        
                  ){
                         return true;
                     
                }
        }
        return false;
    }
    public boolean isLectureRoomBooked(TimeTable timeTable){
        String day=days.getValue();
         int hour=time.getValue().getHour();
         int min=time.getValue().getMinute();
         int totalTime=(hour * 60)+min;
         int dur=Integer.valueOf( duration.getText()) * 60;
        for (TimeTable t : lists) {
                String time2=t.getTime();
                int hour2= Integer.valueOf(time2.split(":")[0]);
                int min2= Integer.valueOf(time2.split(":")[1]);
                int totalTime2=(hour2*60)+min2;
                int dur2=t.getDuration()*60;
                if(   
                         timeTable.getLectureRoom().getId()==t.getLectureRoom().getId()
                        &&
                        timeTable.getDay().equalsIgnoreCase(t.getDay())
                       &&
                        timeOverlaps(totalTime, dur, totalTime2, dur2)
                        
                  ){
                         return true;
                     
                }
        }
        return false;
    }
    private boolean timeOverlaps(int time1 /* x1*/, int duration1 /* x2*/, int time2  /* y1*/, int duration2  /* y2*/){
        if(  time1<=time2 && (time1+duration1) >  time2){
            return  true;
        }
        else if( time2 <= time1 &&  (time2+duration2) > time1){
            return true;
        }
        return false;
    }
    private ObservableList obs(List  list){
        return FXCollections.observableArrayList(list);  
    }
     private void timeListeners(){
         LocalTime localTime = LocalTime.now().withHour(7).withMinute(0).withSecond(0).withNano(0);
          time.setValue(localTime);
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
}
