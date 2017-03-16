package com.asid.foundation.datastructure.list;

/**
 * Component of linked list that stores a value and reference to the next element.
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value){
        setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void attachAfter(Node<T> previous){
        next = previous.next;
        previous.next = this;
    }
}
