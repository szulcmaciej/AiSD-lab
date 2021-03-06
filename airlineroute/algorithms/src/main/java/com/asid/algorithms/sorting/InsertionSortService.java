package com.asid.algorithms.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.messages.StandardMessages;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Insertion sort algorithm
 */
public class InsertionSortService extends AbstractSortService {

    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          /* (TODO Lab No. 3) Please introduce a sensible implementation */

        long startTime = System.currentTimeMillis();


        for(int i = 1; i < list.size(); i++){
            if(less(list.get(i), list.get(i - 1), comparator)){
                int indexToInsertElement = 0;
                while(indexToInsertElement < i && less(list.get(indexToInsertElement), list.get(i), comparator)){
                    indexToInsertElement++;
                }
                T elementToInsert = list.get(i);
                list.remove(i);
                list.add(indexToInsertElement, elementToInsert);
            }
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


        long startTime = System.currentTimeMillis();

        List<T> resultList = new CustomArrayList<>();
        for(int i = 0; i < list.size(); i++){
            int indexToInsertElement = 0;
            while(indexToInsertElement < resultList.size() && less(resultList.get(indexToInsertElement), list.get(i), comparator)){
                indexToInsertElement++;
            }
            resultList.add(indexToInsertElement, list.get(i));
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(resultList);
        sortResult.setEstimatedTime(elapsedTime);


        return sortResult;
    }
*/



    private static <T> List<T> copyList(List<T> source, List<T> emptyList){
        for(T t : source){
            emptyList.add(t);
        }
        return emptyList;
    }
}
