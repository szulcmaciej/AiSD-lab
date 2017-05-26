package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.*;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

/**
 * Created by Lenovo on 2017-05-23.
 */
public class ShortestPathService<V, E extends DefaultEdge<V>> implements AbstractShortestPathService <V, E>{
    @Override
    public MSTResultDs<E> searchShortestPathUsingDijkstraAlg(Graph<V, E> graph, V from, V to) {
        if(graph instanceof CustomDirectedWeightGraphAdapter){
            MSTResultDs<E> results = new MSTResultDs<>();
            DijkstraSPDirected<V, E> dijkstraSPDirected = new DijkstraSPDirected<>((CustomDirectedWeightGraphAdapter<V, E>) graph, from);

            results.setEgdes(dijkstraSPDirected.pathTo(to));
            results.setTotalWeight(dijkstraSPDirected.distTo(to));
            return results;
        }

        else if(graph instanceof CustomUndirectedWeightGraphAdapter){
            MSTResultDs<E> results = new MSTResultDs<>();
            DijkstraSPUndirected<V, E> dijkstraSPUndirected = new DijkstraSPUndirected<>((CustomUndirectedWeightGraphAdapter<V, E>) graph, from);

            results.setEgdes(dijkstraSPUndirected.pathTo(to));
            results.setTotalWeight(dijkstraSPUndirected.distTo(to));
            return results;
        }

        else
            return null;
    }

    @Override
    public MSTResultDs<E> searchShortestPathUsingBellmanFordAlg(Graph<V, E> graph, V from, V to) {
        return null;
    }
}
