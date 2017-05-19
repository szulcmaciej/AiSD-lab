package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.AbstractUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;

/**
 * Created by Lenovo on 2017-05-17.
 */
public class MinimumSpanningTreeService <V, E extends DefaultEdge<V>> implements AbstractMinimumSpanningTreeService <V, E>{
    @Override
    public MSTResultDs<E> searchMinimumSpanningTreeUsingKruskalAlg(AbstractUndirectedWeightGraphAdapter<V, E> graph) {
        KruskalMST<V, E> mst = new KruskalMST<>(graph);

        MSTResultDs<E> results = new MSTResultDs<>();
        results.setEgdes(mst.edges());
        results.setTotalWeight(mst.weight());
        return results;
    }

    @Override
    public MSTResultDs<E> searchMinimumSpanningTreeUsingPrimAlg(AbstractUndirectedWeightGraphAdapter<V, E> graph) {
        PrimMST<V, E> mst = new PrimMST<>(graph);

        MSTResultDs<E> results = new MSTResultDs<>();
        results.setEgdes(mst.edges());
        results.setTotalWeight(mst.weight());
        return results;
    }
}
