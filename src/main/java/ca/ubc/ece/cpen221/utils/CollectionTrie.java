package ca.ubc.ece.cpen221.utils;

public class CollectionTrie {


    static TrieNode root = new TrieNode();

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    public static void insert(String key) {
        TrieNode p = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            int index = c - 'A';
            if (p.children[index] == null) {
                TrieNode temp = new TrieNode();
                p.children[index] = temp;
                p = temp;
            } else {
                p = p.children[index];
            }
        }
        p.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    public static boolean search(String key) {
        TrieNode p = searchNode(key);
        if (p == null) {
            return false;
        } else {
            if (p.isEndOfWord)
                return true;
        }

        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public static boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if (p == null) {
            return false;
        } else {
            return true;
        }
    }

    public static TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'A';
            if (p.children[index] != null) {
                p = p.children[index];
            } else {
                return null;
            }
        }

        if (p == root)
            return null;

        return p;
    }
}
