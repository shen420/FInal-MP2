package ca.ubc.ece.cpen221.mp2.boggle;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.utils.CollectionTrie;
import ca.ubc.ece.cpen221.utils.TrieNode;

import java.util.*;

public class BogglePlayer {
    private Set<String> words;
    private Set<String> dictionary;
    private HashMap<Vertex, Boolean> visited;

    // Initializes the data type using the given set of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BogglePlayer(String[] dictionary) {
        this( new HashSet<>(Arrays.asList(dictionary)));
    }

    // Initializes the data type using the given set of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BogglePlayer(Set<String> dictionary) {
        this.dictionary = dictionary;
        this.words = new TreeSet<>();
        this.visited = new HashMap<>();
    }

    // Returns the set of all valid words in the given Boggle board, as a Set.
    public Set<String> getAllValidWords(BoggleBoard board) {
        int row = board.rows();
        int col = board.cols();

        Iterator<String> stringIterator = dictionary.iterator();
        while (stringIterator.hasNext()) {
            String tmpWord = stringIterator.next();
            CollectionTrie.insert(tmpWord);
        }

        System.out.println("board===" + board.toString());

        AdjacencyListGraph boggleGraph = new AdjacencyListGraph();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board.getLetter(i, j) == 'Q') {
                    boggleGraph.addVertex(new Vertex("QU", i + " " + j));
                }
                else {
                    boggleGraph.addVertex(new Vertex(String.valueOf(board.getLetter(i, j)), i + " " + j));
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                Vertex a;
                if (board.getLetter(i, j) == 'Q') {
                    a = new Vertex("QU",i + " " + j);

                }
                else {
                    a = new Vertex(String.valueOf(board.getLetter(i, j)),i + " " + j);
                }

                Vertex b1 = getNeighbor(i-1, j-1, board);
                Vertex b2 = getNeighbor(i-1, j, board);
                Vertex b3 = getNeighbor(i-1, j+1, board);
                Vertex b4 = getNeighbor(i, j+1, board);
                Vertex b5 = getNeighbor(i, j-1, board);
                Vertex b6 = getNeighbor(i+1, j-1, board);
                Vertex b7 = getNeighbor(i+1, j, board);
                Vertex b8 = getNeighbor(i+1, j+1, board);
                boggleGraph.addEdge(a, b1);
                boggleGraph.addEdge(a, b2);
                boggleGraph.addEdge(a, b3);
                boggleGraph.addEdge(a, b4);
                boggleGraph.addEdge(a, b5);
                boggleGraph.addEdge(a, b6);
                boggleGraph.addEdge(a, b7);
                boggleGraph.addEdge(a, b8);

            }
        }

        List<Vertex> allVertices = boggleGraph.getVertices();
        Iterator<Vertex> iterator = allVertices.iterator();
        while (iterator.hasNext()) {
            Vertex vertex = iterator.next();
            visited.put(vertex, false);
        }

        System.out.println("BEGIN_____" + new Date());

        List<Vertex> allVertices1 = boggleGraph.getVertices();
        Iterator<Vertex> iterator1 = allVertices1.iterator();
        while (iterator1.hasNext()) {
            Vertex vertex = iterator1.next();
            depthFirstFindWords(vertex, boggleGraph, new ArrayList<>());
        }

        System.out.println("END_____" + new Date());
        System.out.println(words);
        return words;
    }

    public void depthFirstFindWords(Vertex v, AdjacencyListGraph graph, List<Vertex> search) {
        visited.put(v, true);
        search.add(v);
        String str = vertexToString(search);
        TrieNode   node = CollectionTrie.searchNode(str);
        if (node == null) {
            search.remove(search.size() - 1);
            visited.put(v, false);

            return;
        }else {
            if (node.getEndOfWork() && str.length()>=3){
                words.add(str);
                System.out.println("*******************===" + str);
            }
        }


        List<Vertex> neighbors = graph.getNeighborsBoggle(v);
        for (int i = 0; i < neighbors.size(); i++) {
            if (!visited.get(neighbors.get(i))) {
                depthFirstFindWords(neighbors.get(i), graph, search);
            }
        }

        search.remove(search.size() - 1);
        visited.put(v, false);
    }


    private Vertex getNeighbor(int i, int j, BoggleBoard board){
        int row = board.rows();
        int col = board.cols();

        if (i == -1) {
            i = row - 1;
        }
        if (i == row) {
            i = 0 ;
        }
        if (j == -1) {
            j = col - 1;
        }
        if (j == col) {
            j = 0;
        }

        if (board.getLetter(i, j) == 'Q') {
            return new Vertex("QU",i + " " + j);

        }
        else {
            return new Vertex(String.valueOf(board.getLetter(i, j)), i + " " + j);
        }


    }

    private static String vertexToString(List<Vertex> vertexList) {
        Iterator<Vertex> iterator = vertexList.iterator();
        StringBuilder builder = new StringBuilder();

        while (iterator.hasNext()) {
            Vertex tmp = iterator.next();
            builder.append(tmp.toString());
        }

        return builder.toString();
    }

    // Returns the maximum possible score that can be achieved from a given board
    public int getMaximumScore(BoggleBoard board) {
        Set<String> words = getAllValidWords(board);
        int sum = 0;

        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            int score = scoreOf(word);
            sum += score;
        }

        System.out.println("======" + sum);
        return sum;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        int length = word.length();
        int score = 0;
        if (length <= 4) {
            score = 1;
        }
        else if (length == 5) {
            score = 2;
        }
        else if (length == 6) {
            score = 3;
        }
        else if (length == 7) {
            score = 5;
        }
        else {
            score = 11;
        }

        return score;
    }
}