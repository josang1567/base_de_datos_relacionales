package proyecto_AADD_UA2.proyecto_AADD_UA2;

import javafx.application.Application;
import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
	private static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    public static void main(String[] args) {
        launch();
    }

}
