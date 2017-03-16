package com.asid.algorithms.mapper;

import com.asid.algorithms.database.DataLoaderImpl;
import com.asid.algorithms.database.InmemmoryDataBase;
import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.entity.Route;
import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.datastructure.list.FilterIterator;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Lenovo on 2017-03-16.
 */
public class RouteFilterTest {
    InmemmoryDataBase dataBase;

    public RouteFilterTest() {
        dataBase = InmemmoryDataBase.getInstance();
        DataLoaderImpl dataLoader = new DataLoaderImpl();
        dataLoader.loadAllData("airports.dat", "airlines.dat", "routes.dat");
    }

    List<Route> getRoutesTo(String airportCode){
        Predicate<Route> predicate = new Predicate<Route>() {
            @Override
            public boolean test(Route route) {
                return route.getDestination().getIataCode().equals(airportCode);
            }
        };
        FilterIterator<Route> filterIterator = new FilterIterator<>(dataBase.getRoutes(), predicate);
        List<Route> filteredList = new CustomArrayList<>();
        while(filterIterator.hasNext()){
            filteredList.add(filterIterator.next());
        }
        return filteredList;
    }

    void printRoutesTo(String airportCode){
        List<Route> routes = getRoutesTo(airportCode);
        for(Route route : routes){
            System.out.println(route);
        }
    }

    public static void main(String args[]){
        RouteFilterTest routeFilterTest = new RouteFilterTest();
        routeFilterTest.printRoutesTo("JFK");
    }
}
