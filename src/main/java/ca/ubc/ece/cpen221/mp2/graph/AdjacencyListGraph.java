package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import java.util.*;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Lists.
 *
 ******************************************************************************/


public class AdjacencyListGraph implements Graph {
    private Map<Vertex, Set<Vertex>> adjacentMap;

    public AdjacencyListGraph() {
        adjacentMap = new HashMap<Vertex, Set<Vertex>>();

    }


    @Override
    public void addVertex(Vertex v) {
        if (adjacentMap.keySet().contains(v)) {
            return;
        }

        adjacentMap.put(v, new HashSet<>());
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        adjacentMap.get(v1).add(v2);
        adjacentMap.get(v2).add(v1);
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        if (adjacentMap.get(v1).contains(v2)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Vertex> getNeighbors(Vertex v) {
        Set<Vertex> neighbors = adjacentMap.get(v);
        List<Vertex> result = new ArrayList<Vertex>(neighbors);
        return result;
    }

    @Override
    public List<Vertex> getVertices() {
        Set<Vertex> vertices = adjacentMap.keySet();
        List<Vertex> allVertices = new ArrayList<Vertex>(vertices);
        Collections.sort(allVertices, new VertexComparator());
        return allVertices;
    }
}

class VertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex a, Vertex b) {
        return a.getLabel().compareTo(b.getLabel());
    }
}
