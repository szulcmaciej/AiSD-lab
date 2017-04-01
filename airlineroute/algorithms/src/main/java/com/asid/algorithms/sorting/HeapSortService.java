package com.asid.algorithms.sorting;

import com.asid.foundation.datatype.CustomPriorityQueue;
import com.asid.foundation.messages.StandardMessages;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * HeapSort sort algorithm
 */
public class HeapSortService extends AbstractSortService {

    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          /* (TODO Lab No. 5) Please introduce a sensible implementation */
        //throw new UnsupportedOperationException(StandardMessages.METHOD_NOT_IMPLEMENTED);

        long startTime = System.currentTimeMillis();

        CustomPriorityQueue<T> heap = new CustomPriorityQueue<>(list.size(), comparator);
        //tworzenie kopca
        for (int i = 0; i < list.size(); i++) {
            heap.add(list.get(i));
        }
        //zdejmowanie z kopca
        for (int i = 0; i < list.size(); i++) {
            list.set(i, heap.poll());
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(list);
        sortResult.setEstimatedTime(elapsedTime);

        return sortResult;
    }
}
