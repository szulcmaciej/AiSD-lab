package com.asid.foundation.datastructure.symbolTable;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 2017-04-27.
 */
public class RedBlackBinarySearchTreeSTTest {
    @Test
    public void shouldBeEmpty(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act

        //assert
        assertTrue(symbolTable.isEmpty());
    }
    @Test
    public void shouldNotBeEmpty(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(4, "cztery");

        //assert
        assertFalse(symbolTable.isEmpty());
    }
    @Test
    public void shouldReturnNullOnRemovingFromEmptyST(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act

        //assert
        assertNull(symbolTable.remove(1));
    }
    @Test
    public void shouldAddAndReturnKeyValuePairs(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(1, "jeden");
        symbolTable.put(2, "dwa");
        symbolTable.put(3, "trzy");

        //assert
        assertEquals("jeden", symbolTable.get(1));
        assertEquals("dwa", symbolTable.get(2));
        assertEquals("trzy", symbolTable.get(3));
        assertNull(symbolTable.get(4));
        assertNull(symbolTable.get(0));
    }
    @Test
    public void shouldAddAndRemoveKeyValuePairs(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(1, "jeden");
        symbolTable.put(2, "dwa");
        symbolTable.put(3, "trzy");

        //assert
        assertEquals("jeden", symbolTable.remove(1));
        assertEquals("dwa", symbolTable.remove(2));
        assertEquals("trzy", symbolTable.remove(3));
        assertTrue(symbolTable.isEmpty());
        assertNull(symbolTable.remove(4));
        assertNull(symbolTable.remove(0));
    }
    @Test
    public void shouldReturnCorrectSize(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(1, "jeden");
        symbolTable.put(2, "dwa");
        symbolTable.put(3, "trzy");

        //assert
        assertEquals(3, symbolTable.size());
    }
    @Test
    public void shouldReturnTrueForContainsKey(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(1, "jeden");
        symbolTable.put(2, "dwa");
        symbolTable.put(3, "trzy");

        //assert
        assertTrue(symbolTable.containsKey(2));
    }
    @Test
    public void shouldReturnFalseForContainsKey(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(1, "jeden");
        symbolTable.put(2, "dwa");
        symbolTable.put(3, "trzy");

        //assert
        assertFalse(symbolTable.containsKey(5));
    }
    @Test
    public void shouldReturnKeySet(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act
        symbolTable.put(1, "jeden");
        symbolTable.put(2, "dwa");
        symbolTable.put(3, "trzy");
        Set keySet = symbolTable.keySet();

        //assert
        assertTrue(keySet.contains(1));
        assertTrue(keySet.contains(2));
        assertTrue(keySet.contains(3));
        assertFalse(keySet.contains(4));
        assertEquals(3, keySet.size());
    }
    @Test
    public void shouldReturnSizeZero(){
        //pre
        RedBlackBinarySearchTreeST<Integer, String> symbolTable = new RedBlackBinarySearchTreeST<>();

        //act

        //assert
        assertEquals(0, symbolTable.size());
    }

}
