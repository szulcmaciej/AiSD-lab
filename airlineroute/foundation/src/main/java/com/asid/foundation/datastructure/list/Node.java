package com.asid.foundation.datastructure.list;

/**
 * Component of linked list that stores a value and reference to the next element.
 */
public class Node {
      /* (TODO Lab No. 1) Please introduce a sensible implementation */
    private Object value;
    private Node next;

    public Node(Object value){
        setValue(value);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void attachAfter(Node previous){
        next = previous.next;
        previous.next = this;
    }
}
