package dataStructures;

public class ExSimpleSplayTree {
    private class Node {
        int key;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            left = right = parent = null;
        }
    }

    private Node root;

    //constructor vacío
    //0,1
    public ExSimpleSplayTree() {
        //COMPLETAR
        this.root = null;
    }

    // Rotación a la derecha
    //0,25
    private Node rightRotate(Node x) {  // x quedará como hijo derecho de aux
        Node aux = x.left;  
        x.left = aux.right;
        if(x.left != null) x.left.parent = x; // si había hijo le actualizamos el padre
        aux.parent = x.parent;
        if(aux.parent == null) root = aux;  // si no hay mas padres quedará como nueva raiz
        else if(x.parent.right == x) x.parent.right = aux; // aux se encuentra en rama derecha del padre
        else x.parent.left = aux;           // se encontraba en la rama izquierda
        aux.right = x;
        x.parent = aux;
        //COMPLETAR
        return aux;
    }

    // Rotación a la izquierda
    //0,25
    private Node leftRotate(Node x) {
        //COMPLETAR
        return null;
    }

    // Función de splay
    //0,3
    private void splay(Node x) {
        //COMPLETAR
    }

    // Insertar un nodo
    //0,2
    public void insert(int key) {
        //COMPLETAR
    }

    // Buscar un nodo
    //0,2
    public Node search(int key) {
        //COMPLETAR
        return null;
    }

    // Método para eliminar un nodo
    //0,2
    public void delete(int key) {
        //COMPLETAR
    }
}
