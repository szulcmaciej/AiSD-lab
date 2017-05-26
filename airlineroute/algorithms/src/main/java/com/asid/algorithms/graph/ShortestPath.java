package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.AbstractDirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;

import java.util.List;

/**
 * Created by Lenovo on 2017-05-26.
 */
public interface ShortestPath <V, E extends DefaultEdge<V>> {

    double distTo(V v);

    boolean hasPathTo(V v);

    List<E> pathTo(V v);
}
