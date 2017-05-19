package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.AbstractUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.CustomUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.datatype.CustomPriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lenovo on 2017-05-19.
 */
public class PrimMST<V, E extends DefaultEdge<V>> {

    private List<V> vertices;
    private boolean[] marked;
    private List<E> mst;
    private CustomPriorityQueue<E> pq;

    public PrimMST(AbstractUndirectedWeightGraphAdapter<V, E> graph) {
        vertices = new ArrayList<>();
        vertices.addAll(graph.vertexSet());

        pq = new CustomPriorityQueue<>(graph.edgeSet().size(), new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });
        marked = new boolean[graph.vertexSet().size()];
        mst = new CustomArrayList<>();

        visit(graph, 0);

        while (!pq.isEmpty()){
            E e = pq.poll();
            V v1 = e.getSource();
            V v2 = e.getTarget();
            int index1 = vertices.indexOf(v1);
            int index2 = vertices.indexOf(v2);

            if(!marked[index1] || !marked[index2]){
                mst.add(e);
                if(!marked[index1])
                    visit(graph, index1);
                if(!marked[index2])
                    visit(graph, index2);
            }
        }
    }

    private void visit(AbstractUndirectedWeightGraphAdapter<V, E> graph, int v){
        marked[v] = true;
        for(E e : graph.edgesOf(vertices.get(v))){
            if(!marked[vertices.indexOf(e.getTarget())]){
                pq.add(e);
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
