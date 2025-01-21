package ec.edu.espol.tresenrayap2;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @author gilmar munoz g
 */

public class Turno {
    private Queue<Character> turnos;
    
    public Turno(Turno turn){
        this.turnos = new LinkedList<>(turn.turnos);
    }
    public Turno(){
        turnos=new LinkedList<>();
        turnos.offer('O');
        turnos.offer('X');
        
    }
    //obtiene el turno actual sin avanzar
    public char getTurno(){
        return turnos.peek();
    }
    //avanza un turno
    public char nexturn(){
        char turn = turnos.poll();
        turnos.offer(turn);
        return turn;
    }
}
