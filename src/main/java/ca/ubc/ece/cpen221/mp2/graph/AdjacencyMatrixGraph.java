package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.utils.In;

import java.util.*;
import java.util.Map.Entry;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Matrices.
 *
 ******************************************************************************/

public class AdjacencyMatrixGraph implements Graph {
    List<List<Integer>> matrixList;
    Map<Vertex, Integer> identifier;
    int inputOrder;

    public AdjacencyMatrixGraph() {
        matrixList = new ArrayList<List<Integer>>();
        identifier = new HashMap<Vertex, Integer>();
        inputOrder = 0;
    }

    @Override
    public void addVertex(Vertex v) {
        if (identifier.containsKey(v)) {
            return;
        }

        identifier.put(v, inputOrder);
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for (int i = 0; i <= inputOrder; i++) {
            tempList.add(0);
        }
        matrixList.add(tempList);
        inputOrder++;
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        int smallOrder = Math.min(identifier.get(v1), identifier.get(v2));
        int bigOrder = Math.max(identifier.get(v1), identifier.get(v2));

        matrixList.get(bigOrder).get(smallOrder).equals(1);
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        int smallOrder = Math.min(identifier.get(v1), identifier.get(v2));
        int bigOrder = Math.max(identifier.get(v1), identifier.get(v2));

        if (matrixList.get(bigOrder).get(smallOrder).equals(1)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        int input = identifier.get(v);
        for (int i = 0; i <= input; i++) {
            if (matrixList.get(input).get(i).equals(1)) {
                neighbors.add(getKeyByValue(identifier, i));
            }
        }
        return neighbors;
    }

    @Override
    public List<Vertex> getVertices() {
        Set<Vertex> vertices = identifier.keySet();
        List<Vertex> allVertices = new ArrayList<Vertex>(vertices);
        Collections.sort(allVertices, new VertexComparator());
        return allVertices;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
