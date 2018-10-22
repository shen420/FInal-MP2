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

    /**
     * Constructor that creates a 2D matrix using two Arraylists which represents a graph using adjacency matrix.
     * Creates a counter that labels the input order of vertices.
     * Creates a Hashmap that stores the vertex and its input order.
     */
    public AdjacencyMatrixGraph() {
        matrixList = new ArrayList<List<Integer>>();
        identifier = new HashMap<Vertex, Integer>();
        inputOrder = 0;
    }

    /**
     * Adds a vertex to the graph using the adjacency matrix representation.
     *
     * @param v is not empty and is not already a vertex in the graph
     */
    @Override
    public void addVertex(Vertex v) {
        identifier.put(v, inputOrder);
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for (int i = 0; i <= inputOrder; i++) {
            tempList.add(0);
        }
        matrixList.add(tempList);
        inputOrder++;
    }

    /**
     * Adds an undirected edge from v1 to v2.
     * Postcondition: Adds an edge connecting v1 and v2 unless v1 == v2;
     * adds maximum of 1 edge between two difference vertices.
     *
     * @param v1 is a vertex in the graph
     * @param v2 is a vertex in the graph
     */
    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        if (edgeExists(v1, v2) == false) {
            int smallOrder = Math.min(identifier.get(v1), identifier.get(v2));
            int bigOrder = Math.max(identifier.get(v1), identifier.get(v2));
            matrixList.get(bigOrder).set(smallOrder, 1);
        }
    }

    /**
     * Check if there is an edge from v1 to v2.
     *
     * @param v1 is a vertex in the graph
     * @param v2 is a vertex in the graph
     * @return true iff an edge from v1 connects to v2 or vice versa
     */
    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        int smallOrder = Math.min(identifier.get(v1), identifier.get(v2));
        int bigOrder = Math.max(identifier.get(v1), identifier.get(v2));

        if (matrixList.get(bigOrder).get(smallOrder).equals(1)) {
            return true;
        }
        return false;
    }

    /**
     * Get an array containing all vertices adjacent to v.
     *
     * @param v is a vertex in the graph
     * @return a list containing each vertex w such that there is
     * an edge from v to w. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no downstream neighbors.
     */
    @Override
    public List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        int order = identifier.get(v);
        for (int i = 0; i < identifier.size(); i++) {
            if (matrixList.get(Math.max(i, order)).get(Math.min(i, order)).equals(1)) {
                neighbors.add(getVertexByValue(identifier, i));
            }
        }

        neighbors.sort(new VertexComparator());
        return neighbors;
    }

    /**
     * Get all vertices in the graph.
     *
     * @return a list containing all vertices in the graph,
     * sorted by label in non-descending order, and return a list of size 0 iff the graph has no vertices.
     */
    @Override
    public List<Vertex> getVertices() {
        if (identifier.size() == 0) {
            return new ArrayList<Vertex>();
        }

        List<Vertex> allVertices = new ArrayList<Vertex>(identifier.keySet());
        Collections.sort(allVertices, new VertexComparator());
        return allVertices;
    }

    private Vertex getVertexByValue(Map<Vertex, Integer> map, int value) {
        for (Entry<Vertex, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }
}
