
package ec.edu.espol.tresenrayap2;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author gilmar munoz g
 */
public class Tablero {
    private HashMap<String,Character>casillas;
    
    public Tablero(){
        casillas = new HashMap<>();
        for (String fila : new String[] {"A", "B", "C"}) {
            for (int col = 1; col <= 3; col++) {
                casillas.put(fila + col, ' ');
            }
        }
    }
    
    public Tablero(Tablero table){
        this.casillas = new HashMap<>(table.casillas);
    }
    
    //muestra el tablero en pantalla
    public void mostrarTablero() {
        System.out.println("\n  1   2   3");
        System.out.println("A " + casillas.get("A1") + " | " + casillas.get("A2") + " | " + casillas.get("A3"));
        System.out.println("  ---------");
        System.out.println("B " + casillas.get("B1") + " | " + casillas.get("B2") + " | " + casillas.get("B3"));
        System.out.println("  ---------");
        System.out.println("C " + casillas.get("C1") + " | " + casillas.get("C2") + " | " + casillas.get("C3")+"\n");
    }
    
    //getter del hashmap de casilleros
    public HashMap<String, Character> getCasillas() {
        return casillas;
    }
    
    //obtiene lo que hay dentro de un casillero especifico
    public char getFill(String space){
        if (!casillas.containsKey(space)) {
            throw new IllegalArgumentException("La posición " + space + " no es válida.");
        }
    return casillas.get(space);
    }
    
    //rellena un casillero
    public void fillPos(String posicion, char simbolo){
        try{
        if (getFill(posicion) != ' ') {
            throw new IllegalArgumentException("La posición " + posicion + " ya está ocupada.");
        }
        casillas.put(posicion, simbolo);
   
        }
        catch(IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //devuelve las casillas disponibles
    
    public HashSet<String> disponibles(){
        HashSet<String> available = new HashSet<>();
        for (String casilla : casillas.keySet()){
            if (casillas.get(casilla) == ' ') { 
                available.add(casilla);     
            }
        }
        return available;
    }
    
}
