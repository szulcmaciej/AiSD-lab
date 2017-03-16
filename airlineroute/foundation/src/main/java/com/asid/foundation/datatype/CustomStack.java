package com.asid.foundation.datatype;

import java.util.List;

/**
 * Stack, LIFO queue
 */
public class CustomStack<T> extends AbstractCustomStackAdapter<T> {
    List<T> storage = null;

    /* (TODO Lab No. 2) Please introduce a sensible implementation */
    public CustomStack(List<T> storage) {
        this.storage = storage;
    }

    @Override
    public void push(T t) {
        /* (TODO Lab No. 2) Please introduce a sensible implementation */
        storage.add(t);
    }

    @Override
    public T pop() {
        /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.remove(storage.size() - 1);
    }

    @Override
    public int size() {
        /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return storage.size() == 0;
    }
}
