package com.asid.foundation.datastructure.symbolTable;

import com.asid.foundation.datastructure.list.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lenovo on 2017-04-26.
 */
public class RedBlackBinarySearchTreeST <K extends Comparable, V> extends AbstractSymbolTable {

    private static boolean RED = true;
    private static boolean BLACK = false;

    private Node root;

    @Override
    public int size() {
        if(root == null) return 0;
        else return size(root);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public Object get(Object key) {
        if(root == null)
            return null;
        else
            return get((K) key, root);
    }

    @Override
    public Object put(Comparable key, Object value) {
        Object previousValue = null;
        if(root != null)
            previousValue = get(key);
        root = put(root, (K) key, (V) value);
        return previousValue;
    }

    @Override
    public Object remove(Object key) {
        Object value = get(key);
        if(root != null){
            delete((K) key);
        }
        return value;
    }

    @Override
    public Set keySet() {
        return keySet(root, new HashSet<>());
    }

    @Override
    public Object put(Object key, Object value) {
        return put(root, (K) key, (V) value);
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
        if (h.left != null) {
            h.left.color = BLACK;
        }
        if (h.right != null) {
            h.right.color = BLACK;
        }
    }

    int size(Node h){
        if(h == null)
            return 0;
        int size = 1;
        if(h.left != null) size += size(h.left);
        if(h.right != null) size += size(h.right);
        return size;
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

    private void delete(K key) {
        if (isRed(root.left) && !isRed(root.right))
        root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node delete(Node h, K key) {
        if (key.compareTo(h.key) < 0)
        {
            if ( !isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else
        {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0)
            {
                h.val = get(min(h.right).key, h.right);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    Set<K> keySet(Node h, Set<K> set){
        if(h == null)
            return set;
        else {
            set.add(h.key);
            keySet(h.left, set);
            keySet(h.right, set);
        }
        return set;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left))
        {
            h.right = rotateRight(h.right) ;
            h = rotateLeft(h);
        }
        return h;
    }
    private void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
        root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMin(Node h) {
        if (h.left == null)
        return null;
        if (! isRed (h.left) && ! isRed (h.left. left))
        h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (!isRed(h.left.left))
            h = rotateRight(h);
        return h;
    }
    private void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
        root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    private Node balance(Node h){
        if (isRed(h.right)) return rotateLeft(h);
        else return h;
    }
    private Node min(Node h){
        if(h.left == null)
            return h;
        else
            return min(h.left);
    }

    private V get(K key, Node h){
        if(h == null)
            return null;
        else {
            if(key.compareTo(h.key) == 0)
                return h.val;
            else if(key.compareTo(h.key) < 0)
                return get(key, h.left);
            else
                return get(key, h.right);
        }
    }
}
