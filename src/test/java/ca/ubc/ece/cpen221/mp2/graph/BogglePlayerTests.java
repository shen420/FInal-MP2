package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.mp2.boggle.BogglePlayer;
import ca.ubc.ece.cpen221.utils.In;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BogglePlayerTests {
    private static char[][] BOGGLE_STRING1 = {
            {'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'D', 'N', 'E'}
    };

    private static char[][] BOGGLE_STRING_216 = {
            {'A', 'T', 'E', 'E'},
            {'A', 'P', 'Y', 'O'},
            {'T', 'I', 'N', 'U'},
            {'E', 'D', 'S', 'E'}
    };

    private static char[][] BOGGLE_STRING_354 = {
            {'S', 'N', 'R', 'T'},
            {'O', 'I', 'E', 'L'},
            {'E', 'Q', 'T', 'T'},
            {'R', 'S', 'A', 'T'}
    };

    private static char[][] BOGGLE_STRING_0 = {
            {'M', 'R', 'M', 'N'},
            {'B', 'G', 'D', 'N'},
            {'T', 'T', 'T', 'V'},
            {'S', 'D', 'F', 'C'}
    };

    private static char[][] BOGGLE_STRING_242 = {
            {'T', 'A', 'S', 'L'},
            {'R', 'S', 'N', 'G'},
            {'L', 'A', 'I', 'D'},
            {'G', 'U', 'H', 'O'}
    };

    private static char[][] BOGGLE_STRING_1162 = {
            {'S', 'T', 'N', 'G'},
            {'E', 'I', 'A', 'E'},
            {'D', 'R', 'L', 'S'},
            {'S', 'E', 'P', 'O'}
    };

    private static char[][] BOGGLE_STRING_2989 = {
            {'D', 'S', 'R', 'O', 'D', 'G'},
            {'T', 'E', 'M', 'E', 'N', 'S'},
            {'R', 'A', 'S', 'I', 'T', 'O'},
            {'D', 'G', 'N', 'T', 'R', 'P'},
            {'R', 'E', 'I', 'A', 'E', 'S'},
            {'T', 'S', 'C', 'L', 'P', 'D'}
    };

    private static BoggleBoard board1;
    private static BoggleBoard board216;
    private static BoggleBoard board354;
    private static BoggleBoard board0;
    private static BoggleBoard board242;
    private static BoggleBoard board1162;
    private static BoggleBoard board2989;

    private static TreeSet<String> commonDictionary;

    @BeforeClass
    public static void setup() throws IOException {
        board1 = new BoggleBoard(BOGGLE_STRING1);
        board216 = new BoggleBoard(BOGGLE_STRING_216);
        board354 = new BoggleBoard(BOGGLE_STRING_354);
        board0 = new BoggleBoard(BOGGLE_STRING_0);
        board242 = new BoggleBoard(BOGGLE_STRING_242);
        board1162 = new BoggleBoard(BOGGLE_STRING_1162);
        board2989 = new BoggleBoard(BOGGLE_STRING_2989);


        // about 20K common words
        In in3 = new In(new File("datasets/dictionary-common.txt"));
        commonDictionary = new TreeSet<String>();
        for (String s : in3.readAllStrings()) {
            commonDictionary.add(s);
        }
    }

    @Test
    public void testGetAllValidWords1() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        player.getAllValidWords(board1);
        assertNotNull(player);
    }

    @Test
    public void testMaxScore216() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        assertEquals(216, player.getMaximumScore(board216));
    }

    @Test
    public void testMaxScore354() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        assertEquals(354, player.getMaximumScore(board354));
    }

    @Test
    public void testMaxScore0() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        assertEquals(0, player.getMaximumScore(board0));
    }

    @Test
    public void testMaxScore242() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        assertEquals(242, player.getMaximumScore(board242));
    }

    @Test
    public void testMaxScore1162() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        assertEquals(1162, player.getMaximumScore(board1162));
    }

    @Test
    public void testMaxScore2989() {
        BogglePlayer player = new BogglePlayer(commonDictionary);
        assertEquals(2989, player.getMaximumScore(board2989));
    }
}
