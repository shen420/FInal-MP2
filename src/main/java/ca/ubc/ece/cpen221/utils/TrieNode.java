package ca.ubc.ece.cpen221.utils;

/**
 * This class models a node of the CollectionTrie.
 */
public class TrieNode {
    //  Rep Invariant:
    //      bLeaf is always true for leaf node and otherwise false
    //      leaf node is always a word of Collection and without children
    //      children size <= 26

    //  Abstract Function:
    //      children's element value is null when bLeaf == false

    private static final int ALPHABET_SIZE = 26;
    private TrieNode[] children = new TrieNode[ALPHABET_SIZE];
    private boolean bLeaf;

    /**
     * Create a new TrieNode with default values
     */
    TrieNode() {
        bLeaf = false;
        for (int i = 0; i < ALPHABET_SIZE; i++)
            children[i] = null;
    }

    /**
     * Checks if a node is a leaf or not
     *
     * @return true when a node is leafnode, false otherwise
     */
    public Boolean isLeaf() {
        return this.bLeaf;
    }

    /**
     * Set leaf flag
     *
     * @param value Boolean type
     */
    public void setIsLeaf(Boolean value) {
        this.bLeaf = value;
    }

    /**
     * Return TrieNode's children
     *
     * @return an array of TrieNode
     */
    public TrieNode[] getChildren() {
        return this.children;
    }
}