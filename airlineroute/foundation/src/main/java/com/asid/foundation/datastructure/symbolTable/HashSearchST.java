package com.asid.foundation.datastructure.symbolTable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lenovo on 2017-04-27.
 */
public class HashSearchST <K extends Comparable, V> extends AbstractSymbolTable {
    private int N;
    private int M;
    private SequentialSearchST<K, V>[] st;

    public HashSearchST(int M){
        this.M = M;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++){
            st[i] = new SequentialSearchST();
        }
    }

    public HashSearchST() {
        this(997);
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int size() {
        int size = 0;
        for(SequentialSearchST t : st){
            size += t.size();
        }
        return size;
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
        return st[hash((K) key)].get(key);
    }

    @Override
    public Object put(Comparable key, Object value) {
        return st[hash((K) key)].put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return st[hash((K) key)].remove(key);
    }

    @Override
    public Set keySet() {
        Set<K> set = new HashSet<>();
        for(SequentialSearchST t : st){
            set.addAll(t.keySet());
        }
        return set;
    }

    @Override
    public Object put(Object key, Object value) {
        return put((Comparable) key, value);
    }
}
