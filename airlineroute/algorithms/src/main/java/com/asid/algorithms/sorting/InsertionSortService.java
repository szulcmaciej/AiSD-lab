package com.asid.algorithms.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.messages.StandardMessages;

import java.util.Comparator;
import java.util.List;

/**
 * Insertion sort algorithm
 */
public class InsertionSortService extends AbstractSortService {

    public static <T> SortResultDs<T> sort(List<T> list, Comparator<? super T> comparator) {
          /* (TODO Lab No. 3) Please introduce a sensible implementation */
        //throw new UnsupportedOperationException(StandardMessages.METHOD_NOT_IMPLEMENTED);

        List<T> resultList = new CustomArrayList<>();
        for(int i = 0; i < list.size(); i++){
            int indexToInsertElement = 0;
            while(indexToInsertElement < resultList.size() && less(resultList.get(indexToInsertElement), list.get(i), comparator)){
                indexToInsertElement++;
            }
            resultList.add(indexToInsertElement, list.get(i));
        }


        SortResultDs<T> sortResult = new SortResultDs<>();
        sortResult.setResult(resultList);

        return sortResult;
    }



    private static <T> List<T> copyList(List<T> source, List<T> emptyList){
        for(T t : source){
            emptyList.add(t);
        }
        return emptyList;
    }






    public static void main(String args[]){
        List<Double> list = new CustomArrayList<>();
        Comparator<Double> naturalComparator = Comparator.naturalOrder();
        for(int i = 0; i < 10; i++){
            list.add(Math.random()*10);
        }
        for(Double d : list){
            System.out.println(d);
        }

        System.out.println();

        List<Double> sortedList = sort(list, naturalComparator).getResult();
        for(Double d :sortedList)
        {
            System.out.println(d);
        }
    }
}
