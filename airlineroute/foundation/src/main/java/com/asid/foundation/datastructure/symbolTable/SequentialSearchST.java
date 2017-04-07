package com.asid.foundation.datastructure.symbolTable;

import com.asid.foundation.datastructure.list.CustomLinkedList;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lenovo on 2017-04-07.
 */
public class SequentialSearchST <K extends Comparable, V> extends AbstractSymbolTable {

    private List<Node> list = new CustomLinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return indexOfKey((K) key) != -1;
    }

    @Override
    public Object get(Object key) {
        Object returnObject = null;
        for(int i = 0; returnObject == null && i < list.size(); i++){
            if(list.get(i).getKey().equals(key)){
                returnObject = list.get(i).getValue();
            }
        }
        return returnObject;
    }

    @Override
    public Object put(Comparable key, Object value) {
        Object previousValue = get(key);
        if(containsKey(key)){
            list.get(indexOfKey(key)).setValue((V) value);
        }
        else {
            Node nodeToAdd = new Node((K) key,(V) value);
            list.add(0, nodeToAdd);
        }

        return previousValue;
    }

    @Override
    public Object remove(Object key) {
        Object returnObject = null;
        if(containsKey(key)){
            returnObject = list.remove(indexOfKey(key)).getValue();
        }
        return returnObject;
    }

    @Override
    public Set keySet() {
        Set<K> keySet = new HashSet<>();
        for(Node node : list){
            keySet.add(node.getKey());
        }
        return keySet;
    }

    @Override
    public Object put(Object key, Object value) {
        return put((Comparable) key, value);
    }

    private class Node{
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

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
    }

    private int indexOfKey(Object key){
        int indexOfKey = -1;
        for(int i = 0; indexOfKey == -1 && i < list.size(); i++){
            if(list.get(i).getKey().equals(key)){
                indexOfKey = i;
            }
        }
        return indexOfKey;
    }
}
