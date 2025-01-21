package ec.edu.espol.tresenrayap2;

/**
 *
 * Profesora: Adriana Collaguazo Jaramillo
 */
public class Tree<E extends Comparable<E>,K> {
    
    // Un árbol solo conoce a su nodo raiz, que es de tipo NodeTree
    private NodeTree<E,K> root;

    public Tree() {
        this.root = null;
    }

    public NodeTree<E,K> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<E,K> root) {
        this.root = root;
    }
        
}
