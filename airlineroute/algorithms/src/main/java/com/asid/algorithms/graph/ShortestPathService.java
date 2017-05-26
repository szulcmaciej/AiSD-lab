package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.AbstractUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;

/**
 * Created by Lenovo on 2017-05-23.
 */
public class ShortestPathService<V, E extends DefaultEdge<V>> implements AbstractShortestPathService <V, E>{
    @Override
    public MSTResultDs searchShortestPathUsingDijkstraAlg(AbstractUndirectedWeightGraphAdapter graph, Object from, Object to) {
        DijkstraSPDirected<V, E> dijkstraSPDirected = new DijkstraSPDirected<>(graph, (V) from);
    }

    @Override
    public MSTResultDs searchShortestPathUsingBellmanFordAlg(AbstractUndirectedWeightGraphAdapter graph, Object from, Object to) {
        return null;
    }
}
