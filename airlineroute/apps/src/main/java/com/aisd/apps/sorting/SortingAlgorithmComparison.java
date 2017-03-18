package com.aisd.apps.sorting;

import com.asid.algorithms.database.InmemmoryDataBase;
import com.asid.algorithms.entity.Route;

import java.util.Collections;
import java.util.Comparator;

/**
 * Sorting algorithms performance test.
 */
public class SortingAlgorithmComparison {

    InmemmoryDataBase dataBase = InmemmoryDataBase.getInstance();
    Comparator<Route> shortestDistanceComparator = (o1, o2) -> {
        double distance1 = Math.sqrt( Math.pow((o1.getDestination().getLatitude() - o1.getOrigin().getLatitude()), 2) + Math.pow((o1.getDestination().getLongitude() - o1.getOrigin().getLongitude()), 2)   );
        double distance2 = Math.sqrt( Math.pow((o2.getDestination().getLatitude() - o2.getOrigin().getLatitude()), 2) + Math.pow((o2.getDestination().getLongitude() - o2.getOrigin().getLongitude()), 2)   );
        return  ((Double) distance1).compareTo(distance2);
    };

    Comparator<Route> longestDistanceComparator = (o1, o2) -> -1 * shortestDistanceComparator.compare(o1, o2);


    public static void main(String[] args) {
         /* (TODO Lab No. 3) Please introduce a sensible implementation */
        


    }


}
