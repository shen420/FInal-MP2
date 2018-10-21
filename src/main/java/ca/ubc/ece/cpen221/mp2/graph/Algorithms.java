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
	 * This is provided as an example to indicate that this method and
	 * other methods should be implemented here.
	 *
	 * You should write the specs for this and all other methods.
	 *
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method and others
		return 0;
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
		// TODO: Implement this method
		return null; // this should be changed

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
	 * You should write the spec for this method
	 */
	 public static Vertex center(Graph graph) {
		 // TODO: Implement this method
		 return null; // this should be changed
	 }

	 /**
	  * You should write the spec for this method
	  */
	 public static int diameter(Graph graph) {
	 	// TODO: Implement this method
		 return -1; // this should be changed
	 }

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
}
