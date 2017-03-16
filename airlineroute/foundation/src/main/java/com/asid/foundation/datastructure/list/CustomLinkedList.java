package com.asid.foundation.datastructure.list;

import java.util.Iterator;

/**
 * List based on recursively related objects
 *
 * @param <T>
 */
public class CustomLinkedList<T> extends AbstractCustomListAdapter<T> {
    private int size;
    private final Node<T> head_and_tail = new Node<>(null);

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
        Node<T> n = head_and_tail.getNext();
        Node<T> previous = head_and_tail;
        while(n != head_and_tail && !o.equals(n.getValue())){
            previous = n;
            n = n.getNext();
        }
        if(n != head_and_tail){
            previous.setNext(n.getNext());
            size--;
            return true;
        }
        else
            return false;
    }

    @Override
    public void clear() {
        head_and_tail.setNext(head_and_tail);
        size = 0;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        return (T) getNode(index).getValue();
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        Node<T> node = getNode(index);
        T old_value = node.getValue();
        node.setValue(element);
        return old_value;
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node<T> new_node = new Node<>(element);
        if(index == 0){
            new_node.setNext(head_and_tail.getNext());
            head_and_tail.setNext(new_node);
        }
        else{
            new_node.attachAfter(getNode(index - 1));
        }
        size++;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        T return_value = getNode(index).getValue();
        if(index == 0){
            head_and_tail.setNext(head_and_tail.getNext().getNext());
        }
        else{
            Node<T> previous_node = getNode(index - 1);
            previous_node.setNext(previous_node.getNext().getNext());
        }
        size--;
        return return_value;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<T> n = head_and_tail.getNext();
        while (n != head_and_tail && !o.equals(n.getValue())){
            n = n.getNext();
            index++;
        }
        return n != head_and_tail ? index : -1;
    }

    private Node<T> getNode(int index) throws IndexOutOfBoundsException{
        checkOutOfBounds(index);
        Node<T> node = head_and_tail.getNext();
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
        Node<E> previous_node;
        Node<E> current_node;

        public CustomLinkedListIterator(){
            current_node = (Node<E>) head_and_tail;
            previous_node = (Node<E>) head_and_tail;
        }

        @Override
        public boolean hasNext() {
            return current_node.getNext() != head_and_tail;
        }

        @Override
        public E next() {
            previous_node = current_node;
            current_node = current_node.getNext();
            return current_node.getValue();
        }

        @Override
        public void remove() {
            CustomLinkedList.this.remove(current_node.getValue());
        }
    }
}
