package com.learn.algorithms2;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v == w) {
            return 0;
        }
        return getAncestorInfo(v, w).dist;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        AncestorInfo info = getAncestorInfo(v, w);
        return info.anc;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException("Params to length cannot be null");
        }
        AncestorInfo info = getAncestorInfo(v, w);
        return info.dist;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException("Params to ancestor cannot be null");
        }
        AncestorInfo info = getAncestorInfo(v, w);
        return info.anc;
    }

    private class AncestorInfo {
        int anc;
        int dist;

        AncestorInfo(int anc, int dist) {
            this.anc = anc;
            this.dist = dist;
        }
    }

    private AncestorInfo getAncestorInfo(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(digraph, w);

        AncestorInfo info = new AncestorInfo(-1, Integer.MAX_VALUE);

        for (int i = 0; i < this.digraph.V(); i++) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i) &&
                    bfv.distTo(i) + bfw.distTo(i) < info.dist) {
                info.anc = i;
                info.dist = bfv.distTo(i) + bfw.distTo(i);

            }
        }
        if (info.dist == Integer.MAX_VALUE) {
            info.dist = -1;
        }
        return info;
    }

    private AncestorInfo getAncestorInfo(int v, int w) {
        AncestorInfo info = new AncestorInfo(-1, Integer.MAX_VALUE);

        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(digraph, w);

        for (int i = 0; i < digraph.V(); ++i) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i) && bfv.distTo(i) + bfw.distTo(i) < info.dist) {
                info.anc = i;
                info.dist = bfv.distTo(i) + bfw.distTo(i);
            }
        }
        if (info.dist == Integer.MAX_VALUE) {
            info.dist = -1;
        }
        return info;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
