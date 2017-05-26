package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.AbstractDirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.AbstractUndirectedWeightGraphAdapter;
import com.asid.foundation.datastructure.graph.DefaultEdge;
import com.asid.foundation.datatype.CustomPriorityQueue;

import java.util.*;

/**
 * Created by Lenovo on 2017-05-26.
 */
public class DijkstraSPUndirected<V, E extends DefaultEdge<V>> implements ShortestPath<V, E> {

    Map<V, Integer> map;
    private DefaultEdge[] edgeTo;
    private double distTo[];
    private CustomPriorityQueue<V> pq;

    public DijkstraSPUndirected(AbstractUndirectedWeightGraphAdapter<V, E> graph, V s) {
        map = new HashMap<>();
        Iterator<V> iterator = graph.vertexSet().iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            map.put(iterator.next(), i);
        }
        int vertexNumber = graph.vertexSet().size();
        edgeTo = new DefaultEdge[vertexNumber];
        distTo = new double[vertexNumber];
        pq = new CustomPriorityQueue<>(vertexNumber, new Comparator<V>() {
            @Override
            public int compare(V o1, V o2) {
                int indexO1 = map.get(o1);
                int indexO2 = map.get(o2);
                return Double.compare(distTo[indexO1], distTo[indexO2]);
            }
        });

        for (int v = 0; v < vertexNumber; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[map.get(s)] = 0.0;

        pq.add(s);
        while (!pq.isEmpty()) {
            relax(graph, pq.poll());
        }
    }

    private void relax(AbstractUndirectedWeightGraphAdapter<V, E> graph, V vertex) {
        int v = map.get(vertex);
        for (E e : graph.edgesOf(vertex)) {
            int w = map.get(e.getTarget());
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (pq.contains(e.getTarget())) {
                    //pq.change(w, distTo[w]);
                    pq.remove(e.getTarget());
                    pq.add(e.getTarget());
                } else {
                    pq.add(e.getTarget());
                }
            }
        }
    }

    @Override
    public double distTo(V v) {
        return distTo[map.get(v)];
    }

    @Override
    public boolean hasPathTo(V v) {
        return distTo[map.get(v)] < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<E> pathTo(V v) {
        if (!hasPathTo(v))
            return null;
        else {
            Stack<DefaultEdge> path = new Stack<>();
            for (DefaultEdge e = edgeTo[map.get(v)]; e != null; e = edgeTo[map.get(e.getSource())]){
                path.push(e);
            }

            List<E> edges = new ArrayList<>(path.size());
            for(DefaultEdge e : path){
                edges.add((E) e);
            }
            Collections.reverse(edges);
            return edges;
        }
    }

}