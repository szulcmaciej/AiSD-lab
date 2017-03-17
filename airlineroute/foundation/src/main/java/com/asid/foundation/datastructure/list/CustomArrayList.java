package com.asid.foundation.datastructure.list;

import java.util.Iterator;

/**
 * List based on the table
 *
 * @param <T>
 */
public class CustomArrayList<T> extends AbstractCustomListAdapter<T> {
    static final int DEFAULT_INITIAL_CAPACITY = 20;
    static final double RESIZING_FACTOR = 1.3;

    private int initialCapacity;
    private T[] array;
    private int size;


    public CustomArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public CustomArrayList(int initialCapacity) {
        this.initialCapacity = initialCapacity;
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
        return new CustomArrayListIterator<>();
    }

    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index != -1){
            remove(index);
        }
        return index != -1;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException{
        /* (TODO Lab No. 1) Please introduce a sensible implementation */
        checkOutOfBounds(index);
        return array[index];
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException{
        checkOutOfBounds(index);
        T oldValue = array[index];
        array[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {   //insert
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        T value = array[index];
        int copyFrom = index + 1;
        if(copyFrom < size){
            System.arraycopy(array, copyFrom, array, index, size - copyFrom);
        }
        size--;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        while(i < size && !o.equals(array[i])){
            i++;
        }
        return i < size ? i : -1;
    }

    private void ensureCapacity(int capacity){
        if(capacity > 0.9 * array.length){
            int newLength = (int) (capacity * RESIZING_FACTOR);
            adjustLength(newLength);
        }
        else if(capacity < 0.6 * array.length){
            int newLength = (int) (capacity * RESIZING_FACTOR);
            adjustLength(newLength);
        }
    }

    private void adjustLength(int newLength){
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkOutOfBounds(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    /**
     * Iterator for CustomArrayList
     */
    private class CustomArrayListIterator<E> implements Iterator<E> {
        int currentIndex;

        public CustomArrayListIterator() {
            currentIndex = -1;
        }

        @Override
        public boolean hasNext() {
             /* (TODO Lab No. 1) Please introduce a sensible implementation */
            if(currentIndex < size - 1)
                return  true;
            else
                return false;
        }

        @Override
        public E next() {
             /* (TODO Lab No. 1) Please introduce a sensible implementation */
            E returnValue = null;
            if(hasNext())
            {
                currentIndex++;
                returnValue = (E) array[currentIndex];
            }
            return returnValue;
        }

        @Override
        public void remove() {
             /* (TODO Lab No. 1) Please introduce a sensible implementation */
            CustomArrayList.this.remove(currentIndex);
            currentIndex--;
        }
    }
}
