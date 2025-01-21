package ec.edu.espol.tresenrayap2;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author gilmar munoz g
 */
public class Game {
    private Tablero table;
    private Turno turnos;
    private Stack<Jugada> moves;
    private boolean winner;
    
    private static final String[][] winTrios = {
        {"A1", "A2", "A3"},
        {"B1", "B2", "B3"},
        {"C1", "C2", "C3"},
        {"A1", "B1", "C1"},
        {"A2", "B2", "C2"},
        {"A3", "B3", "C3"},
        {"A1", "B2", "C3"},
        {"A3", "B2", "C1"}
    };
    
    public Game(){
        
        table = new Tablero();
        turnos = new Turno();
        moves = new Stack<>();
        winner = false;
        System.out.println("*** JUEGO INICIADO ***");
    }
    
    //movimiento por parte del jugador
    public void jugar(String space){
        
        char symbol = turnos.getTurno();        
        
        table.fillPos(space, symbol);
        moves.push(new Jugada(space,symbol));
        turnos.nexturn();
        
        winner=hayGanador(symbol,table);
        
        if (winner) System.out.println("\nEl jugador "+symbol+" ha ganado la partida");
        else{
            current(true);
        }
    }
    
    //calcula las posibilidades de cada jugador
    
    public Chances chances(Tablero t){
        Chances ch = new Chances();
        for(String[] combinacion : winTrios){
            if (t.getFill(combinacion[0]) != 'X' &&
                t.getFill(combinacion[1]) != 'X' &&
                t.getFill(combinacion[2]) != 'X'){
                
                ch.plusO();
            }
            if (t.getFill(combinacion[0]) != 'O' &&
                t.getFill(combinacion[1]) != 'O' &&
                t.getFill(combinacion[2]) != 'O'){
                
                ch.plusX();
            }  
        }
        return ch;
    }
    
    
    //define si hay un ganador
    public boolean hayGanador(char simbolo, Tablero t) {
        for (String[] combinacion : winTrios) {
            if (t.getFill(combinacion[0]) == simbolo &&
                t.getFill(combinacion[1]) == simbolo &&
                t.getFill(combinacion[2]) == simbolo) {
                return true;
            }
        }
        return false;
    }
    
    //devuelve el ultimo movimiento que fue hecho
    
    public String lastMove(){
        return moves.peek().getCasilla();    
    }
    
   //simula una jugada sin afectar el tablero original
    
    public Tablero fakeFill(Tablero t, String pos, char sym){
        Tablero copiaTable = new Tablero(t);
        copiaTable.fillPos(pos,sym);
        return copiaTable;
    }
    
    
    //metodo que encuentra la mejor jugada (algoritmo minmax)
    
    public String emule(){
        char fill = turnos.nexturn();
        char predict = turnos.nexturn();
        String move="";
        boolean add=true;
        int cont=0;
        
        Tree<Integer, Tablero> arbol = new Tree();
        
        arbol.setRoot(new NodeTree(0,table));
        
        for (String casilla:table.disponibles()){
            add=true;
            Tablero tChild = fakeFill(table, casilla,fill);
            NodeTree<Integer,Tablero> nodo = new NodeTree(0,tChild);
            
            if (!tChild.disponibles().isEmpty()){
                for (String c:tChild.disponibles()){
                    Tablero tGrand = fakeFill(tChild,c,predict);
                    int ut = chances(tGrand).utilidad(fill);
                    nodo.addChild(new NodeTree(ut,tGrand));
                    if (ut == nodo.findMin().getContent()) nodo.setContent(ut);
                    if (hayGanador(predict,tGrand)){
                        cont++;
                        if (cont <=1) add=false;
                        else add=true;
                    }
                }
            }
            
            if (add){
                arbol.getRoot().addChild(nodo);
                if(Objects.equals(nodo.getContent(), arbol.getRoot().findMax().getContent())){
                    arbol.getRoot().setContent(nodo.getContent());
                    move=casilla;
            }
            }
            if (hayGanador(fill,tChild)) move=casilla;
        }
        return move;
        
    }
    
    //metodo para mostrar por pantalla de quien es el turno
    public void current(boolean bot){
        char symbol = turnos.getTurno();        
        if (bot){
            System.out.println("\nEs el turno del jugador "+symbol+" (CPU)");
        }
        else{
            System.out.println("\nEs el turno del jugador "+symbol);
            System.out.println("Jugada sugerida: "+emule());
    
        }
    }
    
    //orden de jugadores
    public char setOrden(char player, boolean start){
        
        if((player == 'X' && start == true) || (player == 'O' && start == false)){
            turnos.nexturn();
        }
        return player;
    }
    
    //movimiento por parte del cpu
    public void jugarBot(){
        
        char symbol = turnos.getTurno();        
        String pos = emule();
        table.fillPos(pos, symbol);
        moves.push(new Jugada(pos,symbol));
        turnos.nexturn();
        
        winner=hayGanador(symbol,table);
        
        if (winner) System.out.println("\nEl jugador "+symbol+" ha ganado la partida");
        else if (!table.disponibles().isEmpty()){
            current(false);
        }
    }

    //getter para el tablero
    public Tablero getTable() {
        return table;
    }
    
    
}
