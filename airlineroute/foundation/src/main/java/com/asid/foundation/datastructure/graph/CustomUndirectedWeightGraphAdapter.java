package com.asid.foundation.datastructure.graph;

import com.asid.foundation.datastructure.list.CustomArrayList;
import org.jgrapht.EdgeFactory;

import java.util.*;

/**
 * Created by Lenovo on 2017-04-30.
 */
public class CustomUndirectedWeightGraphAdapter <V, E extends DefaultEdge<V>> extends AbstractUndirectedWeightGraphAdapter <V, E>{

    List<V> vertices;
    List<List<E>> edges;
    Map<V, Integer> symbolTable;
    int indexOfNextVertex;

    public CustomUndirectedWeightGraphAdapter(EdgeFactory<V, E> edgeFactory, boolean isWeight) {
        super(edgeFactory, isWeight);
        vertices = new CustomArrayList<>();
        edges = new CustomArrayList<>();
        symbolTable = new HashMap<>();
        indexOfNextVertex = 0;
    }

    @Override
    public int degreeOf(V v) {
        return getEdges(v).size();
    }

    @Override
    public E getEdge(V v, V v1) {
        if(vertices.contains(v) && vertices.contains(v1)){
            List<E> edgesOfV = getEdges(v);
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
            List<E> edgeList = getEdges(v);
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
        List<E> edgeList = getEdges(source);
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
        edgeSet.addAll(getEdges(v));
        return edgeSet;
    }

    @Override
    public E removeEdge(V v, V v1) {
        if(containsEdge(v, v1)){
            List<E> edgeList = getEdges(v);
            E edgeToRemove = null;
            for(E edge : edgeList){
                if(edge.getTarget().equals(v1)){
                    edgeToRemove = edge;
                }
            }
            if(edgeToRemove != null){
                edgeList.remove(edgeToRemove);
            }

            List<E> edgeList1 = getEdges(v1);
            E edgeToRemove1 = null;
            for(E edge : edgeList1){
                if(edge.getTarget().equals(v)){
                    edgeToRemove1 = edge;
                }
            }
            if(edgeToRemove1 != null){
                edgeList1.remove(edgeToRemove1);
            }

            return edgeToRemove;
        }
        else
            return null;
    }

    @Override
    public boolean removeEdge(E e) {
        if(containsEdge(e)){
            List<E> edgeList = getEdges(e.getSource());
            edgeList.remove(e);

            List<E> edgeList1 = getEdges(e.getTarget());
            E edgeToRemove1 = null;
            for(E edge : edgeList1){
                if(edge.getTarget().equals(e.getSource()) && edge.getWeight() == e.getWeight()){
                    edgeToRemove1 = edge;
                }
            }
            if(edgeToRemove1 != null){
                edgeList1.remove(edgeToRemove1);
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean removeVertex(V v) {
        if(containsVertex(v)){
            //remove all connected edges
            List<E> edgeList = getEdges(v);
            for(E e : edgeList){
                removeEdge(e);
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
    public double getEdgeWeight(E e) {
        return e.getWeight();
    }

    @Override
    public void setEdgeWeight(E e, double v) {
        e.setWeight(v);
        E e1 = getEdge(e.getTarget(), e.getSource());
        e1.setWeight(v);
    }

    public E addEdge(V v, V v1, double weight) {
        if(containsVertex(v) && containsVertex(v1)){
            E edge = edgeFactory.createEdge(v, v1);
            //edge.setWeight(weight);
            E edge1 = edgeFactory.createEdge(v1, v);
            //edge1.setWeight(weight);
            if(containsEdge(edge)){
                return null;
            }
            else {
                getEdges(v).add(edge);
                getEdges(v1).add(edge1);
                return edge;
            }
        }
        else
            return null;
    }

    private List<E> getEdges(V v){
        int index = symbolTable.get(v);
        return edges.get(index);
    }
}
