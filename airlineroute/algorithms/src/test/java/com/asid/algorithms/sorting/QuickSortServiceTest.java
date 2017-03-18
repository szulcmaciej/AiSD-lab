package com.asid.algorithms.sorting;

import com.asid.foundation.datastructure.list.CustomArrayList;
import org.junit.Test;

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

}