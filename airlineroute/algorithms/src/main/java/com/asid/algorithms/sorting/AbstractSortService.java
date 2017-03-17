package com.asid.algorithms.sorting;


import java.util.Comparator;
import java.util.List;

public abstract class AbstractSortService {

    /**
     * Method compares two objects using a comparator
     *
     * @param o1
     * @param o2
     * @param comparator
     * @param <T>
     * @return
     */
    protected static <T> boolean less(T o1, T o2, Comparator<? super T> comparator) {
        /* (TODO Lab No. 3) Please introduce a sensible implementation */
        return comparator.compare(o1, o2) < 0;
    }

    /**
     * Method swaps two objects in the list, using their position.
     *
     * @param list
     * @param o1Pos
     * @param o2Pos
     */
    protected static <T> void exch(List<T> list, int o1Pos, int o2Pos) {
        /* (TODO Lab No. 3) Please introduce a sensible implementation */
        T o1 = list.get(o1Pos);
        T o2 = list.get(o2Pos);
        if(o1Pos < o2Pos){
            list.remove(o2Pos);
            list.remove(o1Pos);
            list.add(o1Pos, o2);
            list.add(o2Pos, o1);
        }
        else if(o2Pos < o1Pos){
            list.remove(o1Pos);
            list.remove(o2Pos);
            list.add(o2Pos, o1);
            list.add(o1Pos, o2);
        }
        else{
            //indeksy są równe, nic nie rób
        }
    }

    /**
     * Method swaps two objects in the list, using their references.
     *
     * @param list
     * @param o1
     * @param o2
     */
    protected static <T> void exch(List<T> list, Object o1, Object o2) {
        /* (TODO Lab No. 3) Please introduce a sensible implementation */
        int o1Pos = list.indexOf((T) o1);
        int o2Pos = list.indexOf((T) o2);

        exch(list, o1Pos, o2Pos);
    }
}
