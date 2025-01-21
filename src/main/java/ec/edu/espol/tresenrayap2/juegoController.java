package ec.edu.espol.tresenrayap2;

import java.io.PrintStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class juegoController {
    
    private Game partida;
    
    @FXML
    private Button A1;

    @FXML
    private Button A2;

    @FXML
    private Button A3;

    @FXML
    private Button B1;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button C1;

    @FXML
    private Button C2;

    @FXML
    private Button C3;

    @FXML
    private GridPane grid;

    @FXML
    private TextArea screentxt;
    
    @FXML
    private MenuItem ng;
    
    @FXML
    private MenuItem quit;
    
    @FXML
    public void initialize() {
        // Redirigir System.out a screentxt
        PrintStream ps = new PrintStream(new TextOutStream(screentxt), true);
        System.setOut(ps);
        System.setErr(ps);
    }
    
    public void inicializarJuego(char simbolo, boolean empieza) {
        
        partida = new Game();
        partida.setOrden(simbolo, empieza);

        partida.current(!empieza);
        if(!empieza){
            partida.jugarBot();
                String posbot = partida.lastMove();
                Button botbutt = getButt(posbot);
                if(botbutt != null){
                    botbutt.setText(String.valueOf(partida.getTable().getFill(posbot)));
                    botbutt.setDisable(true);
                }
        }
    }
    
    
    private Button getButt(String id){
        switch (id) {
            case "A1": return A1;
            case "A2": return A2;
            case "A3": return A3;
            case "B1": return B1;
            case "B2": return B2;
            case "B3": return B3;
            case "C1": return C1;
            case "C2": return C2;
            case "C3": return C3;
            default: return null;
        }
    }
    
    @FXML
    private void buttonClick(ActionEvent event){
        Button clicked = (Button) event.getSource();
        String position = clicked.getId();
        partida.jugar(position);
        clicked.setText(String.valueOf(partida.getTable().getFill(position)));
        clicked.setDisable(true);
        
        if(!partida.hayGanador(partida.getTable().getFill(position),partida.getTable())){
            
            if(!partida.getTable().disponibles().isEmpty()){
            
                partida.jugarBot();
                String posbot = partida.lastMove();
                Button botbutt = getButt(posbot);
                if(botbutt != null){
                    botbutt.setText(String.valueOf(partida.getTable().getFill(posbot)));
                    botbutt.setDisable(true);
                    if(partida.getTable().disponibles().isEmpty()) System.out.println("\nES UN EMPATE!!");
                }
                if(partida.hayGanador(partida.getTable().getFill(posbot),partida.getTable())){
                    deshabilitarBotones();
                }
            }
            else System.out.println("\nES UN EMPATE!!");
        }
        else{
            deshabilitarBotones();
        }
    }
    
    private void deshabilitarBotones() {
        for (String pos : partida.getTable().getCasillas().keySet()) {
            Button btn = (Button) A1.getScene().lookup("#" + pos);
            if (btn != null) {
                btn.setDisable(true);
            }
        }
    }
   @FXML
    private void preStart(ActionEvent event) {
        
        try {
            Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Cargar la nueva pantalla desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("seleccion.fxml"));
            Parent root = loader.load();

        // Establecer la nueva escena
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
