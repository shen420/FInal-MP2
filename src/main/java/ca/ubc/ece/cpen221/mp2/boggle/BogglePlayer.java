package ca.ubc.ece.cpen221.mp2.boggle;

import java.util.TreeSet;
import java.util.Set;

public class BogglePlayer {

    // Some empty methods provided to ensure that the
    // build succeeds. You should implement these methods
    // and the others that are required.

    // Initializes the data type using the given set of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BogglePlayer(String[] dictionary) {

    }

    // Initializes the data type using the given set of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BogglePlayer(Set<String> dictionary) {

    }

    // Returns the set of all valid words in the given Boggle board, as a Set.
    public Set<String> getAllValidWords(BoggleBoard board) {
        return new TreeSet<String>();
    }

    // Returns the maximum possible score that can be achieved from a given board
    public int getMaximumScore(BoggleBoard board) {
        return -1;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        return -1;
    }
}
