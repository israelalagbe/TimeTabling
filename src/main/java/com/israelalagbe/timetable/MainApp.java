package com.israelalagbe.timetable;

import cat.quickdb.db.AdminBase;
import com.israelalagbe.timetable.models.Example;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage stage) throws Exception {
        dataService=new DataService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        
        Parent root = (Parent) loader.load();
         CourseController courseController = loader.getController();
        courseController.setMainApp(this);
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
        Example example = new Example();
        AdminBase admin = AdminBase.initialize(AdminBase.DATABASE.MYSQL,
                "localhost", "3306", "timetable", "root", "");
        admin.save(example);
        launch(args);
    }

}
