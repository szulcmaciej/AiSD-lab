package com.asid.foundation.datastructure.graph;


public class DefaultEdge<V> implements Comparable<DefaultEdge>{
    private V source;
    private V target;
    private long weight;

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public V getSource() {
        return source;
    }

    public void setSource(V source) {
        this.source = source;
    }

    public V getTarget() {
        return target;
    }

    public void setTarget(V target) {
        this.target = target;
    }

    public boolean equals(Object e){
        if(e == this) return true;
        if (e == null || getClass() != e.getClass()) return false;

        DefaultEdge<V> edge = (DefaultEdge<V>) e;

        return source.equals(edge.source) &&
                target.equals(edge.target) &&
                weight == edge.weight;
    }

    public DefaultEdge(V source, V target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public int compareTo(DefaultEdge o) {
        return ((Long) weight).compareTo(o.getWeight());
    }
}
