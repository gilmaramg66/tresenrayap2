package ec.edu.espol.tresenrayap2;
/**
  * @author gilmar munoz g
 */
public class Jugada {
    private String casilla;
    private char player;

    public Jugada(String casilla, char player){
        this.casilla=casilla;
        this.player=player;
    }
    
    public String getCasilla() {
        return casilla;
    }

    public void setCasilla(String casilla) {
        this.casilla = casilla;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }
        
}
