import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller class for Menu.fxml.
 *
 * @author Bhatara Chaemchan SKE17
 */
public class MenuController {

    //initialize FXML attributes.
    @FXML
    private Button worldsummary;

    @FXML
    private Button barchart;

    /**
     * Method for switching to WorldSummary scene.
     * @param event action of WorldSummary button.
     * @throws IOException when fxml file not found.
     */
    @FXML
    public void worldSummaryNavigator(ActionEvent event) throws IOException {
        Parent worldSummary = FXMLLoader.load(getClass().getResource("fxml/WorldSummary.fxml"));
        Scene charScene = new Scene(worldSummary);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.setResizable(false);
        window.show();
    }

    /**
     * Method for switching to Charts scene.
     * @param event action of Charts button.
     * @throws IOException when fxml file not found.
     */
    @FXML
    public void barChartNavigator(ActionEvent event) throws IOException {
        Parent chart = FXMLLoader.load(getClass().getResource("fxml/BarChart.fxml"));
        Scene charScene = new Scene(chart);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.setResizable(false);
        window.show();
    }
}
