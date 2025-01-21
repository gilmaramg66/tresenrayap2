package ec.edu.espol.tresenrayap2;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class welcomeController {

    @FXML
    private Button startButton;
    
    @FXML
    private MenuItem ng;
    
    @FXML
    private MenuItem quit;

    @FXML
    private void preStart(ActionEvent event) {
        
        try{
            // Carga la nueva escena
            FXMLLoader loader = new FXMLLoader(getClass().getResource("seleccion.fxml"));
            Parent root = loader.load();

            // Obtén el controlador de la segunda escena
            seleccionController seleccionController = loader.getController();

            // Cambia la escena
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void escape(ActionEvent event) {
        Platform.exit();
    }

}
