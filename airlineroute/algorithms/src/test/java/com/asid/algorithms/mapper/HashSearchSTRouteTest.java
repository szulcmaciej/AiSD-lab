package com.asid.algorithms.mapper;

import com.asid.algorithms.entity.Airline;
import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.entity.Route;
import com.asid.foundation.datastructure.symbolTable.HashSearchST;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 2017-04-27.
 */
public class HashSearchSTRouteTest {
    @Test
    public void shouldReturnTrueForContainsKeyRoute(){
        //pre
        HashSearchST<Route, Integer> symbolTable = new HashSearchST<>();
        Route[] routes = new Route[10];
        for(int i = 0; i < 10; i++){
            routes[i] = new Route(new Airline("A" + i*3 , "line" + i*7),
                    new Airport("iata" + i/3, i*5, i/3, "a" + i%2),
                    new Airport("iata" + i/2, i*2, i/4, "a" + i%3));
        }

        Route route5 = routes[5];
        Route route4 = routes[4];

        //act
        symbolTable.put(route5, 15);

        //assert
        assertEquals(1, symbolTable.size());
        assertFalse(symbolTable.isEmpty());
        assertTrue(symbolTable.containsKey(route5));
        assertFalse(symbolTable.containsKey(route4));
        assertEquals(15, symbolTable.get(route5));
        assertNull(symbolTable.get(route4));
    }
}
