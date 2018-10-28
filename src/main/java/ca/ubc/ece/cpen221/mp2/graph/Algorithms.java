package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.util.*;

public class Algorithms {

    /**
     * *********************** Algorithms ****************************
     *
     * Please see the README for this machine problem for a more detailed
     * specification of the behavior of each method that one should
     * implement.
     */

    /**
     * Calculates the shortest path between vertex a and b, the minimum number of edges needed to transverse
     * between the two vertices. Distance between a vertex and itself is 0, returns -1 if no path exists.
     *
     * @param graph is not empty
     * @param a     is a vertex in the graph
     * @param b     is a vertex in the graph
     * @return the shortest distance between vertex a and b, returns -1 if no path exists
     */
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        int level = 0;

        if (a.equals(b)) {
            return level;
        }

        List<Vertex> search = new ArrayList<Vertex>();
        Queue<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Integer> levelMap = new HashMap<Vertex, Integer>();
        queue.add(a);
        levelMap.put(a, 0);

        while (!queue.isEmpty()) {
            Vertex tmp = queue.poll();

            if (!search.contains(tmp)) {
                search.add(tmp);
                List<Vertex> neighbors = graph.getNeighbors(tmp);

                if (neighbors.contains(b)) {
                    return levelMap.get(tmp) + 1;
                }

                for (int i = 0; i < neighbors.size(); i++) {
                    if (!queue.contains(neighbors.get(i))) {
                        queue.add(neighbors.get(i));
                        levelMap.put(neighbors.get(i), levelMap.get(tmp) + 1);
                    }
                }
            }
        }

        return -1; //not found
    }

    /**
     * Perform a complete depth first search of the given graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited. Return a set where each element of the set is a list
     * of elements seen by starting a DFS at a specific vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph vertices).
     *
     * @param graph is not empty
     * @return set of lists where each element is a list of elements ordered by a DFS starting at a
     * specific vertex of the graph.
     */
    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        Set<List<Vertex>> allSearch = new HashSet<List<Vertex>>();
        Iterator<Vertex> iterator = graph.getVertices().iterator();

        while (iterator.hasNext()) {
            Vertex v = iterator.next();
            List<Vertex> search = new ArrayList<Vertex>();
            depthFirstFromV(v, graph, search);
            allSearch.add(search);
        }

        return allSearch;

    }

    /**
     * Perform a complete breadth first search of the given graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited. Return a set where each element of the set is a list
     * of elements seen by starting a BFS at a specific vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph vertices).
     *
     * @param graph is not empty
     * @return set of lists where each element is a list of elements ordered by a BFS starting at a
     * specific vertex of the graph.
     */
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        Set<List<Vertex>> allSearch = new HashSet<List<Vertex>>();
        Iterator<Vertex> iterator = graph.getVertices().iterator();

        while (iterator.hasNext()) {
            Vertex v = iterator.next();
            allSearch.add(breadthFirstFromV(v, graph));
        }

        return allSearch;
    }

    /**
     * Finds the center of the graph, which is the vertex with minimum eccentricity. If a graph has
     * multiple unconnected components, the center of the largest connected component
     * is returned. If two unconnected components are the same size, then the center is
     * determined for the component whose vertices are added to the graph first.
     * If multiple vertices qualify then return the vertex that has — lexicographically —
     * the smallest id.
     *
     * @param graph is not empty
     * @return the vertex that is the center of the graph
     */
    public static Vertex center(Graph graph) {
        Map<Vertex, Integer> eccentricityMap = new HashMap<Vertex, Integer>();
        List<Vertex> vertices = graph.getVertices();
        List<Vertex> maxComponentVertices = null;

        //find the biggest component of the graph
        int maxComponent = 0;
        List<Vertex> componentVertices = null;
        for (int k = 0; k < vertices.size(); k++) {
            componentVertices = breadthFirstFromV(vertices.get(k), graph);
            int size = componentVertices.size();
            if (size > maxComponent) {
                maxComponentVertices = componentVertices;
                maxComponent = size;
            }
        }

        // get minimum eccentricity
        int minEccentricity = Integer.MAX_VALUE;
        for (int i = 0; i < maxComponent; i++) {
            Vertex v = maxComponentVertices.get(i);
            int eccen = findEccentricity(graph, v);
            if (eccen < minEccentricity) {
                minEccentricity = eccen;
            }
            eccentricityMap.put(v, eccen);
        }

        // get the smallest id vertex
        List<Vertex> minVertices = new ArrayList<Vertex>();
        for (Map.Entry<Vertex, Integer> entry : eccentricityMap.entrySet()) {
            if (entry.getValue() == minEccentricity) {
                minVertices.add(entry.getKey());
            }
        }

        minVertices.sort(new VertexComparator());
        return minVertices.get(0);
    }

    /**
     * Calculates the eccentricity of the vertex in the graph, the maximum distance between vertex s and any
     * other vertex t in the graph
     *
     * @param graph  is not empty
     * @param vertex is a vertex in the graph
     * @return eccentricity of the vertex
     */
    private static int findEccentricity(Graph graph, Vertex vertex) {
        List<Integer> distances = new ArrayList<Integer>();
        for (int i = 0; i < graph.getVertices().size(); i++) {
            distances.add(shortestDistance(graph, vertex, graph.getVertices().get(i)));
        }

        Collections.sort(distances);
        return distances.get(distances.size() - 1);
    }

    /**
     * Calculates the diameter of the graph, the maximum distance among the distances between all the pairs
     * of vertices in the graph. Diameter will return a maximum finite distance. Unless all distances are
     * infinite, meaning it is not possible to get from vertex v from vertex s for all vertices, diameter
     * will return infinity. Diameter returns 0 if only 1 vertex in the graph, and returns -1 if the graph is empty.
     *
     * @param graph is not null
     * @return diameter of the graph, returns infinity if graph is not connected, returns 0 if only 1 vertex
     * in the graph, returns -1 if the graph is empty
     */
    public static int diameter(Graph graph) {
        int diameter = -1;
        List<Vertex> vertices = graph.getVertices();
        if (vertices.size() == 1) {
            return 0;
        }
        if(vertices.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                int distance = shortestDistance(graph, vertices.get(i), vertices.get(j));

                if (distance > diameter) {
                    diameter = distance;
                }

            }
        }

        if(diameter == -1) {
            return (int) Double.POSITIVE_INFINITY;
        }
        return diameter;
    }

    /**
     * Return a list of vertices in order starting from vertex v, followed by each subsequent vertices by order of transversing
     * through vertices one entire level at a time, such that each level is added to the list before proceeding to the
     * next, the process continues until all vertices reachable from vertex v are added to the list.
     *
     * @param v     is a vertex in the graph
     * @param graph is not empty
     * @return a list of all vertices reachable from vertex v, sorted by transversing through each level before
     * proceeding to the next.
     */
    private static List<Vertex> breadthFirstFromV(Vertex v, Graph graph) {
        List<Vertex> search = new ArrayList<Vertex>();
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(v);

        while (!queue.isEmpty()) {
            Vertex tmp = queue.poll();

            if (!search.contains(tmp)) {
                search.add(tmp);
                List<Vertex> neighbors = graph.getNeighbors(tmp);

                for (int i = 0; i < neighbors.size(); i++) {
                    queue.add(neighbors.get(i));
                }
            }
        }

        return search;
    }

    /**
     * Add vertices to search list starting from vertex v, followed by transversing each parent node individually
     * until reaching the bottom of the tree before moving to the next parent node. This continues until all vertices
     * reachable from vertex v are added to the list.
     *
     * @param v      is a vertex in the graph
     * @param graph  is not empty
     * @param search is  not null
     */
    private static void depthFirstFromV(Vertex v, Graph graph, List search) {
        if (!search.contains(v)) {
            search.add(v);
            List<Vertex> neighbors = graph.getNeighbors(v);
            for (int i = 0; i < neighbors.size(); i++) {
                depthFirstFromV(neighbors.get(i), graph, search);
            }
        }
    }
}
