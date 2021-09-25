package com.learn.algorithms2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver
{
    private static final int RADIX = 26;

    private Region[] adj;
    private Node root;
    private boolean[] marked;
    private char[] board;
    private int rows;
    private int cols;

    // Initializes the data structure using the given array of strings as the dictionary.
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException("Input dictionary cannot be null");

        for (int i = 0; i < dictionary.length; i++) {
            addNode(dictionary[i]);
        }

    }

    private void addNode(String word) {
        root = add(root, word, 0);
    }

    private Node add(Node x, String word, int d) {
        if (x == null) x = new Node();
        if (d == word.length()) x.isWord = true;
        else {
            char c = word.charAt(d);
            x.nodeList[c - 'A'] = add(x.nodeList[c - 'A'], word, d + 1);
        }
        return x;
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null)
            throw new IllegalArgumentException("the argument to getAllValidWords() is null\n");

        if (rows != board.rows() || cols != board.cols()) {
            rows = board.rows();
            cols = board.cols();
            marked = new boolean[rows * cols];
            this.board = new char[rows * cols];
            formAdjList();
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int idx = i * cols + j;
                char c = board.getLetter(i, j);
                this.board[idx] = c;
            }
        }

        SET<String> allValidWords = searchForWords();
        return allValidWords;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Cannot pass null string to scoreOf");
        }

        if (!contains(word)) {
            return 0;
        }
        int len = word.length();
        if (len == 0 || len == 1 || len == 2) {
            return 0;
        }
        if (len == 3 || len == 4) {
            return 1;
        }
        if (len == 5) {
            return 2;
        }
        if (len == 6) {
            return 3;
        }
        if (len == 7) {
            return 5;
        } else {
            return 11;
        }
    }

    private SET<String> searchForWords() {
        SET<String> words = new SET<String>();
        for (int i = 0; i < rows * cols; i++)
            searchForWords(i, new StringBuilder(), words, root);
        return words;
    }

    private void searchForWords(int idx, StringBuilder pre, SET<String> words, Node n) {
        Node next = formPrefix(board[idx], pre, words, n);
        if (next == null) return;

        marked[idx] = true;
        for (int k = 0; k < adj[idx].n; k++) {
            int nextIdx = adj[idx].adjacent[k];
            if (!marked[nextIdx])
                searchForWords(nextIdx, new StringBuilder(pre), words, next);
        }
        marked[idx] = false;
    }

    private Node formPrefix(char c1, StringBuilder pre, SET<String> words, Node n) {
        char c = c1;
        Node next = n.nodeList[c - 'A'];
        if (c == 'Q' && next != null)
            next = next.nodeList['U' - 'A'];
        if (next == null) return null;

        if (c == 'Q') pre.append("QU");
        else pre.append(c);
        String str = pre.toString();
        if (pre.length() > 2 && next.isWord)
            words.add(str);
        return next;
    }

    private void formAdjList() {
        adj =  new Region[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int idx = i * cols + j;
                adj[idx] = new Region();
                populateAdjacencyList(i, j, idx);
            }
        }
    }

    private void populateAdjacencyList(int i, int j, int idx) {
        if (i > 0) {
            adj[idx].adjacent[adj[idx].n++] = (i - 1) * cols + j;
            if (j < cols - 1)
                adj[idx].adjacent[adj[idx].n++] = (i - 1) * cols + j + 1;
        }
        if (i < rows - 1) {
            adj[idx].adjacent[adj[idx].n++] = (i + 1) * cols + j;
            if (j > 0)
                adj[idx].adjacent[adj[idx].n++] = (i + 1) * cols + j - 1;
        }
        if (j > 0) {
            adj[idx].adjacent[adj[idx].n++] = i * cols + j - 1;
            if (i > 0)
                adj[idx].adjacent[adj[idx].n++] = (i - 1) * cols + j - 1;
        }
        if (j < cols - 1) {
            adj[idx].adjacent[adj[idx].n++] = i * cols + j + 1;
            if (i < rows - 1)
                adj[idx].adjacent[adj[idx].n++] = (i + 1) * cols + j + 1;
        }
    }

    private boolean contains(String word) {
        Node x = getNodeInTrie(root, word, 0);
        if (x == null) return false;
        return x.isWord;
    }

    private Node getNodeInTrie(Node x, String word, int d) {
        if (x == null) return null;
        if (d == word.length()) return x;
        char c = word.charAt(d);
        return getNodeInTrie(x.nodeList[c - 'A'], word, d + 1);
    }

    private static class Node {
        private boolean isWord;
        private Node[] nodeList = new Node[RADIX];
    }

    private static class Region {
        private int n = 0;
        private int[] adjacent = new int[8];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

}