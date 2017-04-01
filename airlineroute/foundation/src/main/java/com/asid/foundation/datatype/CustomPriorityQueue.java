package com.asid.foundation.datatype;

import com.asid.foundation.datastructure.list.CustomArrayList;

import java.util.Comparator;
import java.util.List;

/**
 * CustomPriorityQueue data type.
 */
public class CustomPriorityQueue<T> extends AbstractCustomPriorityQueueAdapter<T> {
    private Comparator<? super T> comparator = null;
    private List<T> list = null;
    /* (TODO Lab No. 5) Please introduce a sensible implementation */

    public CustomPriorityQueue(int initialCapacity, Comparator<? super T> comparator) {
        this.comparator = comparator;
        list = new CustomArrayList<>(initialCapacity);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(T t) {
        if(isEmpty()){
            list.add(t);
        }
        else {
            int index = list.size();
            list.add(index, t);
            siftUp(index);
        }
        return true;
    }

    /**
     * Removes all of the elements from this priority queue.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return
     */
    @Override
    public T poll() {
        T objectToReturn = null;
        if(!isEmpty()){
            objectToReturn = list.get(0);
            list.set(0, list.get(size() - 1));
            list.remove(size() - 1);
            siftDown(0);
        }
        return objectToReturn;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return
     */
    @Override
    public T peek() {
        T objectToReturn = null;
        if(!isEmpty()){
            objectToReturn = list.get(0);
        }
        return objectToReturn;
    }

    private void siftUp(int index){
        //może rekursywnie?
        int currentIndex = index;
        int parentIndex = (currentIndex - 1) / 2;
        while (currentIndex > 0 && comparator.compare(list.get(currentIndex), list.get(parentIndex)) < 0){
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
    }

    private void siftDown(int index){
        int biggerChildIndex = biggerChildIndex(index);
        if(biggerChildIndex > 0 && comparator.compare(list.get(index), list.get(biggerChildIndex)) > 0){
            swap(index, biggerChildIndex);
            siftDown(biggerChildIndex);
        }
    }

    private void swap(int i1, int i2){
        T temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, temp);
    }

    private boolean hasAtLeastOneChild(int index){
        return index * 2 + 1 < list.size();
    }
    private int biggerChildIndex(int index){
        if(hasAtLeastOneChild(index)){
            if(index * 2 + 2 < list.size()){
                if(comparator.compare(list.get(index * 2 + 1), list.get(index * 2 + 2)) > 0){
                    return index * 2 + 2;
                }
                else {
                    return index * 2 + 1;
                }
            }
            else {
                return index * 2 + 1;
            }
        }
        else return -1;
    }
}
