package com.asid.foundation.datastructure.graph;

import com.asid.foundation.datastructure.list.CustomArrayList;
import org.jgrapht.EdgeFactory;

import java.util.*;

/**
 * Created by Lenovo on 2017-05-04.
 */
public class CustomDirectedWeightGraphAdapter <V, E extends DefaultEdge<V>> extends AbstractDirectedWeightGraphAdapter <V, E>{

    List<V> vertices;
    List<List<E>> edges;
    Map<V, Integer> symbolTable;
    int indexOfNextVertex;

    public CustomDirectedWeightGraphAdapter(EdgeFactory<V, E> edgeFactory, boolean isWeight) {
        super(edgeFactory, isWeight);
        vertices = new CustomArrayList<>();
        edges = new CustomArrayList<>();
        symbolTable = new HashMap<>();
        indexOfNextVertex = 0;
    }


    @Override
    public E getEdge(V v, V v1) {
        if(vertices.contains(v) && vertices.contains(v1)){
            Set<E> edgesOfV = outgoingEdgesOf(v);
            for(E e : edgesOfV){
                if(e.getTarget().equals(v1)){
                    return e;
                }
            }
            return null;
        }
        else
            return null;
    }

    @Override
    public EdgeFactory<V, E> getEdgeFactory() {
        return edgeFactory;
    }

    @Override
    public E addEdge(V v, V v1) {
        return addEdge(v, v1, DEFAULT_WEIGHT);
    }

    @Override
    public boolean addVertex(V v) {
        if(!containsVertex(v)){
            symbolTable.put(v, indexOfNextVertex);
            indexOfNextVertex++;
            vertices.add(v);
            edges.add(new CustomArrayList<E>());
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean containsEdge(V v, V v1) {
        if(containsVertex(v) && containsVertex(v1)){
            Set<E> edgeList = outgoingEdgesOf(v);
            for(E edge : edgeList){
                if(edge.getTarget().equals(v1)){
                    return true;
                }
            }
            return false;
        }
        else
            return false;
    }

    @Override
    public boolean containsEdge(E e) {
        V source = e.getSource();
        Set<E> edgeList = outgoingEdgesOf(source);
        for(E edge : edgeList){
            if(e.equals(edge)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsVertex(V v) {
        return vertices.contains(v);
    }

    @Override
    public Set<E> edgeSet() {
        Set<E> edgeSet = new HashSet<>();
        for(List<E> edgeList : edges){
            edgeSet.addAll(edgeList);
        }
        return edgeSet;
    }

    @Override
    public Set<E> edgesOf(V v) {
        if(v == null) throw new NullPointerException();
        if(!containsVertex(v)) throw new IllegalArgumentException();
        Set<E> edgeSet = new HashSet<>();
        edgeSet.addAll(outgoingEdgesOf(v));
        edgeSet.addAll(incomingEdgesOf(v));
        return edgeSet;
    }

    @Override
    public E removeEdge(V v, V v1) {
        if(containsEdge(v, v1)){
            List<E> edgeList = outgoingEdgeListOf(v);
            E edgeToRemove = null;
            for(E edge : edgeList){
                if(edge.getTarget().equals(v1)){
                    edgeToRemove = edge;
                }
            }
            if(edgeToRemove != null){
                edgeList.remove(edgeToRemove);
            }

            return edgeToRemove;
        }
        else
            return null;
    }

    @Override
    public boolean removeEdge(E e) {
        if(containsEdge(e)){
            List<E> edgeList = outgoingEdgeListOf(e.getSource());
            edgeList.remove(e);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean removeVertex(V v) {
        if(containsVertex(v)){
            //remove all outgoing edges
            Set<E> outgoingEdges = outgoingEdgesOf(v);
            for(E e : outgoingEdges){
                removeEdge(e);
            }

            //remove all incoming edges
            for(List<E> edgeList : edges){
                for(E e : edgeList){
                    if(e.getTarget().equals(v)){
                        removeEdge(e);
                    }
                }
            }

            //remove vertex
            vertices.remove(v);
            symbolTable.remove(v);
            return true;
        }
        else
            return false;
    }

    @Override
    public Set<V> vertexSet() {
        Set<V> vertexSet = new HashSet<>();
        vertexSet.addAll(vertices);
        return vertexSet;
    }

    @Override
    public V getEdgeSource(E e) {
        return e.getSource();
    }

    @Override
    public V getEdgeTarget(E e) {
        return e.getTarget();
    }

    @Override
    public double getEdgeWeight(E e) {
        return e.getWeight();
    }

    @Override
    public void setEdgeWeight(E e, double v) {
        e.setWeight(v);
    }

    @Override
    public int inDegreeOf(V v) {
        return incomingEdgesOf(v).size();
    }

    @Override
    public Set<E> incomingEdgesOf(V v) {
        if(v == null) throw new NullPointerException();
        if(!containsVertex(v)) throw new IllegalArgumentException();

        Set<E> incomingEdges = new HashSet<>();
        for(List<E> edgeList : edges){
            for(E e : edgeList){
                if(e.getTarget().equals(v)){
                    incomingEdges.add(e);
                }
            }
        }

        return incomingEdges;
    }

    @Override
    public int outDegreeOf(V v) {
        return outgoingEdgesOf(v).size();
    }

    @Override
    public Set<E> outgoingEdgesOf(V v) {
        if(v == null) throw new NullPointerException();
        if(!containsVertex(v)) throw new IllegalArgumentException();

        int index = symbolTable.get(v);
        Set<E> outgoingEdges = new HashSet<>();
        outgoingEdges.addAll(edges.get(index));
        return outgoingEdges;
    }

    public E addEdge(V v, V v1, double weight) {
        if(containsVertex(v) && containsVertex(v1)){
            E edge = edgeFactory.createEdge(v, v1);
            edge.setWeight(weight);
            if(containsEdge(edge)){
                return null;
            }
            else {
                outgoingEdgeListOf(v).add(edge);
                return edge;
            }
        }
        else
            return null;
    }

    private List<E> outgoingEdgeListOf(V v){
        if(v == null) throw new NullPointerException();
        if(!containsVertex(v)) throw new IllegalArgumentException();

        int index = symbolTable.get(v);
        return edges.get(index);
    }
}
