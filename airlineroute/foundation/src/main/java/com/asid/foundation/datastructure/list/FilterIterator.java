package com.asid.foundation.datastructure.list;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Filter iterator
 */
public class FilterIterator<T> implements Iterator<T> {
    /* (TODO Lab No. 2) Please introduce a sensible implementation */
    private List<T> list;
    private Predicate<T> predicate;
    private Iterator<T> iterator;
    private T current;
    private T elementToRemove;

    public FilterIterator(List<T> list, Predicate<T> predicate) {
         /* (TODO Lab No. 2) Please introduce a sensible implementation */
        this.list = list;
        this.predicate = predicate;
        iterator = list.iterator();
        current = null;
        elementToRemove = null;
    }

    @Override
    public boolean hasNext() {
         /* (TODO Lab No. 2) Please introduce a sensible implementation */
        setCurrent();
        return current != null;
    }

    @Override
    public T next() {
         /* (TODO Lab No. 2) Please introduce a sensible implementation */
        setCurrent();
        T returnValue = current;
        current = null;
        elementToRemove = returnValue;
        return returnValue;
    }

    @Override
    public void remove() {
        /* (TODO Lab No. 2) Please introduce a sensible implementation */
        if(elementToRemove != null){
            list.remove(elementToRemove);
        }
    }

    private void setCurrent(){
        while(current == null && iterator.hasNext()){
            current = iterator.next();
            if(!predicate.test(current)){
                current = null;
            }
        }
    }
}
