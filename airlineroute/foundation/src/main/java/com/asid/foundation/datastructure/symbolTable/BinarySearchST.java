package com.asid.foundation.datastructure.symbolTable;

import com.asid.foundation.datastructure.list.CustomArrayList;

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
        boolean containsKey = false;
        if(!isEmpty()){
            containsKey = containsKey((K) key, 0, list.size() - 1);
        }
        return containsKey;
    }

    @Override
    public Object get(Object key) {
        Object returnObject = null;
        if(!isEmpty()){
            returnObject = get((K) key, 0, list.size() - 1);
        }
        return returnObject;
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
}
