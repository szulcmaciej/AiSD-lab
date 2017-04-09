package com.asid.foundation.datastructure.symbolTable;

import com.asid.foundation.datastructure.list.Node;

import java.util.Set;

/**
 * Created by Lenovo on 2017-04-09.
 */
public class BinarySearchTreeST <K extends Comparable, V> extends AbstractSymbolTable {

    private Node root = null;

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
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

    private class Node{
        private K key;
        private V value;
        private Node leftSubtree;
        private Node rightSubtree;

        public K getKey() {
            return key;
        }
        public void setKey(K key) {
            this.key = key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public Node getLeftSubtree() {
            return leftSubtree;
        }
        public void setLeftSubtree(Node leftSubtree) {
            this.leftSubtree = leftSubtree;
        }
        public Node getRightSubtree() {
            return rightSubtree;
        }
        public void setRightSubtree(Node rightSubtree) {
            this.rightSubtree = rightSubtree;
        }
        public Node(K key, V value, Node leftSubtree, Node rightSubtree) {

            this.key = key;
            this.value = value;
            this.leftSubtree = leftSubtree;
            this.rightSubtree = rightSubtree;
        }
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int size(){
            if(leftSubtree == null && rightSubtree == null){
                return 1;
            }
            else if(leftSubtree == null){
                return 1 + rightSubtree.size();
            }
            else if(rightSubtree == null){
                return 1 + leftSubtree.size();
            }
            else {
                return 1 + leftSubtree.size() + rightSubtree.size();
            }
        }


    }
}
