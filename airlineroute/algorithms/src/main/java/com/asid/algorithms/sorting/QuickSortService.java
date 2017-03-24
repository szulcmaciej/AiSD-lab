package com.asid.algorithms.sorting;

import com.asid.foundation.messages.StandardMessages;

import java.util.Comparator;
import java.util.List;

/**
 * Quick sort algorithm
 */
public class QuickSortService extends AbstractSortService {
    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          /* (TODO Lab No. 4) Please introduce a sensible implementation */
        //throw new UnsupportedOperationException(StandardMessages.METHOD_NOT_IMPLEMENTED);

        long startTime = System.currentTimeMillis();

        if(!list.isEmpty()){
            quickSort(list, comparator, 0, list.size() - 1);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(list);
        sortResult.setEstimatedTime(elapsedTime);

        return sortResult;
    }

    private static <T> void quickSort(List<T> list, Comparator<? super T> comparator, int begIndex, int endIndex){
/*
        if(begIndex < endIndex){
            int pivot = begIndex;
            int wall = pivot;
            int i = begIndex + 1;
            while (i <= endIndex){ //czy na pewno?
                if(comparator.compare(list.get(i), list.get(pivot)) < 0){
                    wall++;
                    exch(list, i, wall);
                }
            }
            exch(list, pivot, wall);

            quickSort(list, comparator, begIndex, wall);
            if(wall < endIndex){
                quickSort(list, comparator, wall + 1, endIndex);
            }
        }
*/
        if(begIndex < endIndex){
            int i = begIndex;
            int j = endIndex;
            T pivot = list.get((begIndex + endIndex)/2);

            while(i < j){
                while (i < list.size() - 1 && comparator.compare(list.get(i), pivot) < 0){
                    i++;
                }
                while (j > 0 && comparator.compare(list.get(j), pivot) > 0){
                    j--;
                }
                if (i < j) {
                    exch(list, i, j);
                    i++;
                    j--;
                }
            }
            quickSort(list, comparator, begIndex, j);
            quickSort(list, comparator, j+1, endIndex);
        }

    }
}