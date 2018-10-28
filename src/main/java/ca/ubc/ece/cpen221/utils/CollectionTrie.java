package ca.ubc.ece.cpen221.utils;

/**
 * This class models a Collection as a Trie data structure
 */
public class CollectionTrie {
    //  Rep Invariant:
    //      search complexity is always O(n), n is the length of the searching string
    //  Abstract function:
    //      root node is start of trie, bottom nodes are leaf nodes

    static TrieNode root = new TrieNode();

    /**
     * If key not present, inserts key into trie
     * If the key is prefix of trie node, marks leaf node
     *
     * @param key is a word from Dictionary
     */
    public static void insert(String key) {
        TrieNode p = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            int index = c - 'A';
            if (p.getChildren()[index] == null) {
                TrieNode temp = new TrieNode();
                p.getChildren()[index] = temp;
                p = temp;
            } else {
                p = p.getChildren()[index];
            }
        }
        p.setIsLeaf(true);
    }

    /**
     * Checks if the key is a leaf
     *
     * @param key is a searching string
     * @return true if key presents in trie, false other wise
     */
    public static boolean search(String key) {
        TrieNode p = searchNode(key);
        if (p == null) {
            return false;
        } else {
            if (p.isLeaf())
                return true;
        }
        return false;
    }

    /**
     * Get the TrieNode that represents the string
     *
     * @param str is a searching string
     * @return TrieNode if it exists, otherwise return null
     */
    public static TrieNode searchNode(String str) {
        TrieNode p = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = c - 'A';
            if (p.getChildren()[index] != null) {
                p = p.getChildren()[index];
            } else {
                return null;
            }
        }

        if (p == root)
            return null;

        return p;
    }
}
