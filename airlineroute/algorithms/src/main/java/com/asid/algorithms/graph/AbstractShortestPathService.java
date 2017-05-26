package com.asid.algorithms.graph;

import org.jgrapht.Graph;

/**
 * Do not modify this class if it is not necessary.
 * Please see http://jgrapht.org/javadoc/org/jgrapht/UndirectedGraph.html
 */
public interface AbstractShortestPathService<V,E>  {

    public MSTResultDs<E> searchShortestPathUsingDijkstraAlg(Graph<V, E> graph, V from, V to);

    public MSTResultDs<E> searchShortestPathUsingBellmanFordAlg(Graph<V, E> graph, V from, V to);

}
