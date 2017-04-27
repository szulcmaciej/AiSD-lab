package com.asid.foundation.datastructure.symbolTable;

import java.util.Set;

/**
 * Created by Lenovo on 2017-04-26.
 */
/*public class RedBlackBinarySearchTreeST <K extends Comparable, V> extends AbstractSymbolTable {

    private static boolean RED = true;
    private static boolean BLACK = false;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Comparable key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }


    private class Node {
        K key;
        V val;
        Node left, right;
        int N;
        boolean color;

        public Node(K key, V val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node n){
        if(n == null)
            return false;
        else
            return n.color == RED;
    }

    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    Node rotateRight(Node h){
        Node x = h.left;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node put(Node h, K key, V val){
        if(h == null)
            return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if(cmp < 0) h.left = put(h.left, key, val);
        else if(cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
}*/
