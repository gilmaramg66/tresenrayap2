package ec.edu.espol.tresenrayap2;

/**
 *
 * @author gilmar munoz g
 */
public class Chances {
    private int chanceX;
    private int chanceO;
    
    public Chances() {
        this.chanceX = 0;
        this.chanceO = 0;
    }
    
    
    //calcula la utilidad 
    public int utilidad(char turno){
        return
        switch (turno){
            case 'X' -> chanceX-chanceO;
            case 'O' -> chanceO-chanceX;
            default -> 0;
        };
    }
    
    public int getChanceX() {
        return chanceX;
    }

    public void setChanceX(int chanceX) {
        this.chanceX = chanceX;
    }

    public int getChanceO() {
        return chanceO;
    }

    public void setChanceO(int chanceO) {
        this.chanceO = chanceO;
    }
    
    //aumenta posibilidades a X
    public void plusX(){
        chanceX=chanceX+1;
    }
    
    //aumenta posibilidades a O
    public void plusO(){
        chanceO=chanceO+1;
    }
    
    //toString para mostrar las posibilidades de cada jugador.
    @Override
    public String toString(){
        return "X tiene " + chanceX + " posibilidades, y O tiene " + chanceO + " posibilidades.";
    }
}
