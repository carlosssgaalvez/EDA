package dataStructures;

public class SimpleSplayTree {
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
    public SimpleSplayTree() {
        root = null;
    }

    // Rotación a la derecha
    //0,25
    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
        return y;
    }

    // Rotación a la izquierda
    //0,25
    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
        return y;
    }

    // Función de splay
    //0,3
    private void splay(Node x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                if (x == x.parent.left) rightRotate(x.parent);
                else leftRotate(x.parent);
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {
                rightRotate(x.parent.parent);
                rightRotate(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {
                leftRotate(x.parent.parent);
                leftRotate(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {
                leftRotate(x.parent);
                rightRotate(x.parent);
            } else {
                rightRotate(x.parent);
                leftRotate(x.parent);
            }
        }
    }

    // Insertar un nodo
    //0,2
    public void insert(int key) {
        Node node = new Node(key);
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (node.key < x.key) x = x.left;
            else x = x.right;
        }

        node.parent = y;
        if (y == null) root = node;
        else if (node.key < y.key) y.left = node;
        else y.right = node;

        splay(node);
    }

    // Buscar un nodo
    //0,2
    public Node search(int key) {
        Node x = root;
        while (x != null) {
            if (key < x.key) x = x.left;
            else if (key > x.key) x = x.right;
            else return x;
        }
        return null;
    }

    // Método para eliminar un nodo
    //0,2
    public void delete(int key) {
        Node node = search(key);
        if (node == null) return; // El nodo no está presente en el árbol

        splay(node); // Llevar el nodo al la raíz

        if (node.left != null) {
            Node max = node.left;
            while (max.right != null) {
                max = max.right;
            }
            max.right = node.right;
            node.right.parent = max;
            node.left.parent = null;
            root = node.left;
        } else {
            root = node.right;
            node.right.parent = null;
        }
    }
}
