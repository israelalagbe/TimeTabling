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
import com.israelalagbe.timetable.models.TimeTable;
import java.util.ArrayList;
import java.util.function.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Israel Alagbe
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        TableColumn<Department,  Button> deleteCol = new TableColumn<>("Action");
        deleteCol.setCellFactory(ActionButtonTableCell.<Department>forTableColumn("Delete",new Function<Department, Department>() {
            @Override
            public Department apply(Department department) {
                mainApp.dataService.deleteModels(new DepartmentalCourses(),"department="+department.getId());
            mainApp.dataService.deleteModels(new TimeTable(),"department="+department.getId());
                mainApp.dataService.deleteModel(department);
                departmentsObservable.setAll(mainApp.dataService.getDepartments());
                UIManager.showAlert(Alert.AlertType.INFORMATION, mainApp.getWindow(), "Success", "Department Deleted" );
                return  department;
            }
        }));
        departmentsTable.getColumns().addAll(nameCol,deleteCol);
    }
}

