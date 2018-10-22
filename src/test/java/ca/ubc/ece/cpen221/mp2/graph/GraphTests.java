package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import org.junit.BeforeClass;
import org.junit.Test;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class GraphTests {
    private static Vertex v1 = new Vertex("1");
    private static Vertex v2 = new Vertex("2");
    private static Vertex v3 = new Vertex("3");
    private static Vertex v4 = new Vertex("4");
    private static Vertex v5 = new Vertex("5");
    private static Vertex v6 = new Vertex("6");
    private static Vertex v7 = new Vertex("7");
    private static Vertex v8 = new Vertex("8");
    private static Vertex v9 = new Vertex("9");

    private static Graph graph1 = new AdjacencyListGraph();
    private static Graph graph2 = new AdjacencyMatrixGraph();
    private static Graph graph3 = new AdjacencyMatrixGraph();

    @BeforeClass
    public static void setup() throws IOException {
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addVertex(v3);
        graph1.addVertex(v4);
        graph1.addVertex(v5);
        graph1.addVertex(v6);
        graph1.addVertex(v7);
        graph1.addVertex(v8);
        graph1.addVertex(v9);


        graph1.addEdge(v1,v2);
        graph1.addEdge(v1,v8);
        graph1.addEdge(v1,v3);
        graph1.addEdge(v2,v4);
        graph1.addEdge(v2,v5);
        graph1.addEdge(v2,v3);
        graph1.addEdge(v3,v4);
        graph1.addEdge(v3,v5);
        graph1.addEdge(v4,v6);
        graph1.addEdge(v4,v7);
        graph1.addEdge(v4,v5);
        graph1.addEdge(v5,v6);
        graph1.addEdge(v5,v7);
        graph1.addEdge(v6,v7);
        graph1.addEdge(v6,v8);
        graph1.addEdge(v7,v8);

        graph2.addVertex(v1);
        graph2.addVertex(v2);
        graph2.addVertex(v3);
        graph2.addVertex(v4);
        graph2.addVertex(v5);
        graph2.addVertex(v6);
        graph2.addVertex(v7);
        graph2.addVertex(v8);
        graph2.addVertex(v9);


        graph2.addEdge(v1,v2);
        graph2.addEdge(v1,v8);
        graph2.addEdge(v1,v3);
        graph2.addEdge(v2,v4);
        graph2.addEdge(v2,v5);
        graph2.addEdge(v2,v3);
        graph2.addEdge(v3,v4);
        graph2.addEdge(v3,v5);
        graph2.addEdge(v4,v6);
        graph2.addEdge(v4,v7);
        graph2.addEdge(v4,v5);
        graph2.addEdge(v5,v6);
        graph2.addEdge(v5,v7);
        graph2.addEdge(v6,v7);
        graph2.addEdge(v6,v8);
        graph2.addEdge(v7,v8);

        graph3.addVertex(v1);
        graph3.addVertex(v2);
        graph3.addVertex(v3);
        graph3.addVertex(v4);
        graph3.addVertex(v5);
        graph3.addVertex(v6);
        graph3.addVertex(v7);
        graph3.addVertex(v8);
        graph3.addVertex(v9);


        graph3.addEdge(v1,v2);
        graph3.addEdge(v1,v3);
        graph3.addEdge(v3,v9);
        graph3.addEdge(v3,v6);
        graph3.addEdge(v3,v4);
        graph3.addEdge(v9,v5);
        graph3.addEdge(v5,v4);
        graph3.addEdge(v7,v8);
    }

    @Test
    public void testBreadthFirst1() {
        List<Vertex> expected = new ArrayList<Vertex>();
        expected.add(v1);
        expected.add(v2);
        expected.add(v3);
        expected.add(v8);
        expected.add(v4);
        expected.add(v5);
        expected.add(v6);
        expected.add(v7);
        assertEquals(expected, Algorithms.breadthFirstFromV(v1, graph1));
        assertEquals(expected, Algorithms.breadthFirstFromV(v1, graph2));
    }

    @Test
    public void testBreadthFirst2() {
        List<Vertex> expected = new ArrayList<Vertex>();
        expected.add(v7);
        expected.add(v4);
        expected.add(v5);
        expected.add(v6);
        expected.add(v8);
        expected.add(v2);
        expected.add(v3);
        expected.add(v1);
        assertEquals(expected, Algorithms.breadthFirstFromV(v7, graph1));
        assertEquals(expected, Algorithms.breadthFirstFromV(v7, graph2));
    }

    @Test
    public void testBreadthFirst3() {
        List<Vertex> expected = new ArrayList<Vertex>();
        expected.add(v4);
        expected.add(v2);
        expected.add(v3);
        expected.add(v5);
        expected.add(v6);
        expected.add(v7);
        expected.add(v1);
        expected.add(v8);
        assertEquals(expected, Algorithms.breadthFirstFromV(v4, graph1));
        assertEquals(expected, Algorithms.breadthFirstFromV(v4, graph2));
    }

    @Test
    public void testDepthFirst1() {
        List<Vertex> expected = new ArrayList<Vertex>();
        List<Vertex> result = new ArrayList<Vertex>();
        Algorithms.depthFirstFromV(v1, graph2, result);
        expected.add(v1);
        expected.add(v2);
        expected.add(v3);
        expected.add(v4);
        expected.add(v5);
        expected.add(v6);
        expected.add(v7);
        expected.add(v8);
        assertEquals(expected, result);
    }

    @Test
    public void testDepthFirst2() {
        List<Vertex> expected = new ArrayList<Vertex>();
        List<Vertex> result = new ArrayList<Vertex>();
        Algorithms.depthFirstFromV(v4, graph2, result);
        expected.add(v4);
        expected.add(v2);
        expected.add(v1);
        expected.add(v3);
        expected.add(v5);
        expected.add(v6);
        expected.add(v7);
        expected.add(v8);
        assertEquals(expected, result);
    }

    @Test
    public void testDepthFirst3() {
        List<Vertex> expected = new ArrayList<Vertex>();
        List<Vertex> result = new ArrayList<Vertex>();
        Algorithms.depthFirstFromV(v7, graph1, result);
        expected.add(v7);
        expected.add(v4);
        expected.add(v2);
        expected.add(v1);
        expected.add(v3);
        expected.add(v5);
        expected.add(v6);
        expected.add(v8);
        assertEquals(expected, result);
    }

    @Test
    public void testDistance1() {
        assertEquals(2, Algorithms.shortestDistance(graph1, v6, v3));
        assertEquals(2, Algorithms.shortestDistance(graph2, v3, v6));
        assertEquals(0, Algorithms.shortestDistance(graph1, v1, v1));
        assertEquals(0, Algorithms.shortestDistance(graph2, v1, v1));
        assertEquals(2, Algorithms.shortestDistance(graph1, v1, v6));
        assertEquals(2, Algorithms.shortestDistance(graph2, v6, v1));
        assertEquals(-1, Algorithms.shortestDistance(graph1, v1, v9));
        assertEquals(-1, Algorithms.shortestDistance(graph2, v9, v1));
        assertEquals(3, Algorithms.shortestDistance(graph3, v1, v5));
        assertEquals(-1, Algorithms.shortestDistance(graph3, v1, v7));
        assertEquals(2, Algorithms.shortestDistance(graph3, v1, v6));
    }

    @Test
    public void testDiameter1() {
        assertEquals(2, Algorithms.diameter(graph1));
        assertEquals(4, Algorithms.diameter(graph3));
    }

    @Test
    public void testCenter1() {
        assertEquals(v1, Algorithms.center(graph1));
        assertEquals(v1, Algorithms.center(graph2));
        assertEquals(v3, Algorithms.center(graph3));
    }

    @Test
    public void testFindEccentricity1() {
        assertEquals(2, Algorithms.findEccentricity(graph3, v3));
    }

//    @Test
//    public void testBFS1() {
//        Set<List<Vertex>>expected = new HashSet<List<Vertex>>();
//        expected.add(new ArrayList<Vertex>());
//        {{1, 2, 3, 8, 4, 5, 6, 7}, {2, 1, 3, 4, 5, 8, 6, 7}, {3, 1, 2, 4, 5, 8, 6, 7}, {4, 2, 3, 5, 6, 7, 1, 8}, {5, 2, 3, 4, 6, 7, 1, 8}, {6, 4, 5, 7, 8, 2, 3, 1}, {7, 4, 5, 6, 8, 2, 3, 1}, {8, 1, 6, 7, 2, 3, 4, 5}};
//        assertEquals(expected, Algorithms.breadthFirstSearch(graph1).toArray());
//        assertEquals(expected, Algorithms.breadthFirstSearch(graph2).toArray());
//    }
}

