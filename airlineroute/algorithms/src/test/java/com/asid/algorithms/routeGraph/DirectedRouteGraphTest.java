package com.asid.algorithms.routeGraph;

import com.asid.algorithms.entity.Airport;
import com.asid.foundation.datastructure.graph.CustomDirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 2017-05-05.
 */
public class DirectedRouteGraphTest {

    @Test
    public void shouldReturnEmptySetOfVerticesAndEdges(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        //act

        //assert
        assertEquals(0, graph.edgeSet().size());
        assertEquals(0, graph.vertexSet().size());

    }
    @Test
    public void shouldAddVerticesToGraph(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        //assert
        assertTrue(graph.containsVertex(a1));
        assertTrue(graph.containsVertex(a2));
        assertTrue(graph.containsVertex(a3));
        assertTrue(graph.containsVertex(a4));
        assertFalse(graph.containsVertex(a5));
        assertEquals(4, graph.vertexSet().size());
        assertEquals(0, graph.edgeSet().size());
    }
    @Test
    public void shouldAddEdgesToGraph(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2);
        graph.addEdge(a2, a3);

        //assert
        assertEquals(1, graph.outgoingEdgesOf(a1).size());
        assertEquals(1, graph.outgoingEdgesOf(a2).size());
        assertEquals(1, graph.incomingEdgesOf(a3).size());
        assertEquals(0, graph.edgesOf(a4).size());
    }
    @Test
    public void shouldSetAndReturnEdgeWeight(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2, 5);
        graph.addEdge(a2, a3);
        graph.addEdge(a3, a4);

        graph.setEdgeWeight(graph.getEdge(a2, a3), 3);

        //assert
        assertEquals(5, graph.getEdge(a1, a2).getWeight());
        assertEquals(3, graph.getEdge(a2, a3).getWeight());
        assertEquals(1, graph.getEdge(a3, a4).getWeight());
    }
    @Test
    public void shouldReturnEdgesOfAVertex(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2);
        graph.addEdge(a2, a3);
        graph.addEdge(a1, a3);

        //assert
        //assertEquals(a2, graph.edgesOf(a1).iterator().next().getTarget());
        assertEquals(2, graph.edgesOf(a1).size());
        assertEquals(2, graph.outgoingEdgesOf(a1).size());
        assertTrue(graph.outgoingEdgesOf(a1).contains(graph.getEdge(a1, a2)));
        assertTrue(graph.outgoingEdgesOf(a1).contains(graph.getEdge(a1, a3)));
        assertFalse(graph.outgoingEdgesOf(a1).contains(graph.getEdge(a2, a3)));
        assertTrue(graph.incomingEdgesOf(a2).contains(graph.getEdge(a1, a2)));
        assertFalse(graph.incomingEdgesOf(a2).contains(graph.getEdge(a2, a3)));
        assertEquals(0, graph.inDegreeOf(a1));
        assertEquals(1, graph.inDegreeOf(a2));
        assertEquals(2, graph.inDegreeOf(a3));
    }
    @Test
    public void shouldReturnSetsOfVerticesAndEdges(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2);
        graph.addEdge(a2, a3);

        //assert
        assertEquals(4, graph.vertexSet().size());
        assertEquals(2, graph.edgeSet().size());
    }
    @Test
    public void shouldReturnDegreesOfVertices(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2);
        graph.addEdge(a2, a3);
        graph.addEdge(a1, a3);

        //assert
        assertEquals(2, graph.outDegreeOf(a1));
        assertEquals(1, graph.outDegreeOf(a2));
        assertEquals(0, graph.outDegreeOf(a3));
        assertEquals(0, graph.outDegreeOf(a4));
        assertEquals(0, graph.inDegreeOf(a1));
        assertEquals(1, graph.inDegreeOf(a2));
        assertEquals(2, graph.inDegreeOf(a3));
        assertEquals(0, graph.inDegreeOf(a4));
    }
    @Test
    public void shouldRemoveEdgesFromGraph(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2);
        graph.addEdge(a2, a3);
        graph.addEdge(a4, a1);


        DefaultEdge<Airport> edge = graph.getEdge(a1, a2);
        DefaultEdge<Airport> removedEdge = graph.removeEdge(a1, a2);

        DefaultEdge<Airport> e = graph.getEdge(a2, a3);
        graph.removeEdge(e);

        //assert
        assertEquals(edge, removedEdge);
        assertEquals(1, graph.edgesOf(a1).size());
        assertEquals(0, graph.edgesOf(a2).size());
        assertEquals(0, graph.edgesOf(a3).size());
        assertEquals(1, graph.edgesOf(a4).size());
    }
    @Test
    public void shouldRemoveVerticesFromGraph(){
        //pre
        CustomDirectedWeightGraphAdapter<Airport, DefaultEdge<Airport>> graph =
                new CustomDirectedWeightGraphAdapter<>(new AirportEdgeFactory(), false);

        Airport a1 = new Airport("WRO", 51, 19, "Wroclaw");
        Airport a2 = new Airport("WAW", 52, 20, "Warszawa");
        Airport a3 = new Airport("POZ", 51, 19, "Poznan");
        Airport a4 = new Airport("KAT", 52, 18, "Katowice");
        Airport a5 = new Airport("KRA", 53, 17, "Krakow");

        //act
        graph.addVertex(a1);
        graph.addVertex(a2);
        graph.addVertex(a3);
        graph.addVertex(a4);

        graph.addEdge(a1, a2);
        graph.addEdge(a2, a3);

        graph.removeVertex(a3);

        //assert
        assertTrue(graph.containsVertex(a1));
        assertTrue(graph.containsVertex(a2));
        assertFalse(graph.containsVertex(a3));
        assertTrue(graph.containsVertex(a4));

        assertEquals(1, graph.edgesOf(a1).size());
        assertEquals(1, graph.edgesOf(a2).size());
        //assertEquals(0, graph.edgesOf(a3).size());
        assertEquals(0, graph.edgesOf(a4).size());

        boolean hasThrownException = false;
        try{
            graph.edgesOf(a3);
        }catch(IllegalArgumentException e){
            hasThrownException = true;
        }
        assertTrue(hasThrownException);
    }
}
