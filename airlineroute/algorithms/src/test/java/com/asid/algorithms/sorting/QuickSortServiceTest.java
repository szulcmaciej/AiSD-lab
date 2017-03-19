package com.asid.algorithms.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test for quick sort algorithm.
 */
public class QuickSortServiceTest {
   /* (TODO Lab No. 3) Please introduce a sensible implementation */

    static final double DELTA = 0.001;

    @Test
    public void shouldSortListOfDoubles(){
        //Pre
        List<Double> list = new CustomArrayList<>();
        Comparator<Double> naturalComparator = Comparator.naturalOrder();
        list.add(6.5);
        list.add(4.0);
        list.add(3.8);
        list.add(7.7);
        list.add(1.1);
        list.add(7.7);
        list.add(2.2);
        list.add(3.8);

        //Act
        List<Double> sortedList = QuickSortService.sort(list, naturalComparator).getResult();

        //Assert
        assertEquals(8, sortedList.size());
        assertEquals(1.1, sortedList.get(0), DELTA);
        assertEquals(2.2, sortedList.get(1), DELTA);
        assertEquals(3.8, sortedList.get(2), DELTA);
        assertEquals(3.8, sortedList.get(3), DELTA);
        assertEquals(4.0, sortedList.get(4), DELTA);
        assertEquals(6.5, sortedList.get(5), DELTA);
        assertEquals(7.7, sortedList.get(6), DELTA);
        assertEquals(7.7, sortedList.get(7), DELTA);
    }

    @Test
    public void shouldReturnSameListOnSortedList(){
        //Pre
        List<Double> list = new ArrayList<>();
        Comparator<Double> naturalComparator = Comparator.naturalOrder();
        list.add(6.5);
        list.add(4.0);
        list.add(3.8);
        list.add(7.7);
        list.add(1.1);
        list.add(7.7);
        list.add(2.2);
        list.add(3.8);


        java.util.Collections.sort(list, naturalComparator);

        List<Double> listCopy = new ArrayList<>();
        for(Double d : list){
            listCopy.add(d);
        }

        //Act
        QuickSortService.sort(list, naturalComparator);

        //Assert
        assertEquals(8, list.size());
        for (int i = 0; i < listCopy.size(); i++) {
            assertEquals(list.get(i), listCopy.get(i), DELTA);
        }
    }

    @Test
    public void shouldSortListOfSimilarElements(){
        //Pre
        List<Double> list = new CustomArrayList<>();
        Comparator<Double> naturalComparator = Comparator.naturalOrder();
        list.add(6.5);
        list.add(6.5);
        list.add(6.5);
        list.add(6.5);
        list.add(6.5);
        list.add(6.5);
        list.add(6.5);
        list.add(6.5);


        //Act
        List<Double> sortedList = QuickSortService.sort(list, naturalComparator).getResult();

        //Assert
        assertEquals(8, sortedList.size());
        assertEquals(6.5, sortedList.get(0), DELTA);
        assertEquals(6.5, sortedList.get(1), DELTA);
        assertEquals(6.5, sortedList.get(2), DELTA);
        assertEquals(6.5, sortedList.get(3), DELTA);
        assertEquals(6.5, sortedList.get(4), DELTA);
        assertEquals(6.5, sortedList.get(5), DELTA);
        assertEquals(6.5, sortedList.get(6), DELTA);
        assertEquals(6.5, sortedList.get(7), DELTA);
    }

}