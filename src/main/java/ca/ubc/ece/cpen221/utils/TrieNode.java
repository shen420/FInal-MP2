package ca.ubc.ece.cpen221.utils;

public class TrieNode {

   TrieNode[] children = new TrieNode[26];

    // isEndOfWord is true if the node represents
    // end of a word
    boolean isEndOfWord;

    TrieNode(){
        isEndOfWord = false;
        for (int i = 0; i < 26; i++)
            children[i] = null;
    }

    public  Boolean getEndOfWork(){
       return  this.isEndOfWord;
    }

};