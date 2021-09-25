package com.learn.algorithms2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }
    public String outcast(String[] nouns) {
        int distance = 0;
        String ret = null;

        for (String s : nouns) {
            int dist = 0;
            for (String t : nouns) {
                int x = wordnet.distance(s, t);
                dist += x;
            }
            if (dist < distance) {
                distance = dist;
                ret = s;
            }
        }
        assert ret != null;
        return ret;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
