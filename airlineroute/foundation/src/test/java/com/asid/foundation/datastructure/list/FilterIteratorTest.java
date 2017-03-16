package com.asid.foundation.datastructure.list;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 2017-03-16.
 */
public class FilterIteratorTest {
    private List<Integer> createEmptyList(){
        return new CustomArrayList<>();
        //return new CustomLinkedList<>();
    }
    private Predicate<Integer> createEvenPredicate(){
        return integer -> integer % 2 == 0;
    }

    @Test
    public void shouldReturnNullOnEmptyList(){
        //Pre
        List<Integer> emptyList = createEmptyList();
        Predicate<Integer> predicate = createEvenPredicate();
        FilterIterator<Integer> filterIterator = new FilterIterator<>(emptyList, predicate);

        //Assert
        assertFalse(filterIterator.hasNext());
        assertNull(filterIterator.next());
    }
    @Test
    public void shouldReturnNullOnListWithNoElementsMatchingThePredicate(){
        //Pre
        List<Integer> list = createEmptyList();
        Predicate<Integer> predicate = createEvenPredicate();
        FilterIterator<Integer> filterIterator = new FilterIterator<>(list, predicate);

        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);

        //Assert
        assertFalse(filterIterator.hasNext());
        assertNull(filterIterator.next());
    }
    @Test
    public void shouldNotRemoveOnListWithNoElementsMatchingThePredicate(){
        //Pre
        List<Integer> list = createEmptyList();
        Predicate<Integer> predicate = createEvenPredicate();
        FilterIterator<Integer> filterIterator = new FilterIterator<>(list, predicate);

        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);

        //Act
        filterIterator.remove();
        filterIterator.hasNext();
        filterIterator.remove();
        filterIterator.next();
        filterIterator.remove();

        //Assert
        assertEquals(1,(int) list.get(0));
        assertEquals(3,(int) list.get(1));
        assertEquals(5,(int) list.get(2));
        assertEquals(7,(int) list.get(3));
        assertEquals(9,(int) list.get(4));
    }
    @Test
    public void shouldReturnElementsMatchingThePredicate(){
        //Pre
        List<Integer> list = createEmptyList();
        Predicate<Integer> predicate = createEvenPredicate();
        FilterIterator<Integer> filterIterator = new FilterIterator<>(list, predicate);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Act

        //Assert
        assertTrue(filterIterator.hasNext());
        assertEquals(2,(int) filterIterator.next());
        assertEquals(4,(int) filterIterator.next());
        assertFalse(filterIterator.hasNext());
        assertNull(filterIterator.next());
        assertFalse(filterIterator.hasNext());
    }
    @Test
    public void shouldRemoveElementsMatchingThePredicate(){
        //Pre
        List<Integer> list = createEmptyList();
        Predicate<Integer> predicate = createEvenPredicate();
        FilterIterator<Integer> filterIterator = new FilterIterator<>(list, predicate);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Act
        filterIterator.next();
        filterIterator.remove();

        //Assert
        assertEquals(4,(int) filterIterator.next());
        assertNull(filterIterator.next());

        assertEquals(1,(int) list.get(0));
        assertEquals(3,(int) list.get(1));
        assertEquals(4,(int) list.get(2));
        assertEquals(5,(int) list.get(3));
    }
    @Test
    public void shouldNotRemoveElementsWhenLastGivenElementIsNull(){
        //Pre
        List<Integer> list = createEmptyList();
        Predicate<Integer> predicate = createEvenPredicate();
        FilterIterator<Integer> filterIterator = new FilterIterator<>(list, predicate);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Act
        filterIterator.remove();
        filterIterator.next();
        filterIterator.next();
        filterIterator.next();

        //Assert
        assertNull(filterIterator.next());
        filterIterator.remove();
        assertEquals(1,(int) list.get(0));
        assertEquals(2,(int) list.get(1));
        assertEquals(3,(int) list.get(2));
        assertEquals(4,(int) list.get(3));
        assertEquals(5,(int) list.get(4));
    }
}
