package com.asid.algorithms.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.messages.StandardMessages;

import java.util.Comparator;
import java.util.List;

/**
 * Merge sort algorithm algorithm
 */
public class MergeSortService extends AbstractSortService {

    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          /* (TODO Lab No. 4) Please introduce a sensible implementation */
        //throw new UnsupportedOperationException(StandardMessages.METHOD_NOT_IMPLEMENTED);

        long startTime = System.currentTimeMillis();

        List<T> resultList = mergeSort(list, comparator);
        list.clear();
        for(T t : resultList){
            list.add(t);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(list);
        sortResult.setEstimatedTime(elapsedTime);

        return sortResult;
    }

    private static <T> List<T> mergeSort(List<T> list, Comparator<? super T> comparator){
        List<T> resultList;
        if(list.size() == 1){
            resultList = list;
        }
        else{
            int beg = 0;
            int mid = (list.size() - 1) / 2;
            int end = list.size() - 1;

            List<T> list1 = copyListFromIndexToIndex(list, beg, mid);
            List<T> list2 = copyListFromIndexToIndex(list, mid + 1, end);

            resultList = merge(mergeSort(list1, comparator), mergeSort(list2, comparator), comparator);
        }

        return resultList;
    }

    private static <T> List<T> merge(List<T> list1, List<T> list2, Comparator<? super T> comparator){
        List<T> resultList = new CustomArrayList<>(list1.size() + list2.size());
        int i1 = 0;
        int i2 = 0;
        while(i1 < list1.size() || i2 < list2.size()){
            if (i1 < list1.size() && i2 < list2.size()) {
                if(comparator.compare(list1.get(i1), list2.get(i2)) < 0){
                    resultList.add(list1.get(i1));
                    i1++;
                }
                else{
                    resultList.add(list2.get(i2));
                    i2++;
                }
            }
            else if(i1 < list1.size()){
                while (i1 < list1.size()){
                    resultList.add(list1.get(i1));
                    i1++;
                }
            }
            else{
                while (i2 < list2.size()){
                    resultList.add(list2.get(i2));
                    i2++;
                }
            }
        }

        return resultList;
    }

    private static <T> List<T> copyListFromIndexToIndex(List<T> sourceList, int beg, int end){
        List<T> resultList = new CustomArrayList<>(end - beg);
        int i = beg;
        while(i <= end){
            resultList.add(sourceList.get(i));
            i++;
        }
        return resultList;
    }

}
