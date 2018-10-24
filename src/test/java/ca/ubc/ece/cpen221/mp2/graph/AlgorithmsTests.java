//package ca.ubc.ece.cpen221.mp2.graph;
//
//import ca.ubc.ece.cpen221.mp2.core.Vertex;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import ca.ubc.ece.cpen221.mp2.core.Graph;
//
//import java.io.IOException;
//import java.util.*;
//
//import static org.junit.Assert.assertEquals;
//
//public class AlgorithmsTests {
//    private static Vertex v1 = new Vertex("1");
//    private static Vertex v2 = new Vertex("2");
//    private static Vertex v3 = new Vertex("3");
//    private static Vertex v4 = new Vertex("4");
//    private static Vertex v5 = new Vertex("5");
//    private static Vertex v6 = new Vertex("6");
//    private static Vertex v7 = new Vertex("7");
//    private static Vertex v8 = new Vertex("8");
//    private static Vertex v9 = new Vertex("9");
//    private static Vertex v0 = new Vertex("0");
//
//    private static Graph graph1 = new AdjacencyListGraph();
//    private static Graph graph2 = new AdjacencyMatrixGraph();
//    private static Graph graph3 = new AdjacencyMatrixGraph();
//    private static Graph graph4 = new AdjacencyListGraph();
//    private static Graph graph5 = new AdjacencyMatrixGraph();
//    private static Graph graph6 = new AdjacencyListGraph();
//
//
//    @BeforeClass
//    public static void setup() throws IOException {
//        graph1.addVertex(v1);
//        graph1.addVertex(v2);
//        graph1.addVertex(v3);
//        graph1.addVertex(v4);
//        graph1.addVertex(v5);
//        graph1.addVertex(v6);
//        graph1.addVertex(v7);
//        graph1.addVertex(v8);
//        graph1.addVertex(v9);
//
//
//        graph1.addEdge(v1, v2);
//        graph1.addEdge(v1, v8);
//        graph1.addEdge(v1, v3);
//        graph1.addEdge(v2, v4);
//        graph1.addEdge(v2, v5);
//        graph1.addEdge(v2, v3);
//        graph1.addEdge(v3, v4);
//        graph1.addEdge(v3, v5);
//        graph1.addEdge(v4, v6);
//        graph1.addEdge(v4, v7);
//        graph1.addEdge(v4, v5);
//        graph1.addEdge(v5, v6);
//        graph1.addEdge(v5, v7);
//        graph1.addEdge(v6, v7);
//        graph1.addEdge(v6, v8);
//        graph1.addEdge(v7, v8);
//
//        graph2.addVertex(v1);
//        graph2.addVertex(v2);
//        graph2.addVertex(v3);
//        graph2.addVertex(v4);
//        graph2.addVertex(v5);
//        graph2.addVertex(v6);
//        graph2.addVertex(v7);
//        graph2.addVertex(v8);
//        graph2.addVertex(v9);
//
//
//        graph2.addEdge(v1, v2);
//        graph2.addEdge(v1, v8);
//        graph2.addEdge(v1, v3);
//        graph2.addEdge(v2, v4);
//        graph2.addEdge(v2, v5);
//        graph2.addEdge(v2, v3);
//        graph2.addEdge(v3, v4);
//        graph2.addEdge(v3, v5);
//        graph2.addEdge(v4, v6);
//        graph2.addEdge(v4, v7);
//        graph2.addEdge(v4, v5);
//        graph2.addEdge(v5, v6);
//        graph2.addEdge(v5, v7);
//        graph2.addEdge(v6, v7);
//        graph2.addEdge(v6, v8);
//        graph2.addEdge(v7, v8);
//
//        graph3.addVertex(v1);
//        graph3.addVertex(v2);
//        graph3.addVertex(v3);
//        graph3.addVertex(v4);
//        graph3.addVertex(v5);
//        graph3.addVertex(v6);
//        graph3.addVertex(v7);
//        graph3.addVertex(v8);
//        graph3.addVertex(v0);
//
//        graph3.addEdge(v1, v2);
//        graph3.addEdge(v1, v3);
//        graph3.addEdge(v3, v0);
//        graph3.addEdge(v3, v6);
//        graph3.addEdge(v3, v4);
//        graph3.addEdge(v5, v0);
//        graph3.addEdge(v5, v4);
//        graph3.addEdge(v7, v8);
//        graph3.addEdge(v8, v7);
//        graph3.addEdge(v8, v8);
//
//        graph4.addVertex(v1);
//        graph4.addVertex(v2);
//        graph4.addVertex(v3);
//        graph4.addVertex(v4);
//        graph4.addVertex(v5);
//        graph4.addVertex(v6);
//        graph4.addVertex(v7);
//        graph4.addVertex(v8);
//        graph4.addVertex(v0);
//
//        graph4.addEdge(v1, v2);
//        graph4.addEdge(v1, v3);
//        graph4.addEdge(v3, v0);
//        graph4.addEdge(v3, v6);
//        graph4.addEdge(v3, v4);
//        graph4.addEdge(v5, v0);
//        graph4.addEdge(v5, v4);
//        graph4.addEdge(v7, v8);
//        graph4.addEdge(v8, v7);
//        graph4.addEdge(v8, v8);
//    }
//
//    @Test
//    public void testBreadthAll1() {
//        Set<List<Vertex>> expected = new HashSet<>();
//        List<Vertex> a1 = new ArrayList<Vertex>();
//        a1.add(v1);
//        a1.add(v2);
//        a1.add(v3);
//        a1.add(v0);
//        a1.add(v4);
//        a1.add(v6);
//        a1.add(v5);
//        List<Vertex> a2 = new ArrayList<Vertex>();
//        a2.add(v2);
//        a2.add(v1);
//        a2.add(v3);
//        a2.add(v0);
//        a2.add(v4);
//        a2.add(v6);
//        a2.add(v5);
//        List<Vertex> a3 = new ArrayList<Vertex>();
//        a3.add(v3);
//        a3.add(v0);
//        a3.add(v1);
//        a3.add(v4);
//        a3.add(v6);
//        a3.add(v5);
//        a3.add(v2);
//        List<Vertex> a4 = new ArrayList<Vertex>();
//        a4.add(v4);
//        a4.add(v3);
//        a4.add(v5);
//        a4.add(v0);
//        a4.add(v1);
//        a4.add(v6);
//        a4.add(v2);
//        List<Vertex> a5 = new ArrayList<Vertex>();
//        a5.add(v5);
//        a5.add(v0);
//        a5.add(v4);
//        a5.add(v3);
//        a5.add(v1);
//        a5.add(v6);
//        a5.add(v2);
//        List<Vertex> a6 = new ArrayList<Vertex>();
//        a6.add(v6);
//        a6.add(v3);
//        a6.add(v0);
//        a6.add(v1);
//        a6.add(v4);
//        a6.add(v5);
//        a6.add(v2);
//        List<Vertex> a0 = new ArrayList<Vertex>();
//        a0.add(v0);
//        a0.add(v3);
//        a0.add(v5);
//        a0.add(v1);
//        a0.add(v4);
//        a0.add(v6);
//        a0.add(v2);
//        List<Vertex> a7 = new ArrayList<Vertex>();
//        a7.add(v7);
//        a7.add(v8);
//        List<Vertex> a8 = new ArrayList<Vertex>();
//        a8.add(v8);
//        a8.add(v7);
//
//        expected.add(a1);
//        expected.add(a2);
//        expected.add(a3);
//        expected.add(a4);
//        expected.add(a5);
//        expected.add(a6);
//        expected.add(a7);
//        expected.add(a8);
//        expected.add(a0);
//
//        assertEquals(expected, Algorithms.breadthFirstSearch(graph3));
//        assertEquals(expected, Algorithms.breadthFirstSearch(graph4));
//    }
//
//    @Test
//    public void testBreadthAll2() {
//        Set<List<Vertex>> expected = new HashSet<>();
//        assertEquals(expected, Algorithms.breadthFirstSearch(graph5));
//        assertEquals(expected, Algorithms.breadthFirstSearch(graph6));
//    }
//
//    @Test
//    public void testDepthAll1() {
//        Set<List<Vertex>> expected = new HashSet<>();
//        List<Vertex> a1 = new ArrayList<Vertex>();
//        a1.add(v1);
//        a1.add(v2);
//        a1.add(v3);
//        a1.add(v0);
//        a1.add(v5);
//        a1.add(v4);
//        a1.add(v6);
//        List<Vertex> a2 = new ArrayList<Vertex>();
//        a2.add(v2);
//        a2.add(v1);
//        a2.add(v3);
//        a2.add(v0);
//        a2.add(v5);
//        a2.add(v4);
//        a2.add(v6);
//        List<Vertex> a3 = new ArrayList<Vertex>();
//        a3.add(v3);
//        a3.add(v0);
//        a3.add(v5);
//        a3.add(v4);
//        a3.add(v1);
//        a3.add(v2);
//        a3.add(v6);
//        List<Vertex> a4 = new ArrayList<Vertex>();
//        a4.add(v4);
//        a4.add(v3);
//        a4.add(v0);
//        a4.add(v5);
//        a4.add(v1);
//        a4.add(v2);
//        a4.add(v6);
//        List<Vertex> a5 = new ArrayList<Vertex>();
//        a5.add(v5);
//        a5.add(v0);
//        a5.add(v3);
//        a5.add(v1);
//        a5.add(v2);
//        a5.add(v4);
//        a5.add(v6);
//        List<Vertex> a6 = new ArrayList<Vertex>();
//        a6.add(v6);
//        a6.add(v3);
//        a6.add(v0);
//        a6.add(v5);
//        a6.add(v4);
//        a6.add(v1);
//        a6.add(v2);
//        List<Vertex> a0 = new ArrayList<Vertex>();
//        a0.add(v0);
//        a0.add(v3);
//        a0.add(v1);
//        a0.add(v2);
//        a0.add(v4);
//        a0.add(v5);
//        a0.add(v6);
//        List<Vertex> a7 = new ArrayList<Vertex>();
//        a7.add(v7);
//        a7.add(v8);
//        List<Vertex> a8 = new ArrayList<Vertex>();
//        a8.add(v8);
//        a8.add(v7);
//
//        expected.add(a1);
//        expected.add(a2);
//        expected.add(a3);
//        expected.add(a4);
//        expected.add(a5);
//        expected.add(a6);
//        expected.add(a7);
//        expected.add(a8);
//        expected.add(a0);
//
//        assertEquals(expected, Algorithms.depthFirstSearch(graph3));
//        assertEquals(expected, Algorithms.depthFirstSearch(graph4));
//    }
//
//    @Test
//    public void testDepthAll2() {
//        Set<List<Vertex>> expected = new HashSet<>();
//        assertEquals(expected, Algorithms.depthFirstSearch(graph5));
//        assertEquals(expected, Algorithms.depthFirstSearch(graph6));
//    }
//
//    @Test
//    public void testBreadthFirst1() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        expected.add(v1);
//        expected.add(v2);
//        expected.add(v3);
//        expected.add(v8);
//        expected.add(v4);
//        expected.add(v5);
//        expected.add(v6);
//        expected.add(v7);
//        assertEquals(expected, Algorithms.breadthFirstFromV(v1, graph1));
//        assertEquals(expected, Algorithms.breadthFirstFromV(v1, graph2));
//    }
//
//    @Test
//    public void testBreadthFirst2() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        expected.add(v7);
//        expected.add(v4);
//        expected.add(v5);
//        expected.add(v6);
//        expected.add(v8);
//        expected.add(v2);
//        expected.add(v3);
//        expected.add(v1);
//        assertEquals(expected, Algorithms.breadthFirstFromV(v7, graph1));
//        assertEquals(expected, Algorithms.breadthFirstFromV(v7, graph2));
//    }
//
//    @Test
//    public void testBreadthFirst3() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        expected.add(v4);
//        expected.add(v2);
//        expected.add(v3);
//        expected.add(v5);
//        expected.add(v6);
//        expected.add(v7);
//        expected.add(v1);
//        expected.add(v8);
//        assertEquals(expected, Algorithms.breadthFirstFromV(v4, graph1));
//        assertEquals(expected, Algorithms.breadthFirstFromV(v4, graph2));
//    }
//
//    @Test
//    public void testDepthFirst1() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        List<Vertex> result = new ArrayList<Vertex>();
//        Algorithms.depthFirstFromV(v1, graph2, result);
//        expected.add(v1);
//        expected.add(v2);
//        expected.add(v3);
//        expected.add(v4);
//        expected.add(v5);
//        expected.add(v6);
//        expected.add(v7);
//        expected.add(v8);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testDepthFirst2() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        List<Vertex> result = new ArrayList<Vertex>();
//        Algorithms.depthFirstFromV(v4, graph2, result);
//        expected.add(v4);
//        expected.add(v2);
//        expected.add(v1);
//        expected.add(v3);
//        expected.add(v5);
//        expected.add(v6);
//        expected.add(v7);
//        expected.add(v8);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testDepthFirst3() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        List<Vertex> result = new ArrayList<Vertex>();
//        Algorithms.depthFirstFromV(v7, graph1, result);
//        expected.add(v7);
//        expected.add(v4);
//        expected.add(v2);
//        expected.add(v1);
//        expected.add(v3);
//        expected.add(v5);
//        expected.add(v6);
//        expected.add(v8);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testDepthFirst4() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        List<Vertex> result = new ArrayList<Vertex>();
//        Algorithms.depthFirstFromV(v0, graph3, result);
//        expected.add(v0);
//        expected.add(v3);
//        expected.add(v1);
//        expected.add(v2);
//        expected.add(v4);
//        expected.add(v5);
//        expected.add(v6);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testDepthFirst5() {
//        List<Vertex> expected = new ArrayList<Vertex>();
//        List<Vertex> result = new ArrayList<Vertex>();
//        Algorithms.depthFirstFromV(v0, graph4, result);
//        expected.add(v0);
//        expected.add(v3);
//        expected.add(v1);
//        expected.add(v2);
//        expected.add(v4);
//        expected.add(v5);
//        expected.add(v6);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testDistance1() {
//        assertEquals(2, Algorithms.shortestDistance(graph1, v6, v3));
//        assertEquals(2, Algorithms.shortestDistance(graph2, v3, v6));
//        assertEquals(0, Algorithms.shortestDistance(graph1, v1, v1));
//        assertEquals(0, Algorithms.shortestDistance(graph2, v1, v1));
//        assertEquals(2, Algorithms.shortestDistance(graph1, v1, v6));
//        assertEquals(2, Algorithms.shortestDistance(graph2, v6, v1));
//        assertEquals(-1, Algorithms.shortestDistance(graph1, v1, v9));
//        assertEquals(-1, Algorithms.shortestDistance(graph2, v9, v1));
//        assertEquals(3, Algorithms.shortestDistance(graph3, v1, v5));
//        assertEquals(-1, Algorithms.shortestDistance(graph3, v1, v7));
//        assertEquals(2, Algorithms.shortestDistance(graph3, v1, v6));
//        assertEquals(3, Algorithms.shortestDistance(graph4, v1, v5));
//        assertEquals(-1, Algorithms.shortestDistance(graph4, v1, v7));
//        assertEquals(2, Algorithms.shortestDistance(graph4, v1, v6));
//    }
//
//    @Test
//    public void testDiameter1() {
//        assertEquals(2, Algorithms.diameter(graph1));
//        assertEquals(4, Algorithms.diameter(graph3));
//        assertEquals(2, Algorithms.diameter(graph2));
//        assertEquals(4, Algorithms.diameter(graph4));
//    }
//
//    @Test
//    public void testCenter1() {
//        assertEquals(v1, Algorithms.center(graph1));
//        assertEquals(v1, Algorithms.center(graph2));
//        assertEquals(v3, Algorithms.center(graph3));
//        assertEquals(v3, Algorithms.center(graph4));
//    }
//
//    @Test
//    public void testFindEccentricity1() {
//        assertEquals(2, Algorithms.findEccentricity(graph3, v3));
//        assertEquals(2, Algorithms.findEccentricity(graph4, v3));
//    }
//}
//
