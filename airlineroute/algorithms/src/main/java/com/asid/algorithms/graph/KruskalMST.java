package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.AbstractUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.datatype.CustomPriorityQueue;
import org.jgrapht.alg.util.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lenovo on 2017-05-19.
 */
public class KruskalMST <V, E extends DefaultEdge<V>> {
    private List<E> mst;

    public KruskalMST(AbstractUndirectedWeightGraphAdapter<V, E> graph) {
        mst = new CustomArrayList<>();

        CustomPriorityQueue<E> pq = new CustomPriorityQueue<>(graph.vertexSet().size(), new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });
        for(E e : graph.edgeSet()){
            pq.add(e);
        }

        UnionFind<V> uf = new UnionFind<>(graph.vertexSet());

        while (!pq.isEmpty() && mst.size() < graph.vertexSet().size() - 1){
            E e = pq.poll();
            V v1 = e.getSource();
            V v2 = e.getTarget();

            if(uf.find(v1) != uf.find(v2)){
                uf.union(v1, v2);
                mst.add(e);
            }
        }
    }

    public List<E> edges(){
        return mst;
    }

    public double weight(){
        double weight = 0;
        for(E e : mst){
            weight += e.getWeight();
        }
        return weight;
    }

}
