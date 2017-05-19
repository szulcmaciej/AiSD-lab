package com.asid.algorithms.graph;

import com.asid.foundation.datastructure.graph.DefaultEdge;
import org.jgrapht.EdgeFactory;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Lenovo on 2017-05-19.
 */
public class PositionEdgeFactory implements EdgeFactory<Point2D, DefaultEdge<Point2D>> {
    @Override
    public DefaultEdge<Point2D> createEdge(Point2D p1, Point2D p2) {
        DefaultEdge<Point2D> edge = new DefaultEdge<>(p1, p2);
        edge.setWeight(p1.distance(p2));
        return edge;
    }
}
