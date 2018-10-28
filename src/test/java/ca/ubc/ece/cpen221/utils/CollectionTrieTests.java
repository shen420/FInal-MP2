package ca.ubc.ece.cpen221.utils;

import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;

public class CollectionTrieTests {

    private static String keys[] = {"the", "a", "there", "answer", "any",
            "by", "bye", "their"};

    @BeforeClass
    public static void setup() throws IOException {
    }

    @Test
    public void testGetAllValidWords1() {
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};

        // Construct trie
        int i;
        for (i = 0; i < keys.length; i++)
            CollectionTrie.insert(keys[i].toUpperCase());

        // Search for different keys
        if (CollectionTrie.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if (CollectionTrie.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if (CollectionTrie.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if (CollectionTrie.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }
}
