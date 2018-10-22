package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.utils.In;

import java.util.*;

public class Algorithms<search> {

    /**
     * *********************** Algorithms ****************************
     *
     * Please see the README for this machine problem for a more detailed
     * specification of the behavior of each method that one should
     * implement.
     */

    /**
     * This is provided as an example to indicate that this method and
     * other methods should be implemented here.
     * <p>
     * You should write the specs for this and all other methods.
     *
     * @param graph
     * @param a     is in the graph
     * @param b     is in the graph
     * @return
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

    private static void distanceHelper(Vertex v, Vertex b, Graph graph, int level) {
        List<Vertex> neighbors = graph.getNeighbors(v);

        if (neighbors.contains(b)) {
            level++;
        }
    }

    /**
     * Perform a complete depth first search of the given
     * graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited.
     * Return a set where each element of the set is a list
     * of elements seen by starting a DFS at a specific
     * vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph
     * vertices).
     *
     * @param
     * @return
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
     * Perform a complete breadth first search of the given
     * graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited.
     * Return a set where each element of the set is a list
     * of elements seen by starting a BFS at a specific
     * vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph
     * vertices).
     *
     * @param
     * @return
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
     * Precondition: graph is not empty
     * Postcondition: Returns the center of the graph, which is a vertex in the graph
     *      with minimum eccentricity. Eccentricity of a vertex s is defined as the
     *      maximum distance between s and any other vertex t in the graph. If a graph has
     *      multiple unconnected components, the center of the largest connected component
     *      is returned. If two unconnected components are the same size, then the center is
     *      determined for the component whose vertices are added to the graph first.
     *      If multiple vertices qualify then return the vertex that has — lexicographically —
     *      the smallest id.
     */
    public static Vertex center(Graph graph) {
        Map<Vertex, Integer> eccentricityMap = new HashMap<Vertex, Integer>();
        List<Vertex> vertices = graph.getVertices();
        List<Vertex> componentVertices = new ArrayList<Vertex>();

        int maxComponent = 0;
        int maxIndex = 0;
        for (int k = 0; k < vertices.size(); k++) {
            int size = breadthFirstFromV(vertices.get(k), graph).size();
            if (size > maxComponent) {
                maxComponent = size;
                maxIndex = k;
            }
        }
        componentVertices = (breadthFirstFromV(vertices.get(maxIndex), graph));

        for (int i = 0; i < componentVertices.size(); i++) {
            eccentricityMap.put(componentVertices.get(i), findEccentricity(graph, componentVertices.get(i)));
        }

        int minEccentricity = Integer.MAX_VALUE;
        for (int j = 0; j < componentVertices.size(); j++) {

            int eccen = eccentricityMap.get(componentVertices.get(j));
            if (eccen < minEccentricity) {
                minEccentricity = eccen;
            }
        }

        List<Vertex> minVertices = new ArrayList<Vertex>();
        for (Map.Entry<Vertex, Integer> entry : eccentricityMap.entrySet()) {
            if (entry.getValue() == minEccentricity) {
                minVertices.add(entry.getKey());
            }
        }

        minVertices.sort(new VertexComparator());
        return minVertices.get(0);
    }

    public static int findEccentricity(Graph graph, Vertex vertex) {
        List<Integer> distances = new ArrayList<Integer>();

        for (int i = 0; i < graph.getVertices().size(); i++) {
            distances.add(shortestDistance(graph, vertex, graph.getVertices().get(i)));
        }

        Collections.sort(distances);
        return distances.get(distances.size() - 1);
    }

    /**
     * You should write the spec for this method
     */
    public static int diameter(Graph graph) {
        int diameter = -1;
        List<Vertex> vertices = graph.getVertices();

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                int distance = shortestDistance(graph, vertices.get(i), vertices.get(j));

                if (distance > diameter) {
                    diameter = distance;
                }

            }
        }

        return diameter; //-1 if not connected
    }

    public static List<Vertex> breadthFirstFromV(Vertex v, Graph graph) {
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

    public static void depthFirstFromV(Vertex v, Graph graph, List search) {
        if (v == null) {
            return;
        }

        if (!search.contains(v)) {
            search.add(v);
            List<Vertex> neighbors = graph.getNeighbors(v);
            for (int i = 0; i < neighbors.size(); i++) {
                depthFirstFromV(neighbors.get(i), graph, search);
            }
        }
    }
}
