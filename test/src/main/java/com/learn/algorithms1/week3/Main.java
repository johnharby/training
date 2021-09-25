package com.learn.algorithms1.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        In in = new In("/Users/johnharby/input8.txt");      // input file
        int n = in.readInt();

        // padding for drawing
        int padding = 1000;

        // set draw scale
        StdDraw.setXscale(-padding, Short.MAX_VALUE + padding);
        StdDraw.setYscale(-padding, Short.MAX_VALUE + padding);

        // Index of array
        int index = 0;

        // turn on animation mode
        StdDraw.enableDoubleBuffering();

        // Create array
        Point[] points = new Point[n];

        points[index] = new Point(in.readInt(), in.readInt());
        points[index].draw();
        StdDraw.show();

        index++;

        while (!in.isEmpty()) {
            points[index] = new Point(in.readInt(), in.readInt());
            points[index].draw();
            StdDraw.show();

            index++;
        }

        FastCollinearPoints fclp = new FastCollinearPoints(points);
        //LineSegment[] segments = fclp.segments();
    }

    public static void pointMain(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
