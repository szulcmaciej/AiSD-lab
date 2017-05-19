package com.asid.algorithms.graph;

import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.routeGraph.AirportEdgeFactory;
import com.asid.foundation.datastructure.graph.CustomUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 2017-05-19.
 */
public class MSTTests {
    double DELTA = 0.00001;

    @Test
    public void PrimMSTAirportTest(){
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

        graph.addEdge(a1, a2, 5);
        graph.addEdge(a2, a3, 3);
        graph.addEdge(a3, a4, 6);
        graph.addEdge(a1, a4, 2);
        graph.addEdge(a2, a5, 7);
        graph.addEdge(a3, a5, 1);

        //act

        PrimMST<Airport, DefaultEdge<Airport>> mst = new PrimMST<>(graph);

        System.out.println(mst.weight());
        System.out.println(mst.edges().size());

        //assert
        assertEquals(5, graph.getEdge(a1, a2).getWeight(), DELTA);
    }
    @Test
    public void KruskalMSTAirportTest(){
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

        graph.addEdge(a1, a2, 5);
        graph.addEdge(a2, a3, 3);
        graph.addEdge(a3, a4, 6);
        graph.addEdge(a1, a4, 2);
        graph.addEdge(a2, a5, 7);
        graph.addEdge(a3, a5, 1);

        //act

        KruskalMST<Airport, DefaultEdge<Airport>> mst = new KruskalMST<>(graph);

        System.out.println(mst.weight());
        System.out.println(mst.edges().size());

        //assert
        assertEquals(5, graph.getEdge(a1, a2).getWeight(), DELTA);
    }
    @Test
    public void MSTPositionTest(){
        //pre
        CustomUndirectedWeightGraphAdapter<Point2D, DefaultEdge<Point2D>> graph =
                new CustomUndirectedWeightGraphAdapter<>(new PositionEdgeFactory(), true);

        double[] x = {2.0, 3.4, 1.2, 6.5, 3.2, 7.8, 2.3, 9.0, 0.5, 1.2, 6.5, 3.2, 7.8, 2.3};
        double[] y = {7.5, 2.5, 3.4, 1.2, 3.2, 7.8, 2.3, 5.8, 2.7, 7.5, 2.5, 3.4, 1.2, 3.2};
        Point2D[] points = new Point2D[14];

        for(int i = 0; i < x.length; i++){
            points[i] = new Point2D.Double(x[i], y[i]);
            graph.addVertex(points[i]);
        }

        Random random = new Random();
        for(int i = 0; i < 20; i++){
            int i1 = random.nextInt(14);
            int i2 = 0;
            do {
                i2 = random.nextInt(14);
            }while (i1 == i2);
            DefaultEdge<Point2D> edge = graph.addEdge(points[i1], points[i2]);
            graph.setEdgeWeight(edge, random.nextDouble());

        }

        //act

        MinimumSpanningTreeService<Point2D, DefaultEdge<Point2D>> minimumSpanningTreeService =
                new MinimumSpanningTreeService<>();
        //KruskalMST<Point2D, DefaultEdge<Point2D>> mst = new KruskalMST<>(graph);
        //PrimMST<Point2D, DefaultEdge<Point2D>> mst = new PrimMST<>(graph);

        MSTResultDs<DefaultEdge<Point2D>> mstResultDsPrim = minimumSpanningTreeService.searchMinimumSpanningTreeUsingPrimAlg(graph);
        MSTResultDs<DefaultEdge<Point2D>> mstResultDsKruskal = minimumSpanningTreeService.searchMinimumSpanningTreeUsingKruskalAlg(graph);

/*
        System.out.println("weight: " + mst.weight());
        System.out.println("edges: " + mst.edges().size());
*/
        System.out.println();
        System.out.println("Kruskal");
        System.out.println("weight: " + mstResultDsKruskal.getTotalWeight());
        System.out.println("edges: " + mstResultDsKruskal.getEgdes().size());
        System.out.println("Prim");
        System.out.println("weight: " + mstResultDsKruskal.getTotalWeight());
        System.out.println("edges: " + mstResultDsKruskal.getEgdes().size());

        //assert
        //assertEquals(5, graph.getEdge(a1, a2).getWeight(), DELTA);
    }
    @Test
    public void MSTPositionTestMicro(){
        //pre
        CustomUndirectedWeightGraphAdapter<Point2D, DefaultEdge<Point2D>> graph =
                new CustomUndirectedWeightGraphAdapter<>(new PositionEdgeFactory(), true);

        double[] x = {1,2,3,4,5};
        double[] y = {1,2,3,4,5};
        Point2D[] points = new Point2D[5];

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

        System.out.println("Edge number in graph: " + graph.edgeSet().size());

        for(DefaultEdge<Point2D> e : graph.edgeSet()){
            System.out.println(e);
        }

        //act

        MinimumSpanningTreeService<Point2D, DefaultEdge<Point2D>> minimumSpanningTreeService =
                new MinimumSpanningTreeService<>();

        MSTResultDs<DefaultEdge<Point2D>> mstResultDsPrim = minimumSpanningTreeService.searchMinimumSpanningTreeUsingPrimAlg(graph);
        MSTResultDs<DefaultEdge<Point2D>> mstResultDsKruskal = minimumSpanningTreeService.searchMinimumSpanningTreeUsingKruskalAlg(graph);

        System.out.println();
        System.out.println("Kruskal");
        System.out.println("weight: " + mstResultDsKruskal.getTotalWeight());
        System.out.println("edges: " + mstResultDsKruskal.getEgdes().size());
        System.out.println("Prim");
        System.out.println("weight: " + mstResultDsKruskal.getTotalWeight());
        System.out.println("edges: " + mstResultDsKruskal.getEgdes().size());

        //assert
        //assertEquals(5, graph.getEdge(a1, a2).getWeight(), DELTA);
    }
    @Test
    public void MSTPositionTestMini(){
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


        System.out.println("Edge number in graph: " + graph.edgeSet().size());

        for(DefaultEdge<Point2D> e : graph.edgeSet()){
            System.out.println(e);
        }

        //act

        MinimumSpanningTreeService<Point2D, DefaultEdge<Point2D>> minimumSpanningTreeService =
                new MinimumSpanningTreeService<>();

        MSTResultDs<DefaultEdge<Point2D>> mstResultDsPrim = minimumSpanningTreeService.searchMinimumSpanningTreeUsingPrimAlg(graph);
        MSTResultDs<DefaultEdge<Point2D>> mstResultDsKruskal = minimumSpanningTreeService.searchMinimumSpanningTreeUsingKruskalAlg(graph);

        System.out.println();
        System.out.println("Kruskal");
        System.out.println("weight: " + mstResultDsKruskal.getTotalWeight());
        System.out.println("edges: " + mstResultDsKruskal.getEgdes().size());
        System.out.println("Prim");
        System.out.println("weight: " + mstResultDsKruskal.getTotalWeight());
        System.out.println("edges: " + mstResultDsKruskal.getEgdes().size());

        //assert
        //assertEquals(5, graph.getEdge(a1, a2).getWeight(), DELTA);
    }
}
