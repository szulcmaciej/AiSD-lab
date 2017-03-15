package com.asid.algorithms.mapper;

import com.asid.algorithms.database.DataLoader;
import com.asid.algorithms.database.DataLoaderImpl;
import com.asid.algorithms.database.InmemmoryDataBase;
import com.asid.algorithms.entity.Airline;
import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.entity.Route;
import com.asid.foundation.datastructure.list.CustomArrayList;

import java.util.List;


/**
 * Created by Lenovo on 2017-03-15.
 */
public class MyTests {

    static void test(){
        DataLoader loader = new DataLoaderImpl();
        loader.loadAllData("airports.dat", "airlines.dat", "routes.dat");

        InmemmoryDataBase dataBase = InmemmoryDataBase.getInstance();
        List<Airline> airlines = dataBase.getAirlines();

        for(Airline a : airlines){
            System.out.println(a);
        }

        for(int i = 0; i < 100; i++){
            System.out.println();
        }

        List<Airport> airports = dataBase.getAirports();
        for(Airport airport : airports){
            System.out.println(airport);
        }

        for(int i = 0; i < 100; i++){
            System.out.println();
        }

        List<Route> routes = dataBase.getRoutes();
        for(Route route : routes){
            System.out.println(route);
        }
    }

    public static void main(String args[]){
        test();
    }
}
