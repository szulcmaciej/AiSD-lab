package com.asid.algorithms.graph;

import com.asid.algorithms.database.DataLoader;
import com.asid.algorithms.database.DataLoaderImpl;
import com.asid.algorithms.database.InmemmoryDataBase;
import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.entity.Route;
import com.asid.algorithms.routeGraph.AirportEdgeFactory;
import com.asid.foundation.datastructure.graph.CustomDirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Lenovo on 2017-05-26.
 */
public class ShortestRouteFindingTest {
    private static final String FROM = "LAX";
    private static final String TO = "CPT";
    public static final double R = 6372.8; // Earth diameter in kilometers
    private InmemmoryDataBase dataBase;
    CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph;

    @Test
    public void shouldFindShortestRoute(){
        //pre
        Airport airportFrom = null;
        Airport airportTo = null;
        for(Airport a : dataBase.getAirports()){
            if(a.getIataCode().equals(FROM))
                airportFrom = a;
            if(a.getIataCode().equals(TO)){
                airportTo = a;
            }
        }

        //act
        ShortestPathService<Airport, DefaultEdge<Airport>> sp = new ShortestPathService<>();
        MSTResultDs<DefaultEdge<Airport>> result = sp.searchShortestPathUsingDijkstraAlg(graph, airportFrom, airportTo);

        System.out.println("Total path length in kilometers: " + result.getTotalWeight());
        System.out.println("Routes:");
        for(DefaultEdge<Airport> e : result.getEgdes()){
            System.out.println(e);
        }

    }

    @Before
    public void setUpDatabaseAndGraph(){
        setUpDatabase();
        setUpGraph();
    }

    private void setUpDatabase(){
        dataBase = InmemmoryDataBase.getInstance();
        DataLoader loader = new DataLoaderImpl();
        //loader.loadAllData("airports.dat", "airlines.dat", "routes.dat");
        loader.loadAirports("resources\\airports.dat");
        System.out.println("loaded airports");

        loader.loadAirline("resources\\airlines.dat");
        System.out.println("loaded airlines");

        loader.loadRoute("resources\\routes3.dat");
        System.out.println("loaded routes");
    }

    private void setUpGraph(){
        graph = new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), true);

        for(Airport a : dataBase.getAirports()){
            graph.addVertex(a);
        }

        for(Route r : dataBase.getRoutes()){
            double weight = haversine(r.getOrigin().getLatitude(), r.getOrigin().getLongitude(), r.getDestination().getLatitude(), r.getDestination().getLongitude());
            DefaultEdge<Airport> edge = graph.addEdge(r.getOrigin(), r.getDestination(), weight);
        }
    }


    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
