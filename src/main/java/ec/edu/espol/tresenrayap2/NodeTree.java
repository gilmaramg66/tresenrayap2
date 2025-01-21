package ec.edu.espol.tresenrayap2;

import java.util.LinkedList;

/**
 *
 * Profesora: Adriana Collaguazo Jaramillo
 */
public class NodeTree<E extends Comparable<E>,K> {
    private E content;
    private K key;
    // Esa lista de hijos es una colección de otros árboles

    // Colección de otros nodos que son hijos
    private LinkedList<NodeTree<E,K>> children;

    public NodeTree() {
        this.content = null;
        this.children = null;
    }
    
    public NodeTree(E content,K key) {
        this.key = key;
        this.content = content;
        this.children = new LinkedList<>();
    }
    
    public K getKey(){
        return key;
    }
    
    public void setKey(K key){
        this.key=key;
    }
    
    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public LinkedList<NodeTree<E,K>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<NodeTree<E,K>> children) {
        this.children = children;
    }
    
    public void addChild(NodeTree<E,K> child){
        children.add(child);
    }
    
    public NodeTree<E,K> findMax(){
        if (children.isEmpty()) {
            return null; // Si no hay hijos, no se puede determinar.
        }

        NodeTree<E,K> mayor = children.get(0); // Suponemos que el primero es el mayor.
        for (NodeTree<E,K> child : children) {
            if (child.getContent().compareTo(mayor.getContent()) > 0) {
                mayor = child; // Actualizamos si encontramos uno con mayor utilidad.
            }
        }
        return mayor;
    }
    
    public NodeTree<E,K> findMin(){
        if (children.isEmpty()) {
            return null; // Si no hay hijos, no se puede determinar.
        }

        NodeTree<E,K> menor = children.get(0); // Suponemos que el primero es el mayor.
        for (NodeTree<E,K> child : children) {
            if (child.getContent().compareTo(menor.getContent()) > 0) {
                menor = child; // Actualizamos si encontramos uno con mayor utilidad.
            }
        }
        return menor;
    }
    
}
