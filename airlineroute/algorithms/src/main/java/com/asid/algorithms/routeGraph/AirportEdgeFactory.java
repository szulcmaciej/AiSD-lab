package com.asid.algorithms.routeGraph;

import com.asid.algorithms.entity.Airport;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.ClassBasedEdgeFactory;


/*
public class AirportEdgeFactory<V,E extends DefaultEdge<V>> implements EdgeFactory<V,E> {
    @Override
    public E createEdge(V v, V v1) {
        // TODO please provide sensible implementation

        ClassBasedEdgeFactory<V, E> edgeFactory = new ClassBasedEdgeFactory(Airport.class);
        return edgeFactory.createEdge(v, v1);
    }
}
*/

public class AirportEdgeFactory implements EdgeFactory<Airport, DefaultEdge<Airport>> {
    @Override
    public DefaultEdge<Airport> createEdge(Airport source, Airport target) {
        DefaultEdge<Airport> edge = new DefaultEdge<>(source, target);
        double distance = Math.sqrt( (source.getLatitude() - target.getLatitude())*(source.getLatitude() - target.getLatitude()) + (source.getLongitude() - target.getLongitude())*(source.getLongitude() - target.getLongitude()));
        edge.setWeight(distance);
        return edge;
    }
}
