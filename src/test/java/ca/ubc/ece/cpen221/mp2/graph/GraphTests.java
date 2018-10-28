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
    private static Vertex v10 = new Vertex("10");
    private static Vertex v0 = new Vertex("0");

    private static Graph graph1 = new AdjacencyListGraph();
    private static Graph graph2 = new AdjacencyMatrixGraph();
    private static Graph graph3 = new AdjacencyMatrixGraph();
    private static Graph noEdgeGraph = new AdjacencyListGraph();
    private static Graph graph4 = new AdjacencyListGraph();
    private static Graph graph5 = new AdjacencyMatrixGraph();
    private static Graph graph6 = new AdjacencyListGraph();
    private static Graph graph7 = new AdjacencyMatrixGraph();
    private static Graph segmentedgraph = new AdjacencyListGraph();
    private static Graph emptylistgraph = new AdjacencyMatrixGraph();
    private static Graph emptymatrixgraph = new AdjacencyListGraph();

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
        graph1.addEdge(v9,v10);

        graph2.addVertex(v1);
        graph2.addVertex(v2);
        graph2.addVertex(v3);
        graph2.addVertex(v4);
        graph2.addVertex(v5);
        graph2.addVertex(v6);
        graph2.addVertex(v7);
        graph2.addVertex(v8);
        graph2.addVertex(v9);
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
        graph2.addEdge(v9,v10);

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

        noEdgeGraph.addVertex(v1);
        noEdgeGraph.addVertex(v2);
        noEdgeGraph.addVertex(v3);
        noEdgeGraph.addVertex(v4);
        noEdgeGraph.addVertex(v5);
        noEdgeGraph.addVertex(v6);
        noEdgeGraph.addVertex(v7);
        noEdgeGraph.addVertex(v9);
        noEdgeGraph.addVertex(v8);
        noEdgeGraph.addVertex(v1);

        segmentedgraph.addVertex(v1);
        segmentedgraph.addVertex(v2);
        segmentedgraph.addVertex(v3);
        segmentedgraph.addVertex(v4);
        segmentedgraph.addVertex(v5);
        segmentedgraph.addVertex(v6);
        segmentedgraph.addVertex(v7);
        segmentedgraph.addVertex(v8);
        segmentedgraph.addVertex(v9);
        segmentedgraph.addVertex(v10);
        segmentedgraph.addVertex(v0);

        segmentedgraph.addEdge(v1,v2);
        segmentedgraph.addEdge(v2,v3);
        segmentedgraph.addEdge(v4,v5);
        segmentedgraph.addEdge(v6,v4);
        segmentedgraph.addEdge(v8,v9);
        segmentedgraph.addEdge(v9,v10);
        segmentedgraph.addEdge(v7,v10);

        graph4.addVertex(v1);
        graph5.addVertex(v1);
    }


    @Test
    public void TestGetNeighbor() {
        List<Vertex> expected = new ArrayList<Vertex>();
        expected.add(v1);
        expected.add(v3);
        expected.add(v4);
        expected.add(v5);
        assertEquals(expected, graph1.getNeighbors(v2));
        assertEquals(expected, graph2.getNeighbors(v2));

        expected.clear();
        expected.add(v1);
        expected.add(v2);
        expected.add(v4);
        expected.add(v5);
        assertEquals(expected, graph1.getNeighbors(v3));
        assertEquals(expected, graph2.getNeighbors(v3));

        expected.clear();
        expected.add(v4);
        expected.add(v5);
        expected.add(v6);
        expected.add(v8);
        assertEquals(expected, graph1.getNeighbors(v7));
        assertEquals(expected, graph2.getNeighbors(v7));

        expected.clear();
        expected.add(v4);
        expected.add(v5);
        expected.add(v7);
        expected.add(v8);
        assertEquals(expected, graph1.getNeighbors(v6));
        assertEquals(expected, graph2.getNeighbors(v6));
    }

    @Test
    public void TestNoNeighbor() {
        List<Vertex> expected = new ArrayList<Vertex>();
        assertEquals(expected, graph4.getNeighbors(v2));
        assertEquals(expected, graph4.getNeighbors(v1));
        assertEquals(expected, graph5.getNeighbors(v1));
        assertEquals(expected, graph5.getNeighbors(v2));
    }


    @Test
    public void TestGetVertices() {
        List<Vertex> expected = new ArrayList<Vertex>();
        expected.add(v1);
        expected.add(v2);
        expected.add(v3);
        expected.add(v4);
        expected.add(v5);
        expected.add(v6);
        expected.add(v7);
        expected.add(v8);
        expected.add(v9);

        assertEquals(expected,graph1.getVertices());
        assertEquals(expected, graph2.getVertices());
        expected.clear();
        assertEquals(expected,emptylistgraph.getVertices());
        assertEquals(expected,emptymatrixgraph.getVertices());
    }

    @Test
    public void TestGetNoVertices() {
        List<Vertex> expected = new ArrayList<Vertex>();
        assertEquals(expected,graph6.getVertices());
        assertEquals(expected, graph7.getVertices());
    }

    @Test
    public void TestDuplicateVertex() {
        graph1.addVertex(v9);
        List<Vertex> expected = new ArrayList<Vertex>();
        expected.add(v1);
        expected.add(v2);
        expected.add(v3);
        expected.add(v4);
        expected.add(v5);
        expected.add(v6);
        expected.add(v7);
        expected.add(v8);
        expected.add(v9);

        assertEquals(expected,graph1.getVertices());
        assertEquals(expected, graph2.getVertices());
        expected.clear();
        assertEquals(expected,emptylistgraph.getVertices());
        assertEquals(expected,emptymatrixgraph.getVertices());

    }

    @Test
    public void TestEdgeExists() {
        assertEquals(true, graph1.edgeExists(v1,v2));
        assertEquals(false,noEdgeGraph.edgeExists(v4,v7));
        assertEquals(false,graph2.edgeExists(v8,v2));
        assertEquals(true, graph2.edgeExists(v2,v3));
        assertEquals(false,graph6.edgeExists(v8,v2));
        assertEquals(false,graph7.edgeExists(v2,v3));
    }
}