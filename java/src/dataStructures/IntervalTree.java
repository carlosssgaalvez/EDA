package dataStructures;

import dataStructures.list.ArrayList;
import dataStructures.list.LinkedList;
import dataStructures.list.List;


public class IntervalTree {
    private static class Node{
        Interval interval;
        int max;
        Node lt, rt;

        /* (1.25 ptos) Constructor de Node
        */

        public Node (Interval interval, Node lt, Node rt){
            this.interval = interval;
            this.lt = lt;
            this.lt = rt;
            this.max = maximo();
        }

        public int maximo(){
            int ltmax, rtmax, aux;
            if(lt == null) ltmax = 0; else ltmax = lt.max;
            if(rt == null) rtmax = 0; else rtmax = rt.max;
            aux = Math.max(rtmax, ltmax);
            return Math.max(interval.high(), aux);
        }

        public Node(Interval interval) {
            this(interval,null, null);
        }
    }
    /* Atributos de IntervalTree */
    Node root; //raíz del árbol
    int size; //número de intervalos almacenados
    /* (0.75 pto) Constructor del árbol de intervalo vacío
    */
    public IntervalTree(){
        // TO DO
        this.size = 0;
        this.root = null;
    }
    /* (0.5 ptos) Devuelve true si el árbol de intervalo
    está vacío
    */
    public boolean isEmpty() {
        // TO DO
        return size == 0;
    }
    /* (0.5 ptos) Devuelve el número de intervalos
    almacenados en el
    árbol de intervalo
    */
    public int size() {
        // TO DO
        return this.size;
    }
    /* (2 ptos) Inserta el intervalo x en el árbol de
    intervalos.
    El árbol de intrevalo resultante debe mantener la
    propiedad de
    orden.
    Es similar a un BST: los nodos del árbol están están
    ordenados
    por el valor inferior del intervalo que almacenan.
    Si existe un nodo cuyo intervalo tiene el mismo valor
    inferior
    que x,
    actualiza el límite superior del intervalo almacenado en
    el
    árbol.
    */
    public void insert(Interval x) {
        this.root = insertRec(root, x);
    }

    public Node insertRec(Node node, Interval x){
        if(node == null){
            node = new Node(x);
            size++;
        } else if(x.compareTo(node.interval) == 0){
            node.interval = x;
            node.max = Math.max(x.high(), node.max);        
        } else if(x.compareTo(node.interval) < 0){
            node.max = Math.max(x.high(), node.max);
            node.lt = insertRec(node.lt, x);
        } else if(x.compareTo(node.interval) > 0){
            node.max = Math.max(x.high(), node.max);
            node.rt = insertRec(node.rt, x);
        }
        return node;
    }

        /* (0.5 ptos) Devuelve true si se satisface la
        condición 1:
        Condición C1: el nodo n no es nulo y su atributo max
        es mayor o igual que el límite inferior del
        intervalo x
        */
    private boolean condicionC1(Node n, Interval x) {
        // TO DO
        return (n != null && n.max >= x.low());
    }
        /* (0.5 ptos) Devuelve true si se satisface la
        condición 2
        Condición C2: el hijo derecho de n no es nulo y el
        límite inferior
        de n
        es menor o igual que el límite superior del intervalo
        x
        */
    private boolean condicionC2(Node n, Interval x) {
        // TO DO
        return (n.rt != null && n.interval.low() <= x.high());
    }
        /* (2 ptos) Devuelve un intervalo del árbol que solapa
        con x.
        Debe ser una implementación eficiente que aproveche la
        propiedad de
        orden.
        Si no solapa con ningún intervalo del árbol devuelve
        null.
        En las transparencias 13-16 hay ejemplos de como debe
        explorarse el
        árbol.
        */
    public Interval searchOverlappingInterval(Interval x) {
        // TO DO
        return searchRec(root, x);
    }

    private Interval searchRec(Node node, Interval x){
        if (!condicionC1(node, x)) return null;         
        if (node.interval.overlap(x)) return node.interval;
        if(node.lt != null) {
            Interval aux = searchRec(node.lt,x);
            if(aux == null){
                if(condicionC2(node, x)) return searchRec(node.rt, x);
            } else {
                return aux;
            }
        }
        return null;
    }

    /* (2 ptos) Devuelve una lista con todos los intervalos
    del árbol que
    solapan con x.
    Debe ser una implementación eficiente que aproveche la
    propiedad
    de orden.
    Si no solapa con ninguno devuelve una lista vacía.
    En la transparencia 17 hay un ejemplo.
    */

    public List<Interval> allOverlappingIntervals(Interval x){
        List<Interval> lista = new ArrayList<>();
        lista = searchAllList (lista, root, x);
        return lista;
    }

    private List<Interval> searchAllList (List<Interval> lista, Node node, Interval x){
        Interval aux =searchOverlappingInterval(x);
        if(aux != null){
            lista.append(aux);
            if(node.lt != null) lista = searchAllList(lista, node.lt, x);
            if(condicionC2(node, x)){
                lista = searchAllList(lista, node.rt, x);
            }
        }
        return lista;
    }

    /* ---------- No modificar esta parte ---------- */
    @Override
    public String toString() {
    return toStringRec(root);
    }
    private String toStringRec(Node root) {
    if(root!=null){
    return toStringRec(root.lt) +
    root.interval.toString()+"( "+
    root.max +" )"+ toStringRec(root.rt);
    }
    return "";
    }
}
