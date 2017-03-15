package com.asid.foundation.datatype;

import java.util.Iterator;
import java.util.List;

/**
 * FIFO queue
 */
public class CustomQueue<T> extends AbstractCustomQueueAdapter<T> {
    List<T> storage = null;

    /* (TODO Lab No. 2) Please introduce a sensible implementation */
    public CustomQueue(List<T> storage) {
        this.storage = storage;
    }
    
    @Override
    public int size() {
          /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
          /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.isEmpty();
    }

    @Override
    public boolean add(T t) {
          /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.add(t);
    }

    @Override
    public T poll() {
          /* (TODO Lab No. 2) Please introduce a sensible implementation */
        T returnValue = null;
        Iterator<T> iterator = storage.iterator();
        if(!isEmpty()){
            returnValue = iterator.next();
            iterator.remove();
        }
        return returnValue;
    }

    @Override
    public T peek() {
          /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.iterator().next();
    }
}
