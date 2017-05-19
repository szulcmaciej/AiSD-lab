package com.asid.algorithms.graph;

import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.routeGraph.AirportEdgeFactory;
import com.asid.foundation.datastructure.graph.CustomUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 2017-05-19.
 */
public class MSTTests {
    @Test
    public void PrimMSTTest(){
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
        assertEquals(5, graph.getEdge(a1, a2).getWeight());
    }
    @Test
    public void KruskalMSTTest(){
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
        assertEquals(5, graph.getEdge(a1, a2).getWeight());
    }
}
