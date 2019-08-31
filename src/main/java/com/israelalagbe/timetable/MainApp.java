package com.israelalagbe.timetable;

import cat.quickdb.db.AdminBase;
import com.israelalagbe.timetable.models.Course;
import com.israelalagbe.timetable.models.Example;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainApp extends Application {

    Scene scene;
    DataService dataService;

    public DataService getDataService() {
        return dataService;
    }

    public Scene getScene() {
        return scene;
    }

    public Window getWindow() {
        return scene.getWindow();
    }

    public void navigate(String pageName) {
        try {
            FXMLLoader loader = null;
            switch (pageName) {
                case "departments": {
                    loader = new FXMLLoader(getClass().getResource("/fxml/departments.fxml"));
                    break;
                }
                case "courses": {
                    loader = new FXMLLoader(getClass().getResource("/fxml/courses.fxml"));
                    break;
                }
                case "lecturers": {
                    loader = new FXMLLoader(getClass().getResource("/fxml/lecturers.fxml"));
                    break;
                }
                case "departmental_courses": {
                    loader = new FXMLLoader(getClass().getResource("/fxml/departmental_courses.fxml"));
                    break;
                }
            }

            Parent root = (Parent) loader.load();
            BaseController controller = loader.getController();
            controller.setMainApp(this);
            controller.loaded();
            //Parent root=FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));1
            Stage stage = (Stage) getWindow();
            stage.setTitle("Time Tabling");
            scene = new Scene(root);
            scene.getStylesheets()
                    .add("/styles/Styles.css");
            stage.setTitle("Timetabling");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Navigation error: "+e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        dataService = new DataService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/courses.fxml"));

        Parent root = (Parent) loader.load();
        CourseController courseController = loader.getController();
        courseController.setMainApp(this);
        try {
            courseController.loaded();
        } catch (Exception e) {
            UIManager.showAlert(Alert.AlertType.INFORMATION, stage, "Database Connection Error", e.getMessage());
        }

        //Parent root=FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        stage.setTitle("Time Tabling");
        scene = new Scene(root);
        scene.getStylesheets()
                .add("/styles/Styles.css");
        stage.setTitle("Timetabling");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Example example = new Example();
//         Course course=new Course();
//        try{
//            AdminBase admin = AdminBase.initialize(AdminBase.DATABASE.MYSQL,
//                "localhost", "3306", "timetable", "root", "");
//            admin.save(course);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }

        launch(args);
    }

}
