package com.asid.algorithms.graph;

import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.routeGraph.AirportEdgeFactory;
import com.asid.foundation.datastructure.graph.CustomDirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.CustomUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 2017-05-26.
 */
public class DijkstraSPTest {
    @Test
    public void DijkstraUndirectedMiniTest(){
        //pre
        CustomUndirectedWeightGraphAdapter<Point2D, DefaultEdge<Point2D>> graph =
                new CustomUndirectedWeightGraphAdapter<>(new PositionEdgeFactory(), true);

        double[] x = {1,2,3,4,5,6,7,8};
        double[] y = {1,2,3,4,5,6,7,8};
        Point2D[] points = new Point2D[8];

        for(int i = 0; i < x.length; i++){
            points[i] = new Point2D.Double(x[i], y[i]);
            graph.addVertex(points[i]);
        }

        DefaultEdge<Point2D> e12 = graph.addEdge(points[0], points[1]);
        graph.setEdgeWeight(e12, 1);
        DefaultEdge<Point2D> e23 = graph.addEdge(points[1], points[2]);
        graph.setEdgeWeight(e23, 2);
        DefaultEdge<Point2D> e34 = graph.addEdge(points[2], points[3]);
        graph.setEdgeWeight(e34, 3);
        DefaultEdge<Point2D> e45 = graph.addEdge(points[3], points[4]);
        graph.setEdgeWeight(e45, 3);
        DefaultEdge<Point2D> e13 = graph.addEdge(points[0], points[2]);
        graph.setEdgeWeight(e13, 2);
        DefaultEdge<Point2D> e14 = graph.addEdge(points[0], points[3]);
        graph.setEdgeWeight(e14, 3);
        DefaultEdge<Point2D> e35 = graph.addEdge(points[2], points[4]);
        graph.setEdgeWeight(e35, 4);
        DefaultEdge<Point2D> e17 = graph.addEdge(points[0], points[6]);
        graph.setEdgeWeight(e17, 1);
        DefaultEdge<Point2D> e27 = graph.addEdge(points[1], points[6]);
        graph.setEdgeWeight(e27, 2);
        DefaultEdge<Point2D> e26 = graph.addEdge(points[1], points[5]);
        graph.setEdgeWeight(e26, 1);
        DefaultEdge<Point2D> e46 = graph.addEdge(points[3], points[5]);
        graph.setEdgeWeight(e46, 1);
        DefaultEdge<Point2D> e38 = graph.addEdge(points[2], points[7]);
        graph.setEdgeWeight(e38, 1);
        DefaultEdge<Point2D> e58 = graph.addEdge(points[4], points[7]);
        graph.setEdgeWeight(e58, 2);


/*
        for(DefaultEdge<Point2D> e : graph.edgeSet()){
            System.out.println(e);
        }
*/

        //act

        ShortestPathService<Point2D, DefaultEdge<Point2D>> sp = new ShortestPathService<>();
        MSTResultDs<DefaultEdge<Point2D>> result = sp.searchShortestPathUsingDijkstraAlg(graph, points[0], points[4]);

        System.out.println("Weight: " + result.getTotalWeight());
        System.out.println("Path:");
        for(DefaultEdge<Point2D> e : result.getEgdes()){
            System.out.println(e);
        }

        //assert
    }
    @Test
    public void DijkstraDirectedMiniTest(){
        //pre
        CustomDirectedWeightGraphAdapter<Point2D, DefaultEdge<Point2D>> graph =
                new CustomDirectedWeightGraphAdapter<>(new PositionEdgeFactory(), true);

        double[] x = {1,2,3,4,5,6,7,8};
        double[] y = {1,2,3,4,5,6,7,8};
        Point2D[] points = new Point2D[8];

        for(int i = 0; i < x.length; i++){
            points[i] = new Point2D.Double(x[i], y[i]);
            graph.addVertex(points[i]);
        }

        DefaultEdge<Point2D> e12 = graph.addEdge(points[0], points[1]);
        graph.setEdgeWeight(e12, 1);
        DefaultEdge<Point2D> e23 = graph.addEdge(points[1], points[2]);
        graph.setEdgeWeight(e23, 2);
        DefaultEdge<Point2D> e34 = graph.addEdge(points[2], points[3]);
        graph.setEdgeWeight(e34, 3);
        DefaultEdge<Point2D> e45 = graph.addEdge(points[3], points[4]);
        graph.setEdgeWeight(e45, 3);
        DefaultEdge<Point2D> e13 = graph.addEdge(points[0], points[2]);
        graph.setEdgeWeight(e13, 2);
        DefaultEdge<Point2D> e14 = graph.addEdge(points[0], points[3]);
        graph.setEdgeWeight(e14, 3);
        DefaultEdge<Point2D> e35 = graph.addEdge(points[2], points[4]);
        graph.setEdgeWeight(e35, 4);
        DefaultEdge<Point2D> e17 = graph.addEdge(points[0], points[6]);
        graph.setEdgeWeight(e17, 1);
        DefaultEdge<Point2D> e27 = graph.addEdge(points[1], points[6]);
        graph.setEdgeWeight(e27, 2);
        DefaultEdge<Point2D> e26 = graph.addEdge(points[1], points[5]);
        graph.setEdgeWeight(e26, 1);
        DefaultEdge<Point2D> e46 = graph.addEdge(points[3], points[5]);
        graph.setEdgeWeight(e46, 1);
        DefaultEdge<Point2D> e38 = graph.addEdge(points[2], points[7]);
        graph.setEdgeWeight(e38, 1);
        DefaultEdge<Point2D> e58 = graph.addEdge(points[4], points[7]);
        graph.setEdgeWeight(e58, 2);


/*
        for(DefaultEdge<Point2D> e : graph.edgeSet()){
            System.out.println(e);
        }
*/

        //act

        ShortestPathService<Point2D, DefaultEdge<Point2D>> sp = new ShortestPathService<>();
        MSTResultDs<DefaultEdge<Point2D>> result = sp.searchShortestPathUsingDijkstraAlg(graph, points[0], points[4]);
        System.out.println("Weight: " + result.getTotalWeight());
        System.out.println("Path:");
        for(DefaultEdge<Point2D> e : result.getEgdes()){
            System.out.println(e);
        }


        //assert
    }

    @Test
    public void DijkstraUndirectedAirportTest(){
        //pre
        CustomUndirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomUndirectedWeightGraphAdapter<>(new AirportEdgeFactory(), true);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);
        graph.addVertex(a5);

        DefaultEdge<Airport> e12 = graph.addEdge(a1, a2);
        DefaultEdge<Airport> e23 = graph.addEdge(a2, a3);
        DefaultEdge<Airport> e34 = graph.addEdge(a3, a4);
        DefaultEdge<Airport> e14 = graph.addEdge(a1, a4);
        DefaultEdge<Airport> e25 = graph.addEdge(a2, a5);
        DefaultEdge<Airport> e35 = graph.addEdge(a3, a5);

        graph.setEdgeWeight(e12, 1);
        graph.setEdgeWeight(e23, 0.8);
        graph.setEdgeWeight(e34, 0.9);
        graph.setEdgeWeight(e14, 0.5);
        graph.setEdgeWeight(e25, 1.5);
        graph.setEdgeWeight(e35, 1.3);

        //act

        ShortestPathService<Airport, DefaultEdge<Airport>> sp = new ShortestPathService<>();
        MSTResultDs<DefaultEdge<Airport>> result = sp.searchShortestPathUsingDijkstraAlg(graph, a1, a5);

        System.out.println("Weight: " + result.getTotalWeight());
        System.out.println("Path:");
        for(DefaultEdge<Airport> e : result.getEgdes()){
            System.out.println(e);
        }

        //assert
    }


}
