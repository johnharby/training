package com.learn.algorithms2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {

    private final SAP sap;
    private final Digraph digraph;
    private final ST<Integer, String> lineNoMap;
    private final ST<String, SET<Integer>> wordmap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        lineNoMap = new ST<>();
        wordmap = new ST<>();

        In in = new In(synsets);
        int lineno = -1;
        while (in.hasNextLine()) {
            lineno = processSynsets(in);
        }
        this.digraph = new Digraph(lineno + 1);

        in = new In(hypernyms);
        while (in.hasNextLine()) {
            processHypernyms(in);
        }
        sap = new SAP(digraph);
        DirectedCycle finder = new DirectedCycle(digraph);
        if (finder.hasCycle()) {
            throw new IllegalArgumentException("Graph cannot have cycles");
        }
        processNodes();
    }

    private void processNodes() {
        SET<Integer> nodeSet = new SET<Integer>();
        for (int i = 0; i < digraph.V(); i++) {
            DirectedDFS dfs = new DirectedDFS(digraph, i);
            if (dfs.count() == 1) {
                nodeSet.add(i);
            }
        }
        if (nodeSet.size() > 1) {
            throw new IllegalArgumentException("Graph is invalid");
        }
    }

    private void processHypernyms(In in) {
        String line = in.readLine();
        String[] tokens = line.split(",");
        int synsetId = Integer.parseInt(tokens[0]);

        for (int i = 1; i < tokens.length; i++) {
            digraph.addEdge(synsetId, Integer.parseInt(tokens[i]));
        }
    }

    private int processSynsets(In in) {
        int lineno;
        String line = in.readLine();
        String[] tokens = line.split(",");

        lineno = Integer.parseInt(tokens[0]);
        String[] words = tokens[1].split(" ");

        SET<String> set = new SET<String>();
        for (String noun : words) {
            set.add(noun);
        }
        lineNoMap.put(lineno, tokens[1]);

        for (String s : words) {
            if (wordmap.contains(s)) {
                wordmap.get(s).add(lineno);
            } else {
                SET<Integer> set2 = new SET<Integer>();
                set2.add(lineno);
                wordmap.put(s, set2);
            }
        }
        return lineno;
    }

    // returns all com.learn.algorithms2.WordNet nouns
    public Iterable<String> nouns() {
        return wordmap.keys();
    }

    // is the word a com.learn.algorithms2.WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Parameter to isNoun can't be null");
        }
        return wordmap.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Parameters to distance have to be valid nouns");
        }
        SET<Integer> aset = wordmap.get(nounA);
        SET<Integer> bset = wordmap.get(nounB);
        return sap.length(aset, bset);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Parameters to sap have to be valid nouns");
        }
        SET<Integer> aset = wordmap.get(nounA);
        SET<Integer> bset = wordmap.get(nounB);

        int anc = sap.ancestor(aset, bset);
        return lineNoMap.get(anc);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        StdOut.println(args[0]);
        StdOut.println(args[1]);
        WordNet wordnet = new WordNet(args[0], args[1]);

        StdOut.println(wordnet.distance("liberalism", "spider_nevus"));
        StdOut.println(wordnet.sap("liberalism", "spider_nevus"));

        StdOut.println(wordnet.distance("cool_medium", "palm_nut"));
        StdOut.println(wordnet.sap("cool_medium", "palm_nut"));

    }
}

