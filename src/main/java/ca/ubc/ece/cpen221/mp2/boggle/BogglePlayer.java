package ca.ubc.ece.cpen221.mp2.boggle;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.utils.CollectionTrie;
import ca.ubc.ece.cpen221.utils.TrieNode;

import java.util.*;

/**
 * This class is an application of the graph ADT on the Boggle Board
 */
public class BogglePlayer {
    private Set<String> words;
    private Set<String> dictionary;
    // Tracks if Vertex has been visited or not in the graph traverse
    private HashMap<Vertex, Boolean> visited;

    /**
     * Constructor that creates a BogglePlayer instance using a given dictionary
     * Precondition: each word in the dictionary contains only the uppercase letters A through Z
     *
     * @param dictionary a string array of words
     */
    public BogglePlayer(String[] dictionary) {
        this(new HashSet<>(Arrays.asList(dictionary)));
    }

    /**
     * Constructor that creates a BogglePlayer instance with a dictionary and initializes private members
     * Precondition: each word in the dictionary contains only the uppercase letters A through Z
     *
     * @param dictionary a HashSet of string representing words
     */
    public BogglePlayer(Set<String> dictionary) {
        this.dictionary = dictionary;
        this.words = new TreeSet<>();
        this.visited = new HashMap<>();
    }

    // Returns the set of all valid words in the given Boggle board, as a Set.

    /**
     * Seaches all possible valid words in the Boggle board that is present in the dictionary
     * Precondition: The board is toroidal. The leftmost column is considered adjacent to the
     * rightmost column; the topmost row is considered adjacent to the bottommost row;
     * the upper-left dice/tile is adjacent to the bottom-right dice with diagonal wraparound;
     * the upper-right dice is adjacent to the bottom-left dice. Essentially, each cell or dice
     * or cube has 8 neighbours.
     * <p>
     * Valid words are at least 3 letters long, composed by folllowing a sequence of adjacent dice,
     * and uses each die only least once)
     * <p>
     * The letter Q on the boggle board always represents the two letters: QU
     *
     * Postcondition: The board is never updated.
     *
     * @param board
     * @return the set of all valid words in the given Boggle board, as a Set
     */
    public Set<String> getAllValidWords(BoggleBoard board) {
        int row = board.rows();
        int col = board.cols();

        Iterator<String> stringIterator = dictionary.iterator();
        while (stringIterator.hasNext()) {
            String tmpWord = stringIterator.next();
            CollectionTrie.insert(tmpWord);
        }

        AdjacencyListGraph boggleGraph = new AdjacencyListGraph();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board.getLetter(i, j) == 'Q') {
                    boggleGraph.addVertex(new Vertex("QU", i + " " + j));
                } else {
                    boggleGraph.addVertex(new Vertex(String.valueOf(board.getLetter(i, j)), i + " " + j));
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                Vertex a;
                if (board.getLetter(i, j) == 'Q') {
                    a = new Vertex("QU", i + " " + j);

                } else {
                    a = new Vertex(String.valueOf(board.getLetter(i, j)), i + " " + j);
                }
                Vertex b1 = getNeighbor(i - 1, j - 1, board);
                Vertex b2 = getNeighbor(i - 1, j, board);
                Vertex b3 = getNeighbor(i - 1, j + 1, board);
                Vertex b4 = getNeighbor(i, j + 1, board);
                Vertex b5 = getNeighbor(i, j - 1, board);
                Vertex b6 = getNeighbor(i + 1, j - 1, board);
                Vertex b7 = getNeighbor(i + 1, j, board);
                Vertex b8 = getNeighbor(i + 1, j + 1, board);
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

        List<Vertex> allVertices1 = boggleGraph.getVertices();
        Iterator<Vertex> iterator1 = allVertices1.iterator();
        while (iterator1.hasNext()) {
            Vertex vertex = iterator1.next();
            depthFirstFindWords(vertex, boggleGraph, new ArrayList<>());
        }

        System.out.println(words);
        return words;
    }

    private void depthFirstFindWords(Vertex v, AdjacencyListGraph graph, List<Vertex> search) {
        visited.put(v, true);
        search.add(v);
        String str = vertexToString(search);
        TrieNode node = CollectionTrie.searchNode(str);
        if (node == null) {
            search.remove(search.size() - 1);
            visited.put(v, false);
            return;
        } else {
            if (node.isLeaf() && str.length() >= 3) {
                words.add(str);
            }
        }

        List<Vertex> neighbors = graph.getNeighbors(v);
        for (int i = 0; i < neighbors.size(); i++) {
            if (!visited.get(neighbors.get(i))) {
                depthFirstFindWords(neighbors.get(i), graph, search);
            }
        }

        search.remove(search.size() - 1);
        visited.put(v, false);
    }

    private Vertex getNeighbor(int i, int j, BoggleBoard board) {
        int row = board.rows();
        int col = board.cols();

        if (i == -1) {
            i = row - 1;
        }
        if (i == row) {
            i = 0;
        }
        if (j == -1) {
            j = col - 1;
        }
        if (j == col) {
            j = 0;
        }

        if (board.getLetter(i, j) == 'Q') {
            return new Vertex("QU", i + " " + j);

        } else {
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

    /**
     * Finds the sum of the scores of all valid words in the Boggle Board
     *
     * @param board
     * @return the maximum possible score that can be achieved from a given board
     */
    public int getMaximumScore(BoggleBoard board) {
        Set<String> words = getAllValidWords(board);
        int sum = 0;

        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            int score = scoreOf(word);
            sum += score;
        }
        return sum;
    }

    /**
     * Calculate the score of a given word
     *
     * @param word
     * @return the score of the given word if it is in the dictionary, zero otherwise
     */
    public int scoreOf(String word) {
        int length = word.length();
        int score = 0;
        if (length <= 4) {
            score = 1;
        } else if (length == 5) {
            score = 2;
        } else if (length == 6) {
            score = 3;
        } else if (length == 7) {
            score = 5;
        } else {
            score = 11;
        }
        return score;
    }
}