package com.asid.algorithms.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.messages.StandardMessages;

import java.util.Comparator;
import java.util.List;

/**
 * Selection sort algorithm.
 */
public class SelectionSortService extends AbstractSortService {

    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          /* (TODO Lab No. 3) Please introduce a sensible implementation */

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < list.size() - 1; i++){
            int minValueIndex = i;
            for(int j = i + 1; j < list.size(); j++){
                if(less(list.get(j), list.get(minValueIndex), comparator)){
                    minValueIndex = j;
                }
            }
            exch(list, i, minValueIndex);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(list);
        sortResult.setEstimatedTime(elapsedTime);

        return sortResult;
    }
/*
    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          */
/* (TODO Lab No. 3) Please introduce a sensible implementation *//*

        //throw new UnsupportedOperationException(StandardMessages.METHOD_NOT_IMPLEMENTED);

        long startTime = System.currentTimeMillis();

        List<T> resultList = copyList(list,new CustomArrayList<>());
        for(int i = 0; i < resultList.size() - 1; i++){
            int minValueIndex = i;
            for(int j = i + 1; j < resultList.size(); j++){
                if(less(resultList.get(j), resultList.get(minValueIndex), comparator)){
                    minValueIndex = j;
                }
            }
            exch(resultList, i, minValueIndex);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(resultList);
        sortResult.setEstimatedTime(elapsedTime);

        return sortResult;
    }
*/


/*
    private static <T> List<T> copyList(List<T> source, List<T> emptyList){
        for(T t : source){
            emptyList.add(t);
        }
        return emptyList;
    }
*/

}
