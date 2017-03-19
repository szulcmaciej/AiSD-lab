package com.aisd.apps.sorting;

import com.asid.algorithms.database.DataLoaderImpl;
import com.asid.algorithms.database.InmemmoryDataBase;
import com.asid.algorithms.entity.Route;
import com.asid.algorithms.mapper.RouteMapper;
import com.asid.algorithms.sorting.*;
import com.asid.foundation.datastructure.list.CustomArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting algorithms performance test.
 */
public class SortingAlgorithmComparison {

    static final String AIRPORTS_FILE_PATH = "algorithms\\resources\\airports.dat";
    static final String AIRLINES_FILE_PATH = "algorithms\\resources\\airlines.dat";
    static final String ROUTES_FILE_PATH = "algorithms\\resources\\routes2.dat";

    static InmemmoryDataBase dataBase = InmemmoryDataBase.getInstance();
    static Comparator<Route> shortestDistanceComparator = (o1, o2) -> {
        double distance1 = Math.sqrt( Math.pow((o1.getDestination().getLatitude() - o1.getOrigin().getLatitude()), 2) + Math.pow((o1.getDestination().getLongitude() - o1.getOrigin().getLongitude()), 2)   );
        double distance2 = Math.sqrt( Math.pow((o2.getDestination().getLatitude() - o2.getOrigin().getLatitude()), 2) + Math.pow((o2.getDestination().getLongitude() - o2.getOrigin().getLongitude()), 2)   );
        return  ((Double) distance1).compareTo(distance2);
    };

    static Comparator<Route> longestDistanceComparator = (o1, o2) -> -1 * shortestDistanceComparator.compare(o1, o2);


    public static void main(String[] args) {
         /* (TODO Lab No. 3) Please introduce a sensible implementation */
        new SortingAlgorithmComparison().complexComparision();
    }

    private void complexComparision(){
        loadData();
        List<Route> routeList = new ArrayList<>(dataBase.getRoutes().size());

        //1. random order
        System.out.println("1. Dane w kolejnosci losowej");
        RouteMapper.toRoutes(dataBase.getRoutes(), routeList);
        singleComparision(routeList);

        //2. ordered ascending
        System.out.println("2. Dane uporzadkowane rosnaco");
        routeList.clear();
        RouteMapper.toRoutes(dataBase.getRoutes(), routeList);
        java.util.Collections.sort(routeList, shortestDistanceComparator);
        singleComparision(routeList);

        //3. ordered descending
        System.out.println("3. Dane uporzadkowane malejaco");
        routeList.clear();
        RouteMapper.toRoutes(dataBase.getRoutes(), routeList);
        java.util.Collections.sort(routeList, longestDistanceComparator);
        singleComparision(routeList);

    }

    private void singleComparision(List<Route> routeListToSort){
        List<Route> routeList = new CustomArrayList<>(dataBase.getRoutes().size());

        //InsertionSort
        copyRouteList(routeListToSort, routeList);
        SortResultDs<Route> insertionResult = InsertionSortService.sort(routeList, shortestDistanceComparator);

        //SelectionSort
        copyRouteList(routeListToSort, routeList);
        SortResultDs<Route> selectionResult = SelectionSortService.sort(routeList, shortestDistanceComparator);

        //MergeSort
        copyRouteList(routeListToSort, routeList);
        SortResultDs<Route> mergeResult = MergeSortService.sort(routeList, shortestDistanceComparator);

        //QuickSort
        copyRouteList(routeListToSort, routeList);
        SortResultDs<Route> quickResult = QuickSortService.sort(routeList, shortestDistanceComparator);

        //HeapSort



        System.out.println("Insertion: " + insertionResult.getEstimatedTime());
        System.out.println("Selection: " + selectionResult.getEstimatedTime());
        System.out.println("Merge: " + mergeResult.getEstimatedTime());
        System.out.println("Quick: " + quickResult.getEstimatedTime());
        //System.out.println("Heap: " + heapResult.getEstimatedTime());

    }

    private static void loadData(){
        DataLoaderImpl loader = new DataLoaderImpl();
        loader.loadAirports(AIRPORTS_FILE_PATH);
        System.out.println("loaded airports");

        loader.loadAirline(AIRLINES_FILE_PATH);
        System.out.println("loaded airlines");

        loader.loadRoute(ROUTES_FILE_PATH);
        System.out.println("loaded routes");
    }

    private void copyRouteList(List<Route> source, List<Route> target){
        target.clear();
        for(Route r : source){
            target.add(r);
        }
    }



}
