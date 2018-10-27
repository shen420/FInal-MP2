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

    /**
     * Constructor that creates a hashmap which represents a graph using adjacency list.
     */
    public AdjacencyListGraph() {
        adjacentMap = new HashMap<Vertex, Set<Vertex>>();

    }

    /**
     * Add a vertex to the graph using the adjacency list representation.
     *
     * @param v is not empty and is not already a vertex in the graph
     */
    @Override
    public void addVertex(Vertex v) {

        adjacentMap.put(v, new HashSet<>());
    }

    /**
     * Add an undirected and unweighted edge connecting v1 and v2 iff v1 does not equal v2, and there are no existing
     * edges between the two vertices.
     *
     * @param v1 is a vertex in the graph
     * @param v2 is a vertex in the graph
     */
    @Override
    public void addEdge(Vertex v1, Vertex v2) {
//        System.out.println(v1.toString() + "->" + v2.toString());
        if (edgeExists(v1, v2) == false && !v1.equals(v2)) {
            adjacentMap.get(v1).add(v2);
            adjacentMap.get(v2).add(v1);
        }
    }

    /**
     * Checks if there is an edge from v1 to v2.
     *
     * @param v1 is a vertex in the graph
     * @param v2 is a vertex in the graph
     * @return true iff an edge from v1 connects to v2 or vice versa
     */
    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        if (adjacentMap.get(v1).contains(v2)) {
            return true;
        }
        return false;
    }

    /**
     * Obtain an array containing all vertices adjacent to v containing no trailing null elements.
     *
     * @param v is a vertex in the graph
     * @return list containing each vertex w such that there is an edge from v to w, returns list of size 0
     * iff v has no downstream neighbors.
     */
    @Override
    public List<Vertex> getNeighbors(Vertex v) {
        Set<Vertex> neighbors = adjacentMap.get(v);
        List<Vertex> result = new ArrayList<Vertex>(neighbors);
        result.sort(new VertexComparator());
        return result;
    }

    /**
     * Obtain all vertices in the graph.
     *
     * @return a list containing all vertices in the graph sorted by label in non-descending order, returns
     * list of size 0 iff the graph has no vertices.
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
