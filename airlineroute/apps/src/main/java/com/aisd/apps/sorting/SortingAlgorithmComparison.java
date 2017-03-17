package com.aisd.apps.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;

import java.util.List;
import java.util.Random;

/**
 * Sorting algorithms performance test.
 */
public class SortingAlgorithmComparison {
    static final int DEFAULT_SAMPLE_LIST_SIZE = 10000;
    static final int DEFAULT_REPEATS = 5;

    public static void main(String[] args) {
         /* (TODO Lab No. 3) Please introduce a sensible implementation */

        int sampleListSize = DEFAULT_SAMPLE_LIST_SIZE;
        int repeats = DEFAULT_REPEATS;

        long insertionSortTimeSum, selectionSortTimeSum;

        List<Integer> unsortedList = new CustomArrayList<>(sampleListSize);
        Random random = new Random();
        for(int i = 0; i < sampleListSize; i++){
            unsortedList.add(random.nextInt(1000));
        }

        for(int i = 0; i < repeats; i++){
            //insertionSort


            //selectionSort


        }







    }


}
