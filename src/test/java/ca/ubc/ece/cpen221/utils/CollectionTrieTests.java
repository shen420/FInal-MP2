package ca.ubc.ece.cpen221.utils;

import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.mp2.boggle.BogglePlayer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import static org.junit.Assert.assertNotNull;

public class CollectionTrieTests {

    private static String keys[] = {"the", "a", "there", "answer", "any",
            "by", "bye", "their"};

    private static char[][] BOGGLE_STRING1 = {
            {'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'D', 'N', 'E'}
    };

    private static char[][] BOGGLE_STRING2 = {
            {'A', 'T', 'E', 'E'},
            {'A', 'P', 'Y', 'O'},
            {'T', 'I', 'N', 'U'},
            {'E', 'D', 'S', 'E'}
    };

    private static BoggleBoard board1;
    private static BoggleBoard board2;
    private static TreeSet<String> commonDictionary;

    @BeforeClass
    public static void setup() throws IOException {
        board1 = new BoggleBoard(BOGGLE_STRING1);
        board2 = new BoggleBoard(BOGGLE_STRING2);

        // about 20K common words
        In in3 = new In(new File("datasets/dictionary-common.txt"));
        commonDictionary = new TreeSet<String>();
        for (String s : in3.readAllStrings()) {
            commonDictionary.add(s);
        }
    }

    @Test
    public void testGetAllValidWords1() {
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};



        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            CollectionTrie.insert(keys[i].toUpperCase());

        // Search for different keys
        if(CollectionTrie.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(CollectionTrie.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(CollectionTrie.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(CollectionTrie.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }
}
