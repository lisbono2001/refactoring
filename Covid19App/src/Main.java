import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * initialize class for rum the application.
 *
 * @author Bhatara Chaemchan
 */
public class Main extends Application {

    @Override
    /**
     * Method to initialize scene and stage.
     */
    public void start(Stage primaryStage) throws Exception {
        //set root and load the menu fxml file when application started.
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        //set stage's title.
        primaryStage.setTitle("Covid19 Tracker");
        //set stage's screen to the root.
        primaryStage.setScene(new Scene(root));
        //fix stage size.
        primaryStage.setResizable(false);
        //show the application.
        primaryStage.show();
    }

    /**
     * Mathod for run Application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
