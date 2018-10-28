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
    //  Rep Invariant:
    //      Matrix is always represented by a Map where the key is a vertex and the value is a Set
    //      of the vertex's neighbour

    //  Abstraction Function:
    //      The data type represents an unweighted undirected graph for the client using a Map
    //      It is internally represented by a Map with keys being Vertices and values being the neighbours
    //      of the corresponding vertex
    //      Adding a vertex will add a new entry to the Map with the value being an empty Set
    //      Adding a new edge will add vertices to the value (Set) of both vertices connected by the edge

    private Map<Vertex, Set<Vertex>> adjacentMap;

    /**
     * Constructor that creates a hashmap which represents a graph using adjacency list.
     */
    public AdjacencyListGraph() {
        adjacentMap = new HashMap<Vertex, Set<Vertex>>();

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
        if (!adjacentMap.containsKey(v)) {
            adjacentMap.put(v, new HashSet<>());
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
        if (!adjacentMap.containsKey(v1) || !adjacentMap.containsKey(v2)) {
            return;
        }
        if (edgeExists(v1, v2) == false && !v1.equals(v2)) {
            adjacentMap.get(v1).add(v2);
            adjacentMap.get(v2).add(v1);
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
        if (!adjacentMap.containsKey(v1) || !adjacentMap.containsKey(v2)) {
            return false;
        }
        if (adjacentMap.get(v1).contains(v2)) {
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
        if (!adjacentMap.containsKey(v)) {
            return new ArrayList<>();
        }
        Set<Vertex> neighbors = adjacentMap.get(v);
        List<Vertex> result = new ArrayList<Vertex>(neighbors);
        result.sort(new VertexComparator());
        return result;
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
        if (adjacentMap.size() == 0) {
            return new ArrayList<Vertex>();
        }

        Set<Vertex> vertices = new HashSet<Vertex>(adjacentMap.keySet());
        List<Vertex> allVertices = new ArrayList<Vertex>(vertices);
        allVertices.sort(new VertexComparator());
        return allVertices;
    }
}

/**
 * Comparator for vertex data type
 */
class VertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex a, Vertex b) {
        return a.getLabel().compareTo(b.getLabel());
    }
}
