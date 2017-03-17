package com.asid.foundation.datastructure.list;

import java.util.Iterator;

/**
 * List based on recursively related objects
 *
 * @param <T>
 */
public class CustomLinkedList<T> extends AbstractCustomListAdapter<T> {
    private int size;
    private final Node<T> headAndTail = new Node<>(null);

    public CustomLinkedList(){
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        /* (TODO Lab No. 1) Please introduce a sensible implementation */
        return new CustomLinkedListIterator<>();
    }

    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> n = headAndTail.getNext();
        Node<T> previous = headAndTail;
        while(n != headAndTail && !o.equals(n.getValue())){
            previous = n;
            n = n.getNext();
        }
        if(n != headAndTail){
            previous.setNext(n.getNext());
            size--;
            return true;
        }
        else
            return false;
    }

    @Override
    public void clear() {
        headAndTail.setNext(headAndTail);
        size = 0;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        return getNode(index).getValue();
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        Node<T> node = getNode(index);
        T oldValue = node.getValue();
        node.setValue(element);
        return oldValue;
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node<T> newNode = new Node<>(element);
        if(index == 0){
            newNode.setNext(headAndTail.getNext());
            headAndTail.setNext(newNode);
        }
        else{
            newNode.attachAfter(getNode(index - 1));
        }
        size++;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        T returnValue = getNode(index).getValue();
        if(index == 0){
            headAndTail.setNext(headAndTail.getNext().getNext());
        }
        else{
            Node<T> previousNode = getNode(index - 1);
            previousNode.setNext(previousNode.getNext().getNext());
        }
        size--;
        return returnValue;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<T> n = headAndTail.getNext();
        while (n != headAndTail && !o.equals(n.getValue())){
            n = n.getNext();
            index++;
        }
        return n != headAndTail ? index : -1;
    }

    private Node<T> getNode(int index) throws IndexOutOfBoundsException{
        checkOutOfBounds(index);
        Node<T> node = headAndTail.getNext();
        for(int i = index; i > 0; --i){
            node = node.getNext();
        }
        return node;
    }

    private void checkOutOfBounds(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    /**
     * Iterator for CustomLinkedList
     */
    private class CustomLinkedListIterator<E> implements Iterator<E> {
        Node<E> previousNode;
        Node<E> currentNode;

        public CustomLinkedListIterator(){
            currentNode = (Node<E>) headAndTail;
            previousNode = (Node<E>) headAndTail;
        }

        @Override
        public boolean hasNext() {
            return currentNode.getNext() != headAndTail;
        }

        @Override
        public E next() {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
            return currentNode.getValue();
        }

        @Override
        public void remove() {
            CustomLinkedList.this.remove(currentNode.getValue());
        }
    }
}
