package ec.edu.espol.tresenrayap2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class seleccionController {

    @FXML
    private ToggleButton buttonO;

    @FXML
    private ToggleButton buttonX;

    @FXML
    private ToggleGroup goFirst;

    @FXML
    private ToggleButton noFirst;

    @FXML
    private Button startButton;

    @FXML
    private ToggleGroup symbol;

    @FXML
    private ToggleButton yesFirst;
    
    @FXML
    private MenuItem quit;

    @FXML
    private void startGame(ActionEvent event) {
        try {
            // Obtén las selecciones
            String playerSymbol = ((ToggleButton) symbol.getSelectedToggle()).getText();
            String startChoice = ((ToggleButton) goFirst.getSelectedToggle()).getText();

            // Carga la nueva escena
            FXMLLoader loader = new FXMLLoader(getClass().getResource("juego.fxml"));
            Parent root = loader.load();

            // Obtén el controlador de la segunda escena
            juegoController juegoController = loader.getController();
            juegoController.inicializarJuego(playerSymbol.charAt(0), startChoice.equals("Yes"));

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
