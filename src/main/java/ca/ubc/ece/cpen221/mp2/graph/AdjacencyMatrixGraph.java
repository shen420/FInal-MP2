package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.util.*;
import java.util.Map.Entry;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Matrices.
 *
 ******************************************************************************/

public class AdjacencyMatrixGraph implements Graph {
    //  Rep Invariant:
    //      Matrix is always an ArrayList of Lists that represents a right angled triangle,
    //      with side length always equal to the number of vertices in the graph
    //      The inputs on the hypotenuse of the triangle is always 0
    //      All other inputs of the matrix is always either 0 or 1

    //  Abstraction Function:
    //      The data type represents an unweighted undirected graph for the client using an ArrayList of lists
    //      It is internally represented by a ArrayList of lists of integers where the integer indicates if two
    //      vertices are connected by an edge (0 for no edge and 1 for edge exists)
    //      Each List is a Vertex and its values at the specific index represents the Vertex's connectivity
    //      with other vertices
    //      Adding a vertex will add a new List of int 0s 1 index longer than the previous List to the ArrayList
    //      Adding a new edge will change the 0 at the appropriate index of the List to 1

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
     * Add a vertex to the graph using the adjacency list representation.
     * <p>
     * Precondition: v is not null Vertex
     * <p>
     * Postcondition: adds Vertex v to graph if it is not already in the graph, otherwise do nothing
     *
     * @param v is a vertex
     */
    @Override
    public void addVertex(Vertex v) {
        if (!identifier.containsKey(v)) {
            identifier.put(v, inputOrder);
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            for (int i = 0; i <= inputOrder; i++) {
                tempList.add(0);
            }
            matrixList.add(tempList);
            inputOrder++;
        }
    }

    /**
     * Add an edge connecting two vertices
     * <p>
     * Precondition: v1 and v2 are not null vertices
     * <p>
     * Postcondition: adds an undirected and unweighted edge connecting v1 and v2 iff v1 does not equal v2,
     * and there are no existing edges between the two vertices, otherwise do nothing.
     *
     * @param v1 is a vertex
     * @param v2 is a vertex
     */
    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        if (!identifier.containsKey(v1) || !identifier.containsKey(v2)) {
            return;
        }

        if (edgeExists(v1, v2) == false && !v1.equals(v2)) {
            int smallOrder = Math.min(identifier.get(v1), identifier.get(v2));
            int bigOrder = Math.max(identifier.get(v1), identifier.get(v2));
            matrixList.get(bigOrder).set(smallOrder, 1);
        }
    }

    /**
     * Checks if there is an edge from v1 to v2.
     * <p>
     * Precondition: v1 and v2 are not null vertices
     * <p>
     * PostCondition: true iff an edge from v1 connects to v2 or vice versa, other wise return false
     * return false if v1 or v2 is not in the graph
     *
     * @param v1 is a vertex
     * @param v2 is a vertex
     * @return true iff an edge from v1 connects to v2 or vice versa, other wise return false
     */
    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        if (!identifier.containsKey(v1) || !identifier.containsKey(v2)) {
            return false;
        }
        int smallOrder = Math.min(identifier.get(v1), identifier.get(v2));
        int bigOrder = Math.max(identifier.get(v1), identifier.get(v2));

        if (matrixList.get(bigOrder).get(smallOrder).equals(1)) {
            return true;
        }
        return false;
    }

    /**
     * Obtain an array containing all vertices adjacent to v containing no trailing null elements.
     * <p>
     * Precondition: v is a not null vertex
     * <p>
     * Postcondition: a list containing each vertex w such that there is an edge from v to w, returns list of size 0
     * iff v has no downstream neighbors or v is not in the graph
     *
     * @param v is a vertex
     * @return list containing all neighbour vertices of v
     */
    @Override
    public List<Vertex> getNeighbors(Vertex v) {
        if (!identifier.containsKey(v)) {
            return new ArrayList<>();
        }
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
     * Obtain all vertices in the graph.
     * <p>
     * Postcondition: a list containing all vertices in the graph sorted by label lexicographically in non-descending order,
     * a list of size 0 iff the graph has no vertices
     *
     * @return a list containing all vertices in the graph
     */
    @Override
    public List<Vertex> getVertices() {
        if (identifier.size() == 0) {
            return new ArrayList<Vertex>();
        }

        List<Vertex> allVertices = new ArrayList<Vertex>(identifier.keySet());
        allVertices.sort(new VertexComparator());
        return allVertices;
    }

    private Vertex getVertexByValue(Map<Vertex, Integer> map, int value) {
        Vertex result = null;
        for (Entry<Vertex, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}
