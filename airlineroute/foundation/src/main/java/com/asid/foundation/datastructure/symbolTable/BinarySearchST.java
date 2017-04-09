package com.asid.foundation.datastructure.symbolTable;

import com.asid.foundation.datastructure.list.CustomArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lenovo on 2017-04-08.
 */
public class BinarySearchST <K extends Comparable, V> extends AbstractSymbolTable {

    private List<Node> list = new CustomArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return indexOfNodeWithKey(key) >= 0;
    }

    @Override
    public Object get(Object key) {
        Object returnObject = null;
        int index = indexOfNodeWithKey(key);
        if(index >= 0){
            returnObject = list.get(index).getValue();
        }
        return returnObject;
    }

    @Override
    public Object put(Comparable key, Object value) {
        Object returnObject = null;
        int indexOfNodeWithKey = indexOfNodeWithKey(key);
        if(indexOfNodeWithKey >= 0){
            returnObject = list.get(indexOfNodeWithKey).getValue();
            list.get(indexOfNodeWithKey).setValue((V) value);
        }
        else{
            Node nodeToAdd = new Node((K) key, (V) value);
            list.add(indexOfFirstGreater(key), nodeToAdd);
        }

        return returnObject;
    }

    @Override
    public Object remove(Object key) {
        Object returnObject = null;
        if(containsKey(key)){
            returnObject = list.remove(indexOfNodeWithKey(key)).getValue();
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
        K key;
        V value;

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

    private boolean containsKey(K key, int begIndex, int endIndex){
        boolean containsKey = false;
        int indexTocheck = (begIndex + endIndex) / 2;
        if(begIndex == endIndex){
            if(key.compareTo(list.get(begIndex).getKey()) == 0){
                containsKey = true;
            }
            else {
                containsKey = false;
            }
        }
        else {
            if(key.compareTo(list.get(indexTocheck).getKey()) < 0){
                containsKey = containsKey(key, begIndex, indexTocheck);
            }
            else if(key.compareTo(list.get(indexTocheck).getKey()) > 0){
                containsKey = containsKey(key, indexTocheck + 1, endIndex);
            }
            else if(key.compareTo(list.get(indexTocheck).getKey()) == 0){
                containsKey = true;
            }
        }

        return containsKey;
    }
    private Object get(K key, int begIndex, int endIndex){
        Object returnObject = null;
        int indexTocheck = (begIndex + endIndex) / 2;
        if(begIndex == endIndex){
            if(key.compareTo(list.get(begIndex).getKey()) == 0){
                returnObject = list.get(begIndex).getValue();
            }
        }
        else {
            if(key.compareTo(list.get(indexTocheck).getKey()) < 0){
                returnObject = get(key, begIndex, indexTocheck);
            }
            else if(key.compareTo(list.get(indexTocheck).getKey()) > 0){
                returnObject = get(key, indexTocheck + 1, endIndex);
            }
            else if(key.compareTo(list.get(indexTocheck).getKey()) == 0){
                returnObject = list.get(indexTocheck).getValue();
            }
        }

        return returnObject;
    }
    private int indexOfNodeWithKey(K key, int begIndex, int endIndex){
        int returnValue = -1;
        int indexTocheck = (begIndex + endIndex) / 2;
        if(begIndex == endIndex){
            if(key.compareTo(list.get(begIndex).getKey()) == 0){
                returnValue = begIndex;
            }
        }
        else {
            if(key.compareTo(list.get(indexTocheck).getKey()) < 0){
                returnValue = indexOfNodeWithKey(key, begIndex, indexTocheck);
            }
            else if(key.compareTo(list.get(indexTocheck).getKey()) > 0){
                returnValue = indexOfNodeWithKey(key, indexTocheck + 1, endIndex);
            }
            else if(key.compareTo(list.get(indexTocheck).getKey()) == 0){
                returnValue = indexTocheck;
            }
        }

        return returnValue;
    }
    private int indexOfNodeWithKey(Object key){
        int returnValue = -1;
        if(!isEmpty()){
            returnValue = indexOfNodeWithKey((K) key, 0, list.size() - 1);
        }
        return returnValue;
    }
    private int indexOfFirstGreater(K key, int begIndex, int endIndex){
        int indexToCheck = (begIndex + endIndex) / 2;
        int indexOfFirstGreater = -1;
        if(begIndex == endIndex){
            if(key.compareTo(list.get(begIndex).getKey()) > 0){
                indexOfFirstGreater = begIndex + 1;
            }
            else{
                indexOfFirstGreater = begIndex;
            }
        }
        else {
            if(key.compareTo(list.get(indexToCheck).getKey()) < 0){
                indexOfFirstGreater = indexOfFirstGreater(key, begIndex, indexToCheck);
            }
            else if(key.compareTo(list.get(indexToCheck).getKey()) > 0){
                indexOfFirstGreater = indexOfFirstGreater(key, indexToCheck + 1, endIndex);
            }
        }
        return indexOfFirstGreater;
    }
    private int indexOfFirstGreater(Object key){
        int indexOfFirstGreater = -1;
        if(isEmpty()){
            indexOfFirstGreater = 0;
        }
        else{
            indexOfFirstGreater = indexOfFirstGreater((K) key, 0, list.size() - 1);
        }
        return indexOfFirstGreater;
    }
}
